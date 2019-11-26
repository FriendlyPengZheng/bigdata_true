object CollFunDemo {
  def main(args: Array[String]): Unit = {
    val s: String = "abcdef"
    //println(s.substring(0))
    //println(s.substring(2, 4))

    /**
      * scala 实现WordCount
      */
    val list = List("hadoop java", "hadoop hive", "hadoop spark", "shell scala java")
    //val flatMap = list.flatMap(x => x.split(" "))
    val flatMap = list.flatMap(_.split(" "))
    println(flatMap)
    val map = flatMap.map((_,1))
    println(map)
    val grpb = map.groupBy(_._1)
    println(grpb)
    val mpv = grpb.mapValues(_.size)
    println(mpv)


  }
}
