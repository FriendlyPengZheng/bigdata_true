package sparkRDD

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object RDD1 {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setAppName("RDD1").setMaster("local[*]")
    val sc = new SparkContext(conf)
    val value:RDD[Int] = sc.makeRDD(0 to 5,16)
    println(value.getNumPartitions)
    value.foreach(println(_))
    println(sc)
  }
}
