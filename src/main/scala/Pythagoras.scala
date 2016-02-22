package cinsk

object Pythagoras {
  // a * b / c
  val stifelTriples: Stream[(Int, Int, Int)] = stifelTriple(1, 1, 3)

  def stifelNextInput(a: Int, b: Int, c:Int) = (a + 1, b + 1, c + 2)

  def stifelTriple(a: Int, b: Int, c:Int): Stream[(Int, Int, Int)] = {
    val large = a * c + b;
    (c, large, large + 1) #:: (stifelTriple _).tupled(stifelNextInput(a, b, c))
  }

  def ozanamTriples: Stream[(Int, Int, Int)] = ozanamTriple(1)

  def ozanamNextInput(n: Int) = (n, 4 * n + 3, 4 * n + 4)

  def ozanamTriple(n: Int): Stream[(Int, Int, Int)] = {
    val (a, b, c) = ozanamNextInput(n)
    (c, a * c + b, a * c + b + 2) #:: ozanamTriple(n + 1)
  }
}
