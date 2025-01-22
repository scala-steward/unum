import sbt._
import sbt.Keys._

object deps {

  object versions {

    val catsCore        = "2.13.0"
    val scalaCheck      = "1.18.1"
    val munitDiscipline = "2.0.0"
    val munit           = "1.1.0"

  }

  // Compile

  val cats = "org.typelevel" %% "cats-core" % versions.catsCore

  // Testing

  val munit           = "org.scalameta"  %% "munit"            % versions.munit
  val catsLaws        = "org.typelevel"  %% "cats-laws"        % versions.catsCore
  val scalaCheck      = "org.scalacheck" %% "scalacheck"       % versions.scalaCheck
  val munitDiscipline = "org.typelevel"  %% "discipline-munit" % versions.munitDiscipline

  def compileM(mids: ModuleID*): Seq[ModuleID] = mids.map(_ % Compile)
  def testM(mids: ModuleID*): Seq[ModuleID]    = mids.map(_ % Test)

}
