package sparkRDD

import org.apache.spark.{SparkConf, SparkContext}

object aggregateDemo {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[*]").setAppName("aggregate test")
    val sc = new SparkContext(conf)
    val in = sc.parallelize(1 to 5)
    in.aggregate()()
    val i = in.aggregate(2)((x1, x2) => x1 * x2, (x1, x2) => x1 + x2)
    println(i)

  }
}
