package sparkRDD

import org.apache.spark.{Accumulator, SparkConf, SparkContext}
import org.apache.spark.rdd.RDD
import org.apache.spark.util.LongAccumulator
import scala.tools.cmd.Reference.Accumulators

object CombineByKey {
    def main(args: Array[String]): Unit = {
        val student = Student("zhangsan",2)
        val conf: SparkConf = new SparkConf().setAppName("aggre").setMaster("local[*]")
        val sc: SparkContext = new SparkContext(conf)
        var i: Accumulator[Int] = sc.accumulator(10)
        val accumulator: LongAccumulator = sc.longAccumulator
        println(i.value)
        val input: RDD[String] = sc.makeRDD(List("aa", "bb", "cc", "bb", "bb", "bb", "cc", "dd", "a"),2)
        input.map (x=>
            x match {
                case "aa" => println("aa:"+i);(x+student.name, 10*student.age)
                case "bb" => i.add(1);println("bb:"+i);(x, 20)
                case "cc" => i.add(1);println("cc:"+i);(x, 30*i.toString().toInt)
                case "dd" => i.add(1);println("dd:"+i);(x, 40)
                case "a" => i.add(1);println("a:"+i);(x, 100)
                case _ => i.add(1);println("default:"+i);(x, 0)
            }
        ).combineByKey(
            /**
              * 对value进行转换操作，此时的“x”就是10、20、30...,最终转换成（1，10）、（1，20）...
              * 如果当前key是第一次出现则进行转换操作
              * 否则，执行合并操作（下一个函数）
              */
            x=>(1,x:Int),
            /**
              * 将当前的value和上一个函数转换的结构进行合并（分区内）
              *当前key已经出现过了
              * 若没有出现则进行上一个函数的创建操作
              */
            (x:(Int,Int),y:Int)=>(x._1+1,x._2+y),
            /**
              * 分区间合并
              */
            (x:(Int,Int),y:(Int,Int))=>(x._1+y._1,x._2+y._2)
        ).collect.foreach(println(_))
    }
}
