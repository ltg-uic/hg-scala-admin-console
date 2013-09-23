/**
 *
 */
package util

import java.util.List

import ltg.commons.ltg_event_handler.MultiChatLTGEventHandler
import play.Configuration
import play.api.Play

/**
 * @author tebemis
 *
 */
object EventHandler {
  
  var handler: MultiChatLTGEventHandler = null;
  
  def init() { 
    handler = new MultiChatLTGEventHandler(
      Play.current.configuration.getString("xmpp.username").get,
      Play.current.configuration.getString("xmpp.password").get,
      Play.current.configuration.getStringList("xmpp.chatrooms").get
      );
    handler.runAsynchronously;
  }
  
  def shutdown() = handler.shutdown
  
}