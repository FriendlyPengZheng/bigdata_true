object caseDemo {

  val range = 1 to 10
  val l = range.toList
  val strings = List("aa","bb","cc")

  def main(args: Array[String]): Unit = {
    fun2(l)
  }

  def fun(i: Int) {
    i match {
      case 1 => println(i + "a1")
      case 2 => println(i + "a2")
      case other => println(other)
    }
  }

  def fun1(list: List[Int]): Unit = {
    list.foreach(x=>println(x+1))
    /**
      * list.foreach(x => println(x))
      * list.foreach(println(_))
      */
  }

  def fun2(list: List[Int]): Unit ={
    val ints = list.map(x=>x+"abc")
    ints.foreach(println)
  }

/*  def fun2(list: List[String]): Unit ={
    val ints = list.map(x=>x+"abc")
    ints.foreach(println)
  }*/
}