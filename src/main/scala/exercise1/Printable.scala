package exercise1

// Scala provides a toString method to let us convert any value to a String. However, this method
// comes with a few disadvantages: it is implemented for every type in the language,
// many implementations are of limited use, and we can’t opt-in to specific implementations for specific types.
// Let’s define a Printable type class to work around these problems:
//   1. Define a type class Printable[A] containing a single method format. format should accept a value of type A and return a String.
//   2. Create an object PrintableInstances containing instances of Printable for String and Int.
//   3. Define an object Printable with two generic interface methods:
//     3.1 format accepts a value of type A and a Printable of the corresponding type. It uses the relevant Printable to convert the A to a String.
//     3.2 print accepts the same parameters as format and returns Unit. It prints the formatted A value to the console using println.
trait Printable[A] {
  def format(value: A): String
}

object PrintableInstances {
  implicit val printableString: Printable[String] =
    new Printable[String] {
      def format(value: String): String = value
    }

  implicit val printableInt: Printable[Int] =
    new Printable[Int] {
      def format(value: Int): String = value.toString
    }
}

object Printable {
  def format[A](value: A)(implicit printableInstance: Printable[A]): String = {
    printableInstance.format(value)
  }

  def print[A](value: A)(implicit printableInstance: Printable[A]): Unit = {
    println(format(value))
  }
}
//  The code above forms a general purpose printing library that we can use in multiple applications.
// Let’s make our printing library easier to use by defining some extension methods to provide better syntax:
// 1. Create an object called PrintableSyntax.
// 2. Inside PrintableSyntax define an implicit class PrintableOps[A] to wrap up a value of type A.
// 3. In PrintableOps define the following methods:
//  3.1 format accepts an implicit Printable[A] and returns a String representation of the wrapped A;
//  3.2 print accepts an implicit Printable[A] and returns Unit. It prints the wrapped A to the console.

object PrintableSyntax {
  implicit class PrintableOps[A](value: A) {
    def format(implicit printable: Printable[A]): String = {
      printable.format(value)
    }

    def print(implicit printable: Printable[A]): Unit = {
      println(format(printable))
    }
  }
}
