package sparkStreaming

import kafka.common.TopicAndPartition
import kafka.serializer.StringDecoder
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkException}
import org.apache.spark.streaming.dstream.{DStream, InputDStream}
import org.apache.spark.streaming.kafka.{HasOffsetRanges, KafkaCluster, KafkaUtils}
import org.apache.spark.streaming.{Seconds, StreamingContext}

object DstreamKafka {
    def main(args: Array[String]): Unit = {

        System.setProperty("hadoop.home.dir", "E:\\hadoop-common-2.2.0-bin-master")

        val conf: SparkConf = new SparkConf().setAppName("kafka data").setMaster("local[*]")
        val ssc: StreamingContext = new StreamingContext(conf, Seconds(5))

        /*val input: ReceiverInputDStream[(String, String)] = KafkaUtils.createStream(
            ssc,
            "10.1.1.34:2181,10.1.1.39:2181,10.1.1.12:2181/kafka",
            "sparkStreamingConsumer",
            Map(("my_topic", 2))
        )*/

        val input: InputDStream[(String, String)] = KafkaUtils.createDirectStream[String, String, StringDecoder, StringDecoder](
            ssc,
            Map("bootstrap.servers" -> "10.1.1.34:9092,10.1.1.39:9092,10.1.1.12:9092", "grouo.id" -> "my sparkStreaming"),
            Set("my_topic"),

        )

        val fm: DStream[String] = input.flatMap(_._2.split(" "))
        val map: DStream[(String, Int)] = fm.map((_, 1))
        val result: DStream[(String, Int)] = map.reduceByKey(_ + _)
        result.print()

        ssc.start()
        ssc.awaitTermination()

    }


    def getOffsets(topics: Set[String], kc: KafkaCluster, kafkaParams: Map[String, String]): Map[TopicAndPartition, Long] = {

        val topicAndPartitionsOrNull = kc.getPartitions(topics)
        if (topicAndPartitionsOrNull.isLeft) {
            throw new SparkException(s"$topics in the set may not found")
        }
        else {
            val topicAndPartitions = topicAndPartitionsOrNull.right.get
            val groups = kafkaParams.get("group.id").get
            val offsetOrNull = kc.getConsumerOffsets(groups, topicAndPartitions)
            if (offsetOrNull.isLeft) {
                println(s"$groups you assignment may not exists!now redirect to zero!")
                //如果没有消费过，则从最开始的位置消费
                val erliestOffset = kc.getEarliestLeaderOffsets(topicAndPartitions)
                if (erliestOffset.isLeft)
                    throw new SparkException(s"Topics and Partions not definded not found!")
                else
                    erliestOffset.right.get.map(x => (x._1, x._2.offset))
            }
            else {
                //如果消费组已经存在则从记录的地方开始消费
                offsetOrNull.right.get
            }
        }

    }

    //每次拉取数据后存储offset到ZK
    def upateOffsets(topics: Set[String], kc: KafkaCluster, directRDD: RDD[(String, String)], kafkaParams: Map[String, String]) {
        val offsetRanges = directRDD.asInstanceOf[HasOffsetRanges].offsetRanges
        for (offr <- offsetRanges) {
            val topicAndPartitions = TopicAndPartition(offr.topic, offr.partition)
            val yesOrNo = kc.setConsumerOffsets(kafkaParams.get("group.id").get, Map(topicAndPartitions -> offr.untilOffset))
            if (yesOrNo.isLeft) {
                println(s"Error when update offset of $topicAndPartitions")
            }
        }
    }
}
