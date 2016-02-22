package cinsk

import scala.annotation.tailrec
import Benchmark._

object Hello {
  def main(args: Array[String]): Unit = {
    pause

    println(s"p1: ${p1}");
    println(s"p2: ${p2}");

    val mineTime = time {
      println(Prime.primes.takeWhile(_ <= 10000).last)
    }

    pause("Press any key to quit")
  }

  def p1() = {
    Range(1, 1000).flatMap(x => {
      if (x % 3 == 0 || x % 5 == 0)
        List(x)
      else
        None;
    }).reduceLeft((a, b) => a + b)
  }

  def p2() = {
    @tailrec
    def fibo(first: Long, second: Long, sum: Long): Long = {
      if (second >= 4000000)
        sum;
      else {
        val next = first + second;
        fibo(second, next, if (next % 2 == 0) sum + next else sum);
      }
    }
  }

  def p3() = {
    val input = 600851475143L;
    def larestPrimeFactor(i: Long) = {
      Range(2, math.sqrt(i).toInt).filter(Prime.is(_)).filter(i % _ == 0).last
    }
  }

  def fibos(n: Int, indent: Int = 0): (BigInt, BigInt) = {
    println(s"${"  " * indent}fibo($n)")
      n match {
        case 1 => (1, 0)
        case _ =>
          val (fn, fn_1) = fibos(n / 2, indent + 1)
          val l = (2 * fn_1 + fn) * fn
          val r = fn * fn + fn_1 * fn_1
          if (n % 2 == 0)
            (l, r)
          else
            (l + r, l)
      }
    }

}

