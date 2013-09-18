/**
 * Main JS file
 */

$( document ).ready(function() {
	// Setup callbacks for buttons
	$("#start_bout").click(function() {
		sendMessage('{ "event": "start_bout", "run_id": "5ag" }')
	});
	$("#stop_bout").click(function() {
		sendMessage('{ "event": "stop_bout", "run_id": "5ag" }')
	});
	$("#reset_bout").click(function() {
		sendMessage('{ "event": "reset_bout", "run_id": "5ag", "habitat_configuration_id": "gameon", "bout_id": "1"  }')
	});
	$("#pause_bout").click(function() {
		sendMessage('{ "event": "pause_bout", "run_id": "5ag" }')
	});
	$("#resume_bout").click(function() {
		sendMessage('{ "event": "resume_bout", "run_id": "5ag" }')
	});

	connectWebsockets()
});