package exercise1

object Main extends App {

  // Let’s define an “application” now that uses the library.
  // First we’ll define a data type to represent a well-known type of furry animal:
  final case class Cat(name: String, age: Int, color: String)

  // Next we’ll create an implementation of Printable for Cat that returns content in the following format:
  // NAME is a AGE year-old COLOR cat.
  object Cat {
    import PrintableInstances._
    implicit val printableCat = new Printable[Cat] {
      def format(cat: Cat): String = {
        val name = Printable.format(cat.name)
        val age = Printable.format(cat.age)
        val color = Printable.format(cat.color)
        s"$name is a $age year-old $color cat."
      }
    }
  }

  // Finally, use the type class on the console or in a short demo app: create a Cat and print it to the console:
  // Define a cat:
  val cat = Cat("Jake", 21, "white")

  // Print the cat!
  Printable.print(cat)

  // Use the extension methods to print the example Cat you created in the previous exercise.
  import PrintableSyntax._
  cat.print
}
