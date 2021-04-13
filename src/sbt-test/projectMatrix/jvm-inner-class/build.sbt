import sbt.{Def, VirtualAxis}
import sbtprojectmatrix.ProjectMatrixPlugin.autoImport.virtualAxes

lazy val scala212 = "2.12.12"
lazy val scala213 = "2.13.3"

val Play27 = PlayAxis.Value("2.7.4")
val Play28 = PlayAxis.Value("2.8.1")

val Akka25 = AkkaAxis.Value("2.5.23")
val Akka26 = AkkaAxis.Value("2.6.8")

lazy val akka = projectMatrix
  .customRow(
    scalaVersions = Seq(scala212),
    axisValues = Seq(Akka25, VirtualAxis.jvm),
    settings = Nil
  )
  .customRow(
    scalaVersions = Seq(scala212, scala213),
    axisValues = Seq(Akka26, VirtualAxis.jvm),
    settings = Nil
  )
  .settings(
    // e.g. libraryDependencies += "com.typesafe.akka" %% "akka-actor" % AkkaAxis.current.value.version,
    moduleName := AkkaAxis.current.value.nameComponent,
  )

lazy val play = projectMatrix
  .dependsOn(akka)
  .customRow(
    scalaVersions = Seq(scala212, scala213),
    axisValues = Seq(Play27, Akka25, VirtualAxis.jvm),
    settings = Nil
  )
  .customRow(
    scalaVersions = Seq(scala213),
    axisValues = Seq(Play28, Akka26, VirtualAxis.jvm),
    settings = Nil
  )
  .settings(
    // e.g. libraryDependencies += "com.typesafe.play" %% "play" % PlayAxis.current.value.version,
    moduleName := PlayAxis.current.value.nameComponent,
  )
