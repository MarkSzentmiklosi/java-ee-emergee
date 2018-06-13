var service;
$(".servicesHref").click(getLocation);

function getLocation() {
    service = $(this).data("service");
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(callGoogleApi);
    }
}

function callGoogleApi(position) {
    let longitude = position.coords.longitude;
    let latitude = position.coords.latitude;
    let url = "https://maps.googleapis.com/maps/api/geocode/json?" +
        "latlng=" + latitude + "," + longitude +
        "&key=AIzaSyDiU243_BtwUd2m6j7I1FSHZwf4rhyAhY4";

    $.getJSON(url,handleJSON);
}

function handleJSON(json) {
    if(json["status"] == "OK"){
        let addressComponents = json["results"][0]['address_components'];
        let addressLabel = json["results"][0]["formatted_address"];
        let simplifiedComponents = harvestAddressComponents(addressComponents);

        simplifiedComponents["label"] = addressLabel;
        let service_type = {service};
        let address = simplifiedComponents;
        let data = {service_type,address};

        $.ajax({
            url: '/service',
            type: 'POST',
            data: JSON.stringify(data),
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            success: function () {
                alert("We notified the suitable partner.")
            }
        });

    }else{
        alert("Something went wrong. Please try again.")
    }

}

function harvestAddressComponents(addressComps) {
    let comps = {};
    for(var component in addressComps){
        let type = addressComps[component]["types"][0];
        let label = addressComps[component]["long_name"];
        comps[type] = label;
    }
    return comps;
}




