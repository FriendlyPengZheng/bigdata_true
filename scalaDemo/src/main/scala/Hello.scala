import java.util
import scala.util.control.Breaks._

object Hello {
  /**
    * 1.数据类型：AnyValue、AnyRef
    * 2.Nothing类是所有其他类的子类，Null是AnyRef的所有其他类的子类
    *
    */
  def main(args: Array[String]): Unit = {
    println("hello scala in idea")
    val a = 10 //修饰的变量不可变（不能 重新赋值），等价于Java中final
    var b = 20 //修饰的变量可以重新赋值
    b = 30
    val char1: Char = 'a';
    println(char1)
    val `c`: Float = 1.9.toFloat
    val `s`: String = "12.5"
    println(s.toFloat)
    println(c)
    for (a <- 0 to 10) {
      //包含首尾，闭区间
      //println(a)
    }
    val list = for (a <- 0 until 10; if a > 4) {
      //不包含尾，左闭右开
      println(a)
    }

    val li = for (a <- 0 until 10) yield {
      if (a > 4) a else 0
    }
    println(li)

    /**
      * 函数式编程，break
      * continue 可以用if过滤
      */
    breakable( for (i <- 0 to 10){
      println(i)
      if (i > 7)
        break()
    })
  }
}
