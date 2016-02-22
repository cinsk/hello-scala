package cinsk

object Prime {
  // See Jed Wesley-Smith's answer in
  // http://stackoverflow.com/questions/9711785/find-prime-numbers-using-scala-help-me-to-improve

  def is(i: Long) =
    if (i == 2) true
    else if ((i & 1) == 0) false // efficient div by 2
    else prime(i)

  def primes: Stream[Long] = 2 #:: prime3

  private val prime3: Stream[Long] = {
    @annotation.tailrec
    def nextPrime(i: Long): Long =
      if (prime(i)) i else nextPrime(i + 2) // tail

    def next(i: Long): Stream[Long] = {
      i #:: next(nextPrime(i + 2))
    }

    3 #:: next(5)

  }

  // assumes not even, check evenness before calling - perf note: must
  // pass partially applied >= method
  def prime(i: Long) =
    prime3 takeWhile (math.sqrt(i).>= _) forall { i % _ != 0 }
}
