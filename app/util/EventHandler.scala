/**
 *
 */
package util

import ltg.commons.ltg_handler.LTGEventHandler
import ltg.commons.ltg_handler.LTGEvent

/**
 * @author tebemis
 *
 */
object EventHandler {
  private val host : String = "ltg.evl.uic.edu"
  private val conference : String = "conference."+host;
  
  private val p1 = new LTGEventHandler("hg-bots#admin-console@"+host,"hg-bots#admin-console","5ag@"+conference);
  
  def sendEventTo(chatroom: String, event:LTGEvent) = p1.generateEvent(event);

}