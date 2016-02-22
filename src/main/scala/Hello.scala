package cinsk

import java.io.File
import java.io._

import java.util.Properties
import scopt._

case class OptionConfig(out: File = new File("."),
                        verbose: Boolean = false,
                        version: Boolean = false,
                        debug: Boolean = false,
                        properties: String = "",
                        args: Seq[String] = Seq(),
                        kwargs: Map[String, String] = Map())

class Klass(file: Option[File]) {
  println("C CTOR")

  def lift[T](x: => T): Option[T] = try { Some(x) }
                                    catch { case _: Throwable => println("exception!"); None }


  val props = file match {
      case Some(n) => {
        val is = lift(new BufferedInputStream(new FileInputStream(n)))
        val p = new Properties
        is.map { x =>
          println("loading...")
          p.load(x)
        }
        p
      }
      case _ => new Properties
    }

  def this() { this(None) }

  def size = props.size
}



object Hello {
  val versionString = BuildInfo.version
  val programName = BuildInfo.name

  def showVersionAndExit {
    println(s"$programName version $versionString")
    System.exit(0)
  }

  def liftFilter[A](a: => A)(f: A => Boolean) =
    try {
      val v = a
      if (f(v)) Some(v)
      else None
    }
    catch {
      case _: Throwable => None
    }

  def main(args: Array[String]) {
    val optParser = new scopt.OptionParser[OptionConfig](programName) {
        head("Short description of the program")
        help("help") text("display this help and exit")
        opt[Unit]("verbose") action { (_, c) =>
          c.copy(verbose = true) } text("verbose is a flag")
        opt[Unit]("version") action { (_, c) =>
          showVersionAndExit
          c.copy(version = true)
        } text("output version information and exit")
        opt[String]('P', "properties") required() valueName("<file>") action {
          (x, c) => c.copy(properties = x) } text("properties file")
        arg[String]("ARG...") unbounded() optional() action { (x, c) =>
          c.copy(args = c.args :+ x) } text("Optional unbounded args")
        note("")
      }

    val c: OptionConfig = optParser.parse(args, OptionConfig()).orNull
    if (c == null) {
      System.err.println("parse error")
      System.exit(1)
    }

    if (c.verbose)
      println("verbose on")

    for (a <- c.args)
      println(s"arg: $a")

    val k = new Klass { liftFilter(c.properties) { _.length > 0 } }

    //propFile.map(x => println("OPTION!"))
    //val k = new Klass(propFile)
    println("size of klass: " + k.size)
    println("args: " + c.args)
  }
}

