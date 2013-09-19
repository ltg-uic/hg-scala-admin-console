import play.api._
import util.EventHandler

object Global extends GlobalSettings {
  
  override def onStart(app: Application)  = EventHandler.init
  
  override def onStop(app: Application) = EventHandler.shutdown

}