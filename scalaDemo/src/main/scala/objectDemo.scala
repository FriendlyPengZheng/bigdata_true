object objectDemo {


  def main(args: Array[String]): Unit = {
    val person = new Person
    person.name = "zhangsan"
    person.introduce()
  }

  class Person {
    var name: String = _
    var age: Int = _

    def introduce(): Unit = {
      println(s"name=$name,age=$age")
    }
  }
}