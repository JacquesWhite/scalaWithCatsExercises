package exercise5

import cats.instances.double._
import cats.instances.int._
import cats.instances.option._
import cats.syntax.option._
import cats.syntax.semigroup._
import cats.Monoid

object Main extends App {

  /* The cutting edge SuperAdder v3.5a-32 is the world’s first choice for adding together numbers.
   * The main function in the program has signature def add(items: List[Int]): Int. In a tragic
   * accident this code is deleted! Rewrite the method and save the day! */
  def add(items: List[Int]): Int = {
    items.foldLeft(Monoid[Int].empty)(_ |+| _)
  }

  /* Well done! SuperAdder’s market share continues to grow, and now there is demand for additional
   * functionality. People now want to add List[Option[Int]]. Change add so this is possible. The
   * SuperAdder code base is of the highest quality, so make sure there is no code duplication! */
  def add[A](items: List[A])(implicit monoid: Monoid[A]): A =
    items.foldLeft(monoid.empty)(_ |+| _)

  val list: List[Option[Int]] = List(1.some, 2.some, none[Int])

  println(add(list))

  /* SuperAdder is entering the POS (point-of-sale, not the other POS) market. Now we want to add up
   * Orders: */
  case class Order(totalCost: Double, quantity: Double)

  // We need to release this code really soon so we can’t make any modifications to add. Make it so!
  implicit val addOrdersMonoid: Monoid[Order] =
    new Monoid[Order] {
      def combine(a: Order, b: Order) =
        Order(
          totalCost = a.totalCost |+| b.totalCost,
          quantity = a.quantity |+| b.quantity,
        )
      def empty = Order(totalCost = 0, quantity = 0)
    }

  println(add(List(Order(0.20, 5).some, Order(0.22, 5).some, none[Order])))
}
