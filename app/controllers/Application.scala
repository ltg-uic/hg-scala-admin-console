package controllers

import ltg.commons.ltg_event_handler.LTGEvent
import ltg.commons.ltg_event_handler.MultiChatLTGEventListener
import play.api.Play
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.iteratee.Concurrent
import play.api.libs.iteratee.Iteratee
import play.api.libs.json.JsValue
import play.api.libs.json.Json
import play.api.mvc.Action
import play.api.mvc.Controller
import play.api.mvc.WebSocket
import util.EventHandler

object Application extends Controller {

	// Index
	def index = Action {
		Ok(views.html.index(Play.current.configuration.getStringList("xmpp.chatrooms").get))
	}

	
	// Websockets
	def websockets = WebSocket.using[JsValue] { request =>
	  
	  //In channel
	  val in = Iteratee.foreach[JsValue] { json =>
	    val runId: String = (json \ "run_id").validate[String].get
	    val gc = runId + Play.current.configuration.getString("xmpp.chatservice").get
	    val ltgEvent: LTGEvent = LTGEvent.deserializeEvent((json \ "ltg_event").toString)
	    EventHandler.handler.generateEvent(gc, ltgEvent)
	  }
	  
	  //Out channel
	  val (out, channel) = Concurrent.broadcast[JsValue]
	  
	  // Register event handler 
	  EventHandler.handler.registerHandler(".*", new MultiChatLTGEventListener {
		def processEvent(chatroom: String, e:LTGEvent) = {
		  val json_out = """{ "run_id": """"+ chatroom.split("@")(0) + """", "ltg_event": """+ LTGEvent.serializeEvent(e) + """}"""
		  channel.push(Json.parse(json_out))
		}
	  })
	  
	  // Return
	  (in, out)
	}
}