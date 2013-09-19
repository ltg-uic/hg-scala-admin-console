/**
 *
 */
package util

import ltg.commons.ltg_handler.LTGEventHandler
import ltg.commons.ltg_handler.LTGEvent
import ltg.commons.ltg_handler.LTGEventListener
import com.typesafe.config.Config
import com.typesafe.config.ConfigFactory
import play.Configuration
import play.api.Play
import java.util.List

/**
 * @author tebemis
 *
 */
object EventHandler {
  
  var eh: LTGEventHandler = null;
  
  def init() { 
    eh = new LTGEventHandler(
      Play.current.configuration.getString("xmpp.username").get,
      Play.current.configuration.getString("xmpp.password").get,
      Play.current.configuration.getStringList("xmpp.chatrooms").get
      );
  }
  
  def shutdown() = eh.close
  
}