object FunDemo {

  def main(args: Array[String]): Unit = {
    //recursion(6)
    /*println(cal(3, 4))
    println(cal1(4, 5))
    println(cal2(5, 6))*/
    println(fun1(p2 = "jerry"))
  }

  def sayHello() = {
    println("hello")
  }

  def recursion(n: Int) {
    if (n > 2) {
      recursion(n - 1)
    }
    println("n=" + n)
  }

  def cal(a: Int, b: Int) {
    //无返回值，return不会生效,会返回()
    return a + b
  }

  def cal1(a: Int, b: Int) = {
    //随返回类型推导
    println("a.getClass=" + a.getClass)
    if (a.getClass.toString == "int") {
      println("coming*****")
      a + b
    } else {
      "String"
    }
  }

  def cal2(a: Int, b: Int): Int = {
    return a + b
  }

  /**
    * 多个形参有默认参数的，可以传参数时指定形参名，不然不知道参数具体是传递给哪一个参数
    * eg: fun1(p2 = "jerry")
    * @param p1
    * @param p2
    * @return String
    */
  def fun1(p1: String = "jack", p2: String = "lili"): String = {
    return p1 + p2
  }
}
