package sparkRDD

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object AggregateByKey {
    def main(args: Array[String]): Unit = {
        val conf: SparkConf = new SparkConf().setAppName("aggre").setMaster("local[*]")
        val sc:SparkContext = new SparkContext(conf)
        val input: RDD[String] = sc.makeRDD(List("aa","bb","cc","bb","bb","bb","cc","dd","a"))
        /*val agg: (Int, Int) = input.aggregate(
            (0, 0))(
            (x, y) => (x._1 + y, x._2 + 1),
            (x, y) => (x._1 + y._1, x._2 + y._2))
        val aggbk: RDD[(Int, (Int, Int))] = input.map((_,1)).aggregateByKey(
            (0,0))(
            (x, y) => (x._1 + y, x._2 + 1),
            (x, y) => (x._1 + y._1, x._2 + y._2))*/
        //println(agg)
        val aggbk2: RDD[(String, Int)] = input.map((_,1)).aggregateByKey(
            0)(
            (x,y)=>(x+y),
            (x,y)=>(x+y))
        aggbk2.sortByKey().collect.foreach(println)
        println("-----------------------")

        /**
          * foldByKey是aggregateByKey的特例
          * seqOp和combOp一样的时候两个等价
          */
        val foldbk: RDD[(String, Int)] = input.map((_,1)).foldByKey(0)((x,y)=>(x+y))
        foldbk.persist
        foldbk.cache

        //aggbk.sortByKey().collect.foreach(println(_))
    }
}
