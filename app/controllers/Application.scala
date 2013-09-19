package controllers

import play.api._
import play.api.mvc._
import play.api.libs.iteratee.Iteratee
import play.api.libs.iteratee.Enumerator
import ltg.commons.ltg_handler.LTGEvent
import com.fasterxml.jackson.databind.node.JsonNodeFactory
import util.EventHandler
import ltg.commons.ltg_handler.LTGEventListener
import play.api.libs.json.JsValue
import ltg.commons.ltg_handler.LTGEventHandler
import play.api.libs.iteratee.Concurrent
import play.api.libs.json.JsObject
import play.api.libs.json.JsObject
import play.api.libs.json.JsObject
import play.api.libs.json.Json
import play.api.libs.json.JsString
import play.api.libs.json.JsString
import play.api.libs.json.JsString
import play.api.libs.json.JsResult
import org.codehaus.jackson.JsonFactory
import play.api.libs.json.JsString

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
	    val ltgEvent: LTGEvent = LTGEventHandler.deserializeEvent((json \ "ltg_event").toString)
	    EventHandler.handler.generateEvent(ltgEvent)
	  }
	  
	  //Out channel
	  val (out, channel) = Concurrent.broadcast[JsValue]
	  
	  // Register event handler 
	  EventHandler.handler.registerHandler("room", ".*", new LTGEventListener {
		def processEvent(e:LTGEvent) = {
		  channel.push(Json.parse(LTGEventHandler.serializeEvent(e)))
		}
	  })
	  
	  // Return
	  (in, out)
	}
}