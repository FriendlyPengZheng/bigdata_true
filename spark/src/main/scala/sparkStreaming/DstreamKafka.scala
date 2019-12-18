package sparkStreaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}

object DstreamKafka {
    def main(args: Array[String]): Unit = {

        System.setProperty("hadoop.home.dir","E:\\hadoop-common-2.2.0-bin-master")

        val conf: SparkConf = new SparkConf().setAppName("kafka data").setMaster("local[*]")
        val ssc: StreamingContext = new StreamingContext(conf, Seconds(5))

        val input: ReceiverInputDStream[(String, String)] = KafkaUtils.createStream(
            ssc,
            "10.1.1.34:2181,10.1.1.39:2181,10.1.1.12:2181/kafka",
            "sparkStreamingConsumer",
            Map(("my_topic", 2))
        )

        val fm: DStream[String] = input.flatMap(_._2.split(" "))
        val map: DStream[(String, Int)] = fm.map((_,1))
        val result: DStream[(String, Int)] = map.reduceByKey(_+_)
        result.print()

        ssc.start()
        ssc.awaitTermination()

    }
}
