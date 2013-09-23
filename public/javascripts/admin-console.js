/**
 * Main JS file
 */

$( document ).ready(function() {
	
	// Setup callbacks for buttons
	$("#start_bout").click(function() {
		sendMessage({ "run_id": "5ag", "ltg_event": { "event": "start_bout", "payload": {} } })
	});
	$("#stop_bout").click(function() {
		sendMessage({ "run_id": "5ag", "ltg_event": { "event": "stop_bout", "payload": {} } })
	});
	$("#reset_bout").click(function() {
		sendMessage({ "run_id": "5ag", "ltg_event": { "event": "reset_bout", "payload": { "habitat_configuration_id": "gameon", "bout_id": "1" } } })
	});
	$("#pause_bout").click(function() {
		sendMessage({ "run_id": "5ag", "ltg_event": { "event": "pause_bout", "payload": {} } })
	});
	$("#resume_bout").click(function() {
		sendMessage({ "run_id": "5ag", "ltg_event": { "event": "resume_bout", "payload": {} } })
	});

	// Connect Websockets
	connectWebsockets()
});