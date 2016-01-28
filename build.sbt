
name := "hello"

version := "1.0"

scalaVersion := "2.11.7"

mainClass in (Compile, run) := Some("Hello")

libraryDependencies += "org.apache.httpcomponents" % "httpclient" % "4.3.2"

libraryDependencies += "junit" % "junit" % "4.11"

libraryDependencies += "org.scala-tools.testing" % "specs_2.10" % "1.6.9"

javacOptions ++= Seq("-source", "1.8", "-target", "1.8")
scalacOptions ++= Seq("-target:jvm-1.8", "-deprecation")

compileOrder := CompileOrder.JavaThenScala

// Java then Scala for main sources
//compileOrder in Compile := CompileOrder.JavaThenScala
// allow circular dependencies for test sources
//compileOrder in Test := CompileOrder.Mixed
