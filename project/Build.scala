import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "hg-scala-admin-console"
  val appVersion      = "0.1.0"

  val appDependencies = Seq(
      // LTG Event Handler
      "ltg" % "ltg-java-event-handler" % "1.5"
  )


  val main = play.Project(appName, appVersion, appDependencies).settings(
      //Adding local Maven repository
      resolvers += "Local Maven Repository" at "file://"+Path.userHome.absolutePath+"/.m2/repository"
  )

}
