function initMap() {
    var map = new google.maps.Map(document.getElementById('map_canvas'), {
        zoom: 16,
        center: {lat: -34.397, lng: 150.644}
    });
    var geocoder = new google.maps.Geocoder();

    addEventListeners(geocoder, map);
}

function geocodeAddress(geocoder, resultsMap, address) {
    geocoder.geocode({'address': address}, function (results, status) {
        if (status === 'OK') {
            resultsMap.setCenter(results[0].geometry.location);
            var marker = new google.maps.Marker({
                map: resultsMap,
                position: results[0].geometry.location
            });
        } else {
            alert('Geocode was not successful for the following reason: ' + status);
        }
    });
}

function addEventListeners(geocoder, map) {
    let userRequests = document.getElementsByClassName("partner-page");
    for (var i = 0; i < userRequests.length; i++) {
        let address = document.getElementById("td" + i).innerText;
        userRequests.item(i).addEventListener('click', showMap(geocoder, map, address))
    }
}

function showMap(geocoder, map, address) {
    geocodeAddress(geocoder, map, address);
    $('#modal').modal({
        backdrop: 'static',
        keyboard: false
    }).on('shown.bs.modal', function () {
        google.maps.event.trigger(map, 'resize');
    });
}
