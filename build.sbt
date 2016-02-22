
name := "hello"

version := "0.1"

scalaVersion := "2.11.7"

mainClass in (Compile, run) := Some("cinsk.Hello")

// libraryDependencies += "org.apache.httpcomponents" % "httpclient" % "4.3.2"
// libraryDependencies += "junit" % "junit" % "4.11"
// libraryDependencies += "org.scala-tools.testing" % "specs_2.10" % "1.6.9"

// See https://github.com/scopt/scopt
libraryDependencies += "com.github.scopt" %% "scopt" % "3.3.0"

resolvers += Resolver.sonatypeRepo("public")

// See https://github.com/sbt/sbt-buildinfo ---------------
buildInfoSettings

sourceGenerators in Compile <+= buildInfo

buildInfoKeys := Seq[BuildInfoKey](name, version, scalaVersion, sbtVersion)

buildInfoPackage := "cinsk"

// scalacOptions += "-deprecation"

// javaOptions in run += "-javaagent:lib/classmexer.jar"


// See http://www.scala-sbt.org/0.13/docs/Forking.html
// To prevent sbt.TrapExitSecurityException raised while '--help' is used,
// use following statement.
fork in (run) := true

// STDIN will be connected to sbt(1)
connectInput in run := true

// The output will be connected to sbt(1)'s stdout
outputStrategy := Some(StdoutOutput)

