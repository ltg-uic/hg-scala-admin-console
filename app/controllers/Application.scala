package controllers

import play.api._
import play.api.mvc._
import play.api.libs.iteratee.Iteratee
import play.api.libs.iteratee.Enumerator
import ltg.commons.ltg_handler.LTGEvent
import com.fasterxml.jackson.databind.node.JsonNodeFactory
import util.EventHandler

object Application extends Controller {

	// Index
	def index = Action {
		Ok(views.html.index())
	}

	// Websockets
	def websockets = WebSocket.using[String] { request =>
	  
	  val e  = new LTGEvent("random_event", null, null, JsonNodeFactory.instance.objectNode() )
	  
	  val in = Iteratee.foreach[String] { msg =>
	    EventHandler.sendEventTo("5ag", e)
	    println(msg)
	  }

	  val out = Enumerator("Hello!")
	  
	  (in, out)

	}

}