object StringDemo {
  def main(args: Array[String]): Unit = {
    var s = " a b "
    s.trim
    val i: Int = 10
    val name: String = "张三"
    println("!" + s + "!")
    val $plus = "123"
    println($plus)
    /**
      * 数值类型 加f 可指定保留小数位
      * raw 表示不转义特殊字符，按原样输出
      * s 字符串变量拼接
      */
    println(f"name=$name,age=$i%.3f")
    print(raw"name=$name\n")
    println(s"name=$name")

  }
}
