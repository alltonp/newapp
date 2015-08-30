package app.agent.root

import im.mange.jetboot.Bs._
import im.mange.jetpac._
import im.mange.jetpac.comet.Subscriber

case class Test(content: String)

case class RootAgent(subscriber: Subscriber) extends Renderable {
  private val holder = div(Some("comet"), R("hello")).styles(fontWeight(bold))

  subscriber ! Test("world")

  def render = div(
      containerFluid(
        row(col(12, holder))
      )
  ).render

  def onMessage(message: Any) = {
    println("RootAgent: " + message)
        val r = message match {
          case x:Test => {
            Thread.sleep(1000)
            subscriber ! Test(new java.util.Date().toString)
            holder.fill(R(x.content))
          }
          case x => println("Unexpected: " + x); Js.nothing
      }
      r
  }

  def showError(message: String) = Js.nothing
}
