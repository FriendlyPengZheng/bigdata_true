package sparkStreaming

import org.apache.spark._
import org.apache.spark.streaming._

object WordCount {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("word count streaming").setMaster("local")
    val ssc = new StreamingContext(conf, Seconds(1))
    val input = ssc.socketTextStream("10.1.1.34", 9999)
    val result = input.flatMap(x => x.split(" ")).map(x => (x, 1)).reduceByKey((x, y) => x + y)
    input.print()
    ssc.start()
    ssc.awaitTermination()
  }
}
