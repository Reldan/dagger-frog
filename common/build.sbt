organization := "com.daggerfrog"

name := "daggerfrog-common"

version := "0.1"

scalaVersion := "2.10.2"

resolvers ++= Seq(
  "Typesafe Releases" at "http://repo.typesafe.com/typesafe/releases/",
  "Spray" at "http://repo.spray.io/",
  "Sun" at "https://repository.jboss.org/nexus/content/repositories/public/"
)

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.2.0-RC1",
  "com.typesafe.akka" %% "akka-slf4j" % "2.2.0-RC1",
  "com.typesafe" % "config" % "1.0.0",
  "ch.qos.logback" % "logback-classic" % "1.0.10" % "runtime",
  "io.spray" % "spray-can" % "1.2-M8",
  "io.spray" % "spray-client" % "1.2-M8",
  "io.spray" % "spray-http" % "1.2-M8",
  "io.spray" % "spray-routing" % "1.2-M8",
  "io.spray" %% "spray-json" % "1.2.3",
  "org.apache.thrift" % "libthrift" % "0.9.0",
  "com.github.sstone" % "amqp-client_2.10" % "1.1",
  "org.squeryl" %% "squeryl" % "0.9.5-6",
  "postgresql" % "postgresql" % "8.4-701.jdbc4"
)
