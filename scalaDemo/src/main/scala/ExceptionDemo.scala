object ExceptionDemo {
  def main(args: Array[String]): Unit = {
    fun()
    
  }

  def fun(): Unit = {
    try {
      throw new NullPointerException
    } catch {
      case e: NumberFormatException => {
        println("numberformatException")
      }
      case e: NullPointerException => {
        println("nullpointerException")
      }
    } finally {
      println("end")
    }
  }
}
