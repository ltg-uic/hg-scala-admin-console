package controllers

import ltg.commons.ltg_event_handler.LTGEvent
import play.api._
import play.api.libs.iteratee.Concurrent
import play.api.libs.iteratee.Enumerator
import play.api.libs.iteratee.Iteratee
import play.api.libs.json.JsResult
import play.api.libs.json.JsValue
import play.api.mvc._
import util.EventHandler
import ltg.commons.ltg_event_handler.MultiChatLTGEventListener
import play.api.libs.json.Json

object Application extends Controller {

	// Index
	def index = Action {
		Ok(views.html.index())
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