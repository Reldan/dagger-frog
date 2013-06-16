organization := "com.daggerfrog"

name := "daggerfrog"

version := "1.0"

scalaVersion := "2.10.2"

resolvers ++= Seq("spray" at "http://repo.spray.io/",
                  "sun" at "https://repository.jboss.org/nexus/content/repositories/public/",
                  "fakod-snapshots" at "https://raw.github.com/FaKod/fakod-mvn-repo/master/snapshots",
                  "fakod-releases" at "https://raw.github.com/FaKod/fakod-mvn-repo/master/releases"
                  )

libraryDependencies ++= Seq(
  "com.typesafe.akka" % "akka-actor_2.10" % "2.1.2",
  "com.typesafe.akka" % "akka-slf4j_2.10" % "2.1.2",
  "com.typesafe" % "config" % "1.0.0",
  "ch.qos.logback" % "logback-classic" % "1.0.9",
  "io.spray" % "spray-routing" % "1.1-M7",
  "io.spray" % "spray-can" % "1.1-M7",
  "io.spray" % "spray-http" % "1.1-M7",
  "io.spray" % "spray-caching" % "1.1-M7",
  "io.spray" % "spray-json_2.10" % "1.2.3",
  "org.apache.thrift" % "libthrift" % "0.9.0",
  "com.michaelpollmeier" % "gremlin-scala" % "2.3.0",
  "com.tinkerpop.blueprints" % "blueprints-neo4j-graph" % "2.3.0"
  )
