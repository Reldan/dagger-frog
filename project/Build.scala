import sbt._
import Keys._


object DaggerFrogBuild extends Build {

  lazy val root     = Project("root",      file(".")) aggregate(common, frontApi)
  lazy val common   = Project("common",    file("common"))
  lazy val frontApi = Project("front-api", file("front/api")) dependsOn(common)
}
