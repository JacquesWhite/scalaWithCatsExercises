package exercise2

import cats._
import cats.syntax.show._

object Main extends App {

  final case class Cat(name: String, age: Int, color: String)

  implicit val catShow: Show[Cat] =
    Show.show(cat => s"${cat.name} is ${cat.age} year-old ${cat.color} cat.")

  println(Cat("Jake", 21, "white").show)
}
