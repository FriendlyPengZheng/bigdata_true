object implicitDemo extends App {

  println("app..")

  //定义隐式转换规则
  implicit def DoubleToInt(d: Double): Int = {
    d.toInt
  }

  implicit def mysqlToMysql2(mysql: Mysql): Mysql2 = {
    new Mysql2
  }

  val i: Int = 5.0

  println(i)

  private val mysql = new Mysql
  mysql.printInfo(9.0)
  println(mysql.select())
  println(mysql.update())
}

class Mysql2 {
  def update(): String = {
    val student = new Student
    student.toString
  }
}

class Mysql {

  def select(): Student = {
    new Student
  }

  //定义隐式转换规则
  implicit def DoubleToInt(d: Double): Int = {
    d.toInt
  }

  def printInfo(i: Double): Unit = {
    println("i=" + i)
  }

}
