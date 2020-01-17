package sparkStreaming

import java.io.InputStream
import java.util.Properties
import kafka.common.TopicAndPartition
import kafka.message.MessageAndMetadata
import kafka.serializer.StringDecoder
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.{SparkConf, SparkContext}

object DirectConsumeKafka {

    def main(args: Array[String]): Unit = {
        val conf: SparkConf = new SparkConf().setAppName("direct").setMaster("local[*]")
        /*val stream: InputStream = DirectConsumeKafka.getClass.getClassLoader.getResourceAsStream("**.properties")
        val properties = new Properties()
        properties.load(stream)*/
        val kafkaParameters: Map[String, String] = Map(
            "bootstrap.servers" -> "10.1.1.34:9092,10.1.1.39:9092,10.1.1.12:9092",
            "grouo.id" -> "directStreaming",
            "auto.offset.reset" -> "false")

        val topicName = "mytopic"
        val partitionNum = 0
        val untiloffset = 0l

        var topicAndPartition = TopicAndPartition(topicName, partitionNum)
        //val fromOffsets = Map(topicAndPartition, 100l)
        val sc = new StreamingContext(conf, Seconds(5))
        val messageHandler = (mam: MessageAndMetadata[String, String]) => (mam.key(), mam.message())
        //KafkaUtils.createDirectStream(sc, kafkaParameters, fromOffsets, messageHandler)
    }

}
