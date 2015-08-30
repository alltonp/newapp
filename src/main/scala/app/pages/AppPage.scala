package app.pages

import app.comet.AppCometActor
import im.mange.jetpac.page.CometPage
import net.liftweb.sitemap.Loc

case class AppPage(override val path: String, override val params: Loc.LocParam[Any]*) extends CometPage[AppCometActor]
