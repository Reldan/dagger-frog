organization := "com.daggerfrog"

name := "daggerfrog"

version := "0.1"

scalaVersion := "2.10.2"

scalacOptions ++= Seq(
  "-language:postfixOps",
  "-language:implicitConversions",
  "-feature",
  "-deprecation",
  "-Yinline-warnings",
  "-optimise",
  "-encoding", "utf8"
)
