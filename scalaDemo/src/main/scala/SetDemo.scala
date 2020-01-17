import scala.collection.mutable

object SetDemo {
  def main(args: Array[String]): Unit = {

    /**
      *不可重复，默认不可变
      * immutable.Set
      */
    val set = mutable.Set(5,1,2,3,4,4,2)
    val set1 = set.+(100)
    set.+=(50)
    println(set.apply(6))
    println(set)
    println(set1)

    val i = 5
    val double: Double = i.toDouble
    println(double)
  }
}
