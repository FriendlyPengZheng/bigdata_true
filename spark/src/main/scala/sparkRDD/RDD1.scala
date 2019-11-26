package sparkRDD

import org.apache.spark.{SparkConf, SparkContext}

object RDD1 {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setAppName("RDD1").setMaster("local[*]")
    val sc = new SparkContext(conf)
    println(sc)
  }
}
