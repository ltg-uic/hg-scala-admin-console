import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "hg-scala-admin-console"
  val appVersion      = "0.2.0"

  val appDependencies = Seq(
      // LTG Event Handler
      "ltg" % "ltg-java-event-handler" % "2.0.0-beta1"
  )


  val main = play.Project(appName, appVersion, appDependencies).settings(
      //Adding local Maven repository
      //resolvers += "Local Maven Repository" at "file://"+Path.userHome.absolutePath+"/.m2/repository"
	  resolvers += "LTG Artifactory Repository" at "http://ltg.evl.uic.edu/artifactory/repo"
  )

}
