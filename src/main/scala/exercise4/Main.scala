package exercise4

object Main extends App {
  /* We’ve seen a few examples of monoids but there are plenty more to be found. Consider Boolean.
   * How many monoids can you define for this type? For each monoid, define the combine and empty
   * operations and convince yourself that the monoid laws hold. Use the following definitions as a
   * starting point: */
  trait Semigroup[A] {
    def combine(x: A, y: A): A
  }

  trait Monoid[A] extends Semigroup[A] {
    def empty: A
  }

  object Monoid {
    def apply[A](implicit monoid: Monoid[A]) =
      monoid
  }

  // this is ez. also we can define monoid for "or with false", "xor with false" etc.
  val conjunction: Monoid[Boolean] = new Monoid[Boolean] {
    def combine(x: Boolean, y: Boolean) = x && y
    def empty                           = true
  }

  // What monoids and semigroups are there for sets?
  implicit def setAdd[A]: Monoid[Set[A]] =
    new Monoid[Set[A]] {
      def combine(a: Set[A], b: Set[A]) = a ++ b
      def empty                         = Set.empty[A]
    }
  // let's move on to the cats monoids
}
