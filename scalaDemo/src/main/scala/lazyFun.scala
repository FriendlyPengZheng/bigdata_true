object lazyFun {
  def main(args: Array[String]): Unit = {
    lazy val res = fun(10, 20)
    println("-----------------")
    println(res)
  }

  def fun(a: Int, b: Int): Int = {
    println("执行a+b----—————")
    return a + b
  }

}
