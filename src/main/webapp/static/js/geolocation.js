var $btn = $("#geo_location_btn");
var $locDiv = $("#location");

$btn.click(getLocation);

function getLocation() {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(sendPosition);
    }
}

function sendPosition(position) {
    $.post("/coordinates",{
        latitude:position.coords.latitude,
        longitude:position.coords.longitude
    },function () {

    })
}