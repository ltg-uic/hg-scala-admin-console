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

object Application extends Controller {

	// Index
	def index = Action {
		Ok(views.html.index())
	}

	
	// Websockets
	def websockets = WebSocket.using[String] { request =>
	  
	  //In channel
	  val in = Iteratee.foreach[String] { json =>
	    //EventHandler.handler.generateEvent("5ag", LTGEventHandler.deserializeEvent(json))
	    println(json)
	  }
	  
	  //Out channel
	  val (out, channel) = Concurrent.broadcast[String]
	  
	  // Register event handler 
	  EventHandler.handler.registerHandler(".*", new LTGEventListener {
		def processEvent(e:LTGEvent) = {
			channel.push(e.toString())
		}
	  })
	  
	  // Return
	  (in, out)
	}
}