
name := "hello"

version := "0.1"

scalaVersion := "2.11.7"

mainClass in (Compile, run) := Some("cinsk.hello.Hello")

// libraryDependencies += "org.apache.httpcomponents" % "httpclient" % "4.3.2"
// libraryDependencies += "junit" % "junit" % "4.11"
// libraryDependencies += "org.scala-tools.testing" % "specs_2.10" % "1.6.9"

// See https://github.com/scopt/scopt
libraryDependencies += "com.github.scopt" %% "scopt" % "3.3.0"

libraryDependencies += "org.scala-tools.testing" % "specs_2.10" % "1.6.9"

resolvers += Resolver.sonatypeRepo("public")


javacOptions ++= Seq("-source", "1.8", "-target", "1.8")

scalacOptions ++= Seq("-target:jvm-1.8", "-deprecation")

// See https://github.com/sbt/sbt-buildinfo ---------------
buildInfoSettings

sourceGenerators in Compile <+= buildInfo


buildInfoKeys := Seq[BuildInfoKey](name, version, scalaVersion, sbtVersion)

buildInfoPackage := "cinsk.hello"

compileOrder := CompileOrder.JavaThenScala

// Java then Scala for main sources
//compileOrder in Compile := CompileOrder.JavaThenScala
// allow circular dependencies for test sources
//compileOrder in Test := CompileOrder.Mixed

// scalacOptions += "-deprecation"

// To prevent sbt.TrapExitSecurityException raised while '--help' is used,
// use following statement.
fork in (run) := true
