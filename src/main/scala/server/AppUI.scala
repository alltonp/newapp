package server

import im.mange.little.LittleServer

object AppUI extends App {
  private val providedPort = System.getProperty("http.port")
  private val port = if (providedPort == null) 4998 else providedPort.toInt

  new LittleServer(port)
  println(s"### Starting on $port")
}
