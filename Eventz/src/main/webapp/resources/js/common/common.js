function getGeoLocation() {
	if (navigator.geolocation) {
	    navigator.geolocation.getCurrentPosition(loadEvents, askForLocation);
	} else {
	    askForLocation();
	}
}

function loadEvents(geoLocation) {
	var url = "http://api.geonames.org/findNearbyPlaceNameJSON?lat="
			+ geoLocation.coords.latitude +"&lng="
			+ geoLocation.coords.longitude +"&username=adam&callback=?";
	$.getJSON(url, {}, function(cities) {
		if (cities.geonames.length > 0) {
			var city = cities.geonames[0];
			$(".city").val( city.name ); 
			$("#city-submit").removeClass("disabled").removeAttr("disabled");
		}
	});
}

function askForLocation() {
	
}

function toggleFilters(obj) {
	$(".filters-div").slideToggle();
}

function mapPosition(position) {
    //Creates a variable called point and sends the latitude and longitude to Google Maps to get your position
    var point = new google.maps.LatLng(position.latitude, position.longitude),
   
    //Settings for the map   
    myOptions = {
        zoom: 15,
        center: point,
        mapTypeId: google.maps.MapTypeId.ROADMAP
    },
   
    //Finding the div we want the map to be in
    mapDiv = document.getElementById("map"),
    //Pass in the div and map settings to the map
    map = new google.maps.Map(mapDiv, myOptions),
   
    //
    marker = new google.maps.Marker({
        position: point,
        map: map,
        title: "Venue"
    });
}

function enableSubmitButton(value) {
	if (value != '') {
		$("#city-manual-submit").removeAttr("disabled").removeClass("disabled");
	} else {
		$("#city-manual-submit").attr("disabled", "disabled").addClass("disabled");
	}
}

function toggleForms(initial) {
	$(".animate.form").toggle();
	$("#wrapper").toggleClass("extend");
	if (!initial)
		$(".errors").hide();
}