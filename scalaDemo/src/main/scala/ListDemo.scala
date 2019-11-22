import org.apache.parquet.schema.Types.ListBuilder

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

object ListDemo {
  def main(args: Array[String]): Unit = {
    /**
      * immutable.List
      */
    val l1: List[Int] = List(1,2,3,4)
    println(l1.mkString(","))
    //val l2 = l1.+:(100)
    //val l2 = l1.::(100,2)
    //val l2 = l1.++(List(2,3))
    //val l2 = l1.+("str")
    val l2 = l1.updated(0,20)
    //val l2 = l1.max
    //val l2 = l1.take(4)
    //println(l2)
    println(l2.mkString(","))
    /**
      * mutable.ListBuffer
      */
    val lb1 = ListBuffer(10,20,30,40)
    //lb1.insert(2,100)
    //lb1.update(4,0)
    //lb1.++=(List(1,2,3))
    //val ints: ListBuffer[Int] = lb1.++(List(10,20,30,4))
    println(lb1)

  }
}
