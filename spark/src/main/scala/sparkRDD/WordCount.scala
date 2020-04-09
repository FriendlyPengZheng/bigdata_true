package sparkRDD

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.catalyst.util.StringUtils
import org.apache.spark.{SparkConf, SparkContext}

object WordCount {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local").setAppName("wc")
    val sc: SparkContext = new SparkContext(conf)
    val input = sc.textFile(("E:\\MyDownloads\\spark.txt"))
    //val input:RDD[String]=sc.makeRDD(Seq("hello","world"))
    val input1: RDD[String] = input.filter(x => x.length != 0)
    val fmap: RDD[String] = input1.flatMap(_.split(" "))
    val myMap: RDD[(String, Int)] = fmap.map(x => (x, 1));
    val result: RDD[(String, Int)] = myMap.reduceByKey((x, y) => x + y);
    //val result = input.filter(x => x.length != 0).flatMap (s => s.split(" ")).map(x => (x, 1)).reduceByKey((x, y) => (x + y))
    result.foreach(println)


    /*
    import scala.math._
    val fun = ceil _
    val a = fun(3.14)
    println(a)*/

    //result.saveAsTextFile("E:\\MyDownloads\\wc-output")
  }
}
