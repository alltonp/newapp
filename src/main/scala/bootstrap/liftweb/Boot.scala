package bootstrap.liftweb

import app.pages._
import im.mange.jetpac.page.Pages
import net.liftmodules.JQueryModule
import net.liftweb.common.{Full, Loggable}
import net.liftweb.http._
import net.liftweb.sitemap.Loc.{If, LocGroup}
import net.liftweb.util.NamedPF
import net.liftweb.util.TimeHelpers._

class Boot extends Loggable {
  def boot { doOrDie(unsafeBoot) }

  def doOrDie(f: () => Unit) = try { f() } catch { case t: Throwable => logger.error("Fatal", t); System.exit(1) }

  val topBar = LocGroup("topBar")

  def unsafeBoot() {
    val protectedPages = Seq(
      AppPage("index", topBar)
    )

    Pages(
      protectedPages: _*
    )

    LiftRules.sessionInactivityTimeout.default.set(Full(30.minutes: Long))
    LiftRules.addToPackages("app")
    LiftRules.early.append(_.setCharacterEncoding("UTF-8"))
    LiftRules.htmlProperties.default.set((r: Req) => new Html5Properties(r.userAgent))

    LiftRules.uriNotFound.prepend(NamedPF("404handler") {
      case (req, failure) ⇒ NotFoundAsTemplate(ParsePath(List("404"), "html", false, false))
    })

    JQueryModule.InitParam.JQuery=JQueryModule.JQuery211
  }
}
