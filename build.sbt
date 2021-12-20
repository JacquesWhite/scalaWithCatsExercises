name := "ScalaWithCatsExcersises"
version := "0.0.1-SNAPSHOT"

scalaVersion := "2.13.7"

libraryDependencies += "org.typelevel" %% "cats-core" % "2.0.0"

// scalac options come from the sbt-tpolecat plugin so need to set any here

addCompilerPlugin(
  "org.typelevel" %% "kind-projector" % "0.13.2" cross CrossVersion.full
)
