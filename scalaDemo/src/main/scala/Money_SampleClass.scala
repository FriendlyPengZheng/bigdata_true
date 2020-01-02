case class Money_SampleClass(num: Int, name: String) {

}

object Money_SampleClass {
    def main(args: Array[String]): Unit = {
        val money = Money_SampleClass(100, "RMB")

        money match {
            case Money_SampleClass(num, "aa") => print(num, "RMB")
            case Money_SampleClass(num, name) => print(num, name)
            case _ => print("no match")
        }
    }
}
