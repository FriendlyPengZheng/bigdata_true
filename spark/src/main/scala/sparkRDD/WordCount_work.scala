package sparkRDD

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.catalyst.util.StringUtils
import org.apache.spark.{SparkConf, SparkContext}

object WordCount_work {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local").setAppName("wc")
    val sc: SparkContext = new SparkContext(conf)
    //val input = sc.textFile(("E:\\MyDownloads\\spark.txt"))
    //val input = sc.textFile(("F:\\A--尚硅谷\\2.资料\\谷粒影音.txt"))
    val input = sc.textFile(("J:\\ps-public.csv"))
    //val input:RDD[String]=sc.makeRDD(Seq("hello","world"))
    //val input1: RDD[String] = input.filter(x => x.length != 0)
    val input1: RDD[String] = input.filter(x => x.contains("-"))
    //val fmap: RDD[String] = input1.flatMap(_.split(" "))
    //val myMap: RDD[(String, Int)] = fmap.map(x => (x, 1));
    val strings: Array[String] = "avbb".split("b")
    val myMap: RDD[(String, Int)] = input1.map(x=>(x.split(",")(0),x.split(",")(1).toInt))
    val result: RDD[(String, Int)] = myMap.reduceByKey((x, y) => x + y);
    //val result = input.filter(x => x.length != 0).flatMap (s => s.split(" ")).map(x => (x, 1)).reduceByKey((x, y) => (x + y))
    result.foreach(println)
    result.saveAsTextFile("J:\\output")

    //result.saveAsTextFile("E:\\MyDownloads\\wc-output")
  }
}
