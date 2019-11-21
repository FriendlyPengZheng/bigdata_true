import scala.collection.mutable.ArrayBuffer
//import scala.collection.immutable.List

object arrayDemo {
  def main(args: Array[String]): Unit = {
    val arr = Array(1,2,3,4)
    val arr_c: Array[Int] = arr:+100
    println(arr == arr_c)
    arr.update(0,100)
    arr.foreach(println(_))
    val ints: List[Int] = List(1, 2, 3, 4)

    val ints1: List[Int] = ints :+ 5
    val str = ints1.+("ssss")
    str.foreach(print)
    ints1.foreach(println)
    val arrb = ArrayBuffer(1, 2, 3, 4, 5)
    /*
      ints.toBuffer
      arrb.toArray
    */
    arrb.+("abc").foreach(print)
    println(arrb.update(4, 100).getClass)
    println(arrb.getClass)
    println(Unit.getClass)
    arrb.insert(3,200)
    arrb.insert(6,300)
    //arrb.insert(8,400)
    arrb.foreach(println(_))
  }
}
