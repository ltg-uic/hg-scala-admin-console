/**
 * This takes care of handling Websockets
 */

var wsUri = "ws://localhost:9000/websockets";

function connectWebsockets() {
	// Setup websockets
	websocket = new WebSocket(wsUri);

	websocket.onopen = function(e) {
		console.debug("Websocket connected")
	};
	websocket.onclose = function(e) {
		console.debug("Websocket disconnected")
	};
	websocket.onmessage = function(e) {
		onMessageReceived(e)
	};
	websocket.onerror = function(evt) {
		console.error(e.data)
	};
}


function onMessageReceived(evt) {
	console.debug(evt.data)
}

function sendMessage(json) {
	websocket.send(JSON.stringify(json));
}
