package sparkRDD

import org.apache.spark.{SparkConf, SparkContext}

object aggregateDemo {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[*]").setAppName("aggregate test")
    val sc = new SparkContext(conf)
    val in = sc.parallelize(1 to 10)
    //in.glom().foreach(println(_))
    //val i = in.aggregate(2)((x1, x2) => x1 * x2, (x1, x2) => x1 + x2)
    val tuple = in.aggregate((0,0))((x,y)=>(x._1,x._2+1),(x,y)=>(x._1+y._1,x._2+y._2))
    println(tuple)

  }
}
