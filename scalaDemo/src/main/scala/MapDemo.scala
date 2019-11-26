import scala.collection.mutable

object MapDemo {

  def main(args: Array[String]): Unit = {
    val map: Map[String, Int] = Map("a"->1,"b"->2,"c"->3)

    val map1 = map.updated("a",10)
    //map("c") = 300
    println(map == map1)
    println(map.get("d").getOrElse("no such elem"))

    val s:String = "asdf"
    s.substring(3)

    val mmap = mutable.Map("a"->1,"b"->2)
    mmap.update("b",20)
    mmap.update("c",30)
    mmap("b") = 200
    println(mmap)
  }
}
