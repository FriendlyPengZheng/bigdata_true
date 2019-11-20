object Student {
  def main(args: Array[String]): Unit = {
    val student: Person = new Student
    student.test()
  }
}


abstract class Person {

  var name: String = _
  val sex: String = "nv"

  def test()
}

class Student extends Person {

  //子类只能重写父类的不可变变量
  override val sex = "nan"

  def test(): Unit = {
    name = "abc"
    println(sex)
  }
}
