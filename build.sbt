import deps._

Global / onChangedBuildSource := ReloadOnSourceChanges
Global / lintUnusedKeysOnLoad := false

lazy val Scala2 = "2.13.10"
lazy val Scala3 = "3.2.0"

lazy val root = project
  .in(file("."))
  .enablePlugins(NoPublishPlugin)
  .aggregate(unum)

lazy val unum = project
  .in(file("unum"))
  .enablePlugins(AutomateHeaderPlugin)
  .settings(
    name := "unum",
    libraryDependencies ++=
      compileM(cats) ++ testM(munit, catsLaws, scalaCheck, munitDiscipline)
  )

inThisBuild(
  List(
    scalaVersion := Scala3,
    crossScalaVersions := Seq(Scala3, Scala2),
    tlJdkRelease := Some(8),
    tlBaseVersion := "1.1",
    tlSonatypeUseLegacyHost := false,
    mergifyStewardConfig ~= { _.map(_.copy(mergeMinors = true).copy(author = "scala-steward-ahjohannessen[bot]")) },
    organization := "io.github.ahjohannessen",
    organizationName := "Alex Henning Johannessen",
    startYear := Some(2022),
    developers += tlGitHubDev("ahjohannessen", "Alex Henning Johannessen"),
    shellPrompt := Prompt.enrichedShellPrompt,

    // ==== Github Actions

    githubWorkflowTargetBranches := Seq("main"),
    githubWorkflowJavaVersions := Seq(JavaSpec.temurin("17"))
  )
)
