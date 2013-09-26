/**
 * Main JS file
 */

// Represents the current run_id
var run_id;


// Executed when the DOM is fully loaded using JQuery.ready
$(	function() {
	
	// Adds a listener to the drop down bout menu
	$('.dropdown-menu a').click( function () {
		if ($(this).text().toLowerCase() != run_id)
			$('#debug_console').empty()
	    run_id = $(this).text().toLowerCase()
		$('#current_run').text($(this).text())
	});
	
	// Setup callbacks for the command buttons
	$("#start_bout").click(function() {
		if (typeof run_id !== "undefined")
			sendMessage({ "run_id": run_id, "ltg_event": { "event": "start_bout", "payload": {} } })
	});
	$("#stop_bout").click(function() {
		if (typeof run_id !== "undefined")
			sendMessage({ "run_id": run_id, "ltg_event": { "event": "stop_bout", "payload": {} } })
	});
	$("#reset_bout").click(function() {
		if (typeof run_id !== "undefined")
			sendMessage({ "run_id": run_id, "ltg_event": { "event": "reset_bout", "payload": { "habitat_configuration_id": "gameon", "bout_id": "1" } } })
	});

	// Connect Websockets
	connectWebsockets()
});



	// /**********/
// 	/* MODELS */
// 	/**********/
// 	
// 	var BoutModel = Backbone.Model.extend({
// 		// Save the bout state locally		   
// 		localStorage: new Backbone.LocalStorage("bout-backbone"),
// 	});
// 	
// 	// The actual bout model instance
// 	var bout_model = new BoutModel;
// 	
// 	/*********/
// 	/* VIEWS */
// 	/*********/
// 	var BoutView = Backbone.View.extend({
// 	    // The DOM events specific to an item.
// 		events : {
// 		    "click .dropdown-menu a"       : "updateModel"
// 		},
// 
// 		updateModel : function(){
// 		    //display selected text
// 		    console.debug( $(this).text() );
// 		}
// 		
// 	});
// 	
// 	var bout_view = new BoutView(el:);
// 	


	// $("#pause_bout").click(function() {
// 		sendMessage({ "run_id": "5ag", "ltg_event": { "event": "pause_bout", "payload": {} } })
// 	});
// 	$("#resume_bout").click(function() {
// 		sendMessage({ "run_id": "5ag", "ltg_event": { "event": "resume_bout", "payload": {} } })
// 	});


