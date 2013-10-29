/**
 * Main JS file
 */

// Represents the current run_id
var run_id;

// Simulator variables
var departure;
var arrival;
var tag;

// Executed when the DOM is fully loaded using JQuery.ready
$(	function() {
	
	// Adds a listener to the drop down bout menu
	$('#run_id_dropdown a').click( function () {
		if ($(this).text().toLowerCase() != run_id)
			$('#debug_console').empty()
	    run_id = $(this).text().toLowerCase()
		$('#run_id_a').html($(this).text()+'<b class="caret"></b>')
	});
	// Adds a listener to the departure and arrivals dropdowns
	$('#departure_dropdown a').click( function () {
	    departure = $(this).text()
		$('#departure_btn').html( $(this).text() + ' <span class="caret"></span>')
	});
	$('#arrival_dropdown a').click( function () {
	    arrival = $(this).text()
		$('#arrival_btn').html( $(this).text() + ' <span class="caret"></span>')
	});
	
	// Setup callbacks for the command buttons
	$("#start_bout").click(function() {
		if (typeof run_id !== "undefined") {
			var current_habitat = $("input[name='habitat_sel']:checked").val()
			var current_bout = $('#bout_id').val()
			if (typeof current_habitat !== "undefined" && typeof current_bout !== "undefined")
				sendMessage({ "run_id": run_id, "ltg_event": { "event": "start_bout", "payload": { "habitat_configuration_id": current_habitat, "bout_id": current_bout } } })
		}
	});
	$("#stop_bout").click(function() {
		if (typeof run_id !== "undefined")
			var current_habitat = $("input[name='habitat_sel']:checked").val()
			var current_bout = $('#bout_id').val()
			if (typeof current_habitat !== "undefined" && typeof current_bout !== "undefined")
				sendMessage({ "run_id": run_id, "ltg_event": { "event": "stop_bout", "payload": { "habitat_configuration_id": current_habitat, "bout_id": current_bout } } })
	});
	$("#reset_bout").click(function() {
		if (typeof run_id !== "undefined")
			var current_habitat = $("input[name='habitat_sel']:checked").val()
			var current_bout = $('#bout_id').val()
			if (typeof current_habitat !== "undefined" && typeof current_bout !== "undefined")
			sendMessage({ "run_id": run_id, "ltg_event": { "event": "reset_bout", "payload": {  "habitat_configuration_id": current_habitat, "bout_id": current_bout  } } })
	});
	$("#move_btn").click(function() {
		tag =  $('#tag_id').val()
		if (typeof run_id !== "undefined" && typeof departure !== "undefined" && typeof arrival !== "undefined" && typeof tag !== "undefined") {
			if (departure != arrival) {
				sendMessage({ "run_id": run_id, "ltg_event": { "event": "rfid_update", "payload": { "id": tag, "departure": departure, "arrival": arrival } } })
			}
		}
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


