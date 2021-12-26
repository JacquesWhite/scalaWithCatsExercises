package exercise6

import cats.syntax.functor._
import cats.Functor

object Main extends App {
  /* Write a Functor for the following binary tree data type. Verify that the code works as expected
   * on instances of Branch and Leaf: */
  sealed trait Tree[+A]

  final case class Branch[A](left: Tree[A], right: Tree[A]) extends Tree[A]

  final case class Leaf[A](value: A) extends Tree[A]

  // solution
  implicit val treeFunctior: Functor[Tree] =
    new Functor[Tree] {
      def map[A, B](tree: Tree[A])(f: A => B): Tree[B] =
        tree match {
          case Branch(left, right) => Branch(left.map(f), right.map(f))
          case Leaf(value)         => Leaf(f(value))
        }
    }

  val oak: Tree[Int] /* we have to explicitelu tell that this is tree */ = Branch(
    Branch(
      Leaf(0),
      Leaf(1),
    ),
    Branch(
      Leaf(2),
      Branch(
        Leaf(3),
        Leaf(4),
      ),
    ),
  )
  /* also we could do this here */
  println(oak /*.asInstanceOf[Tree[Int]]*/.map(_ + 1))

  object Tree {
    def branch[A](left: Tree[A], right: Tree[A]): Tree[A] =
      Branch(left, right)

    def leaf[A](value: A): Tree[A] =
      Leaf(value)
  }

  val pine = Tree.branch(
    Tree.branch(
      Tree.leaf(0),
      Tree.leaf(1),
    ),
    Tree.branch(
      Tree.leaf(2),
      Tree.branch(
        Tree.leaf(3),
        Tree.leaf(4),
      ),
    ),
  )

  println(pine.map(_ * 2))

}
