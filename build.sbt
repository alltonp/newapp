import scala.util.Try

name := "newapp"

organization := "im.mange"

version := Try(sys.env("TRAVIS_BUILD_NUMBER")).map("0.0." + _).getOrElse("1.0-SNAPSHOT")

scalaVersion:= "2.11.7"

//crossScalaVersions := Seq("2.10.4"/*, "2.11.0"*/)

resolvers ++= Seq(
  "Sonatype OSS Releases" at "http://oss.sonatype.org/content/repositories/releases/"
)

libraryDependencies ++= Seq(
    "io.shaka" %% "naive-http" % "48",
    "ch.qos.logback" % "logback-classic" % "1.0.6",
    "im.mange" %% "jetboot" % "0.0.121",
    "im.mange" %% "little-server" % "0.0.8",
    "net.liftmodules"   %% "lift-jquery-module_2.6" % "2.8",
    "net.liftweb" %% "lift-webkit" % "2.6.1"
    exclude("javax.mail", "mail")
    exclude("net.liftweb", "lift-markdown_2.11")
    //TODO: re-enable this soon
    //exclude("org.scala-lang", "scala-compiler")
)

homepage := Some(url("https://github.com/alltonp/newapp"))

licenses +=("Apache-2.0", url("http://www.apache.org/licenses/LICENSE-2.0.html"))

enablePlugins(JavaAppPackaging)

//heroku login
//heroku create newapp
//sbt stage deployHeroku
//https://newapp.herokuapp.com/
//heroku logs  --app newapp

herokuAppName in Compile := "newapp"