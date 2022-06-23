import sbt._
import sbt.Keys._

object deps {

  object versions {

    val catsCore        = "2.8.0"
    val scalaCheck      = "1.16.0"
    val munitDiscipline = "1.0.9"
    val munit           = "0.7.29"
    val spikeCE         = "3.3.0"

  }

  // Compile

  val cats = "org.typelevel" %% "cats-core" % versions.catsCore

  // Testing

  val munit           = "org.scalameta"  %% "munit"            % versions.munit
  val catsLaws        = "org.typelevel"  %% "cats-laws"        % versions.catsCore
  val catsEffect      = "org.typelevel"  %% "cats-effect"      % versions.spikeCE
  val scalaCheck      = "org.scalacheck" %% "scalacheck"       % versions.scalaCheck
  val munitDiscipline = "org.typelevel"  %% "discipline-munit" % versions.munitDiscipline

  def compileM(mids: ModuleID*): Seq[ModuleID] = mids.map(_ % Compile)
  def testM(mids: ModuleID*): Seq[ModuleID]    = mids.map(_ % Test)

}
