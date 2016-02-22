package cinsk

import java.lang.management.ManagementFactory

import scala.io.StdIn

object Benchmark {
  def pause(prompt: String) {
    val pid = ManagementFactory.getRuntimeMXBean().getName.split("@")(0);
    StdIn.readLine(s"benchmark[$pid] $prompt> ");
  }
  def pause {
    pause("press any key to continue")
  }

  def time(proc: => Unit) = timeWithThrow(proc)._1

  def timeWithThrow(proc: => Unit) = {
    val s = System.currentTimeMillis
    var e: Option[Exception] = None
    try {
      proc
    }
    catch {
      case ex: Exception => e = Some(ex)
    }
    val time = System.currentTimeMillis - s
    (time, e)
  }
}
