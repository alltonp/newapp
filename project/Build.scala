import sbt.Keys._
import sbt._

import scala.util.Try

object App extends Build {
  import Build._

  lazy val root = Project(id = "app", base = file("."), settings = standardBuildSettings)
}

object Build {
  val standardBuildSettings: Seq[Def.Setting[_]] = Defaults.defaultSettings ++ Seq[Setting[_]](
    mappings in (Compile, packageBin) ++= {
      val webapp: File = baseDirectory.value / "src/main/webapp"
      for ((from, to) <- (webapp ***) x rebase(webapp, "webapp")) yield (from, to)
    })
}