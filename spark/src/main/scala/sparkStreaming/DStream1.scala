package sparkStreaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}

object DStream1 {
    def main(args: Array[String]): Unit = {

        val conf: SparkConf = new SparkConf().setAppName("Stream1").setMaster("local[*]")
        val stc = new StreamingContext(conf, Seconds(3))
        val input: ReceiverInputDStream[String] = stc.socketTextStream("10.1.1.34",1025)
        val flatmap: DStream[String] = input.flatMap(_.split(" "))
        val map: DStream[(String, Int)] = flatmap.map((_,1))
        val result: DStream[(String, Int)] = map.reduceByKey(_+_)
        result.print()

        stc.start()
        stc.awaitTermination()

    }
}
