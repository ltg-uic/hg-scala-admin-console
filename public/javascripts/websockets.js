/**
 * This takes care of handling Websockets
 */

var wsUri = "ws://ltg.evl.uic.edu/hg-admin/websockets";

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
	if (JSON.parse(evt.data).run_id==run_id)
		$('#debug_console').prepend(evt.data+"\n")
}

function sendMessage(json) {
	websocket.send(JSON.stringify(json));
}
