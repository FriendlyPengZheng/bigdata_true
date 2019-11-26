object flatMapDemo {
  def main(args: Array[String]): Unit = {
    val list = List("1", List(2, 3), 5, 6, List(7), List(8, 9, 10))

    /*    val value: List[Any] = list.flatMap {
          case x: List[Int] => x
          case x: Int => List(x)
        }
        println(value)*/

    val list2 = list.flatMap(x => {
      if (x.isInstanceOf[List[Int]]) {
        x.asInstanceOf[List[Int]]
      } else {
        List(x)
      }
    })

    println(list2)
    for (x <- list2) {
      println(x.getClass)
    }

  }
}