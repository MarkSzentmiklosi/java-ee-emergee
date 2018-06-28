function initMap() {
    var map = new google.maps.Map(document.getElementById('map_canvas'), {
        zoom: 16,
        center: {lat: -34.397, lng: 150.644}
    });
    var geocoder = new google.maps.Geocoder();

    document.getElementById('launchMap').addEventListener('click', function () {
        geocodeAddress(geocoder, map);
        $('#modal').modal({
            backdrop: 'static',
            keyboard: false
        }).on('shown.bs.modal', function () {
            google.maps.event.trigger(map, 'resize');
            // map.setCenter(center);
        });
    });
}

function geocodeAddress(geocoder, resultsMap) {
    var address = document.getElementById('address').innerText;
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