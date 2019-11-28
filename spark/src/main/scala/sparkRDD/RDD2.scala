package sparkRDD

import org.apache.spark.{SparkConf, SparkContext}

object RDD2 {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local").setAppName("RDD2 test")
    val sc = new SparkContext(conf)
    //val input = sc.textFile("file:///E:\\MyDownloads\\input.txt")
    val input = sc.textFile("hdfs://10.1.1.34:8020/user/hadoop/input/input2.txt")
    val result = input.flatMap(_.split(" ")).map((_,1)).reduceByKey(_+_)
    result.foreach(println(_))
  }
}
