name := "detect"
version := "0.0.1"

scalaVersion := "3.1.2"

scalacOptions := Seq(
  "-unchecked",
  "-deprecation",
  "-encoding", "utf8"
)

libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.12"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.12" % "test"