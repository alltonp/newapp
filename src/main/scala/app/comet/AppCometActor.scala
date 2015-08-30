package app.comet

import app.agent.root.{RootAgent, Test}
import im.mange.jetpac.comet._
import net.liftweb.common.Loggable
import net.liftweb.http.ResponseShortcutException

class AppCometActor extends RefreshableCometActor with MessageCapturingCometActor with Subscriber with Loggable {
  override def onCapturedMessage(message: Any) {}

  private var rootAgent: RootAgent = _

  def beforeRefresh() {}

  def doRefresh() {
    rootAgent = new RootAgent(this)
  }

  def afterRefresh() {}

  def doRender = rootAgent.render

  override def localShutdown() {
    super.localShutdown()
  }

  override def onMessage = handleMessage :: super.onMessage

  private def handleMessage: PartialFunction[Any, Unit] = {
    case r:Test => partialUpdate(rootAgent.onMessage(r))
    case e => logger.error(s"${getClass.getSimpleName}: unexpected message received: $e")
  }

  override def exceptionHandler: PartialFunction[Throwable, Unit] = {
    case x:ResponseShortcutException => super.exceptionHandler(x)
    case x =>
      logger.error("oops", x)
      showError("Oops, something went wrong")
  }

  //TODO: need to have a think about this ...
  private def showError(message: String) = if (rootAgent != null) partialUpdate(rootAgent.showError(message))
}