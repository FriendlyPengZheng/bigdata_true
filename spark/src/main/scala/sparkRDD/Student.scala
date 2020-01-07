package sparkRDD

import java.io.Serializable

object Student{
    def apply(name:String,age:Int): Student ={
        new Student(name,age)
    }
}

class Student(var name: String,var  age: Int) extends Serializable{
    def name(name: String): String = {
        this.name
    }

    def age(age: Int): Int = {
        this.age
    }

    def setName(name:String){
        this.name=name
    }
}
