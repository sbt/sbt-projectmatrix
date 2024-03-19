ThisBuild / version := "0.1.0-SNAPSHOT"

lazy val util = projectMatrix
  .jvmPlatform(scalaVersions = Seq("2.11.12", "3.3.3"))

lazy val root = (projectMatrix in file("."))
  .dependsOn(util)
  .jvmPlatform(scalaVersions = Seq("2.11.12"))

// ss is second system
lazy val ss = projectMatrix
  .dependsOn(util)
  .jvmPlatform(scalaVersions = Seq("3.3.3"))

lazy val strayJar = project
