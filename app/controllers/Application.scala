package controllers

import play.api._
import play.api.mvc._
import play.api.libs.iteratee.Iteratee
import play.api.libs.iteratee.Enumerator

object Application extends Controller {

	// Index
	def index = Action {
		Ok(views.html.index())
	}

	// Websockets
	def websockets = WebSocket.using[String] { request =>
	  
	  val in = Iteratee.foreach[String](println).mapDone { _ =>
	    println("Disconnected")
	  }

	  val out = Enumerator("Hello!")
	  
	  (in, out)

	}

}