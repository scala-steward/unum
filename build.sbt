import deps._

Global / onChangedBuildSource := ReloadOnSourceChanges
Global / lintUnusedKeysOnLoad := false

lazy val Scala2 = "2.13.8"
lazy val Scala3 = "3.1.1"

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
      compileM(cats) ++ testM(munit, catsLaws, scalaCheck, munitDiscipline),
    scalacOptions ++= {
      if (tlIsScala3.value) Seq("-Xtarget:8") else Seq("-target:8")
    }
  )

inThisBuild(
  List(
    scalaVersion := Scala3,
    crossScalaVersions := Seq(Scala3, Scala2),
    versionScheme := Some("semver-spec"),
    tlBaseVersion := "1.0",
    tlSonatypeUseLegacyHost := false,
    javacOptions ++= Seq("-target", "8", "-source", "8"),
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
