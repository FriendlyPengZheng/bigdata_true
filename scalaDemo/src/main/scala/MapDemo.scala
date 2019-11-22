import scala.collection.mutable

object MapDemo {

  def main(args: Array[String]): Unit = {
    val map: Map[String, Int] = Map("a"->1,"b"->2,"c"->3)

    println(map)
    println(map.get("d").getOrElse("no such elem"))

  }
}
