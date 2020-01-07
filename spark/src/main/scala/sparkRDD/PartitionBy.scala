package sparkRDD

import org.apache.spark.rdd.RDD
import org.apache.spark.{HashPartitioner, SparkConf, SparkContext}

object PartitionBy {

    def main(args: Array[String]): Unit = {
        val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("partitionBy")
        val sc = new SparkContext(conf)

        val input: RDD[String] = sc.makeRDD(List("aa","bb","cc","dd"),1)
        println(input.getNumPartitions)
        println(input.partitioner.getOrElse());

        val partition: RDD[(String, Int)] = input.map((_,1)).partitionBy(new HashPartitioner(1314))
        println(partition.getNumPartitions)
        println(partition.partitioner.getOrElse(250))

        val pa: RDD[(String, Int)] = input.map((_,1)).partitionBy(new Pa(5))
        println(pa.getNumPartitions)
        pa.partitions.foreach(println(_))
        println(pa.partitioner.getOrElse(999))
    }
}
