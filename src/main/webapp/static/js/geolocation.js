var $btn = $("#geo_location_btn");
var street;
var city;
var zip;
var country;
var houseNum;
var label;


$btn.click(getLocation);

function getLocation() {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(sendPosition);
    }
}

function sendPosition(position) {
    const url = `https://reverse.geocoder.cit.api.here.com/6.2/reversegeocode.json?prox=${position.coords.latitude}%2C${position.coords.longitude}%2C250&mode=retrieveAddresses&maxresults=1&gen=8&app_id=Ta4Bs3skhxzoMXfIP4j8&app_code=y0khbGyyfAoVakL5lqjipg`

    $.getJSON(url, function (data) {
        label = data['Response']['View'][0]['Result'][0]['Location']['Address']['Label'];
        country = data['Response']['View'][0]['Result'][0]['Location']['Address']['Country'];
        city = data['Response']['View'][0]['Result'][0]['Location']['Address']['City'];
        zip = data['Response']['View'][0]['Result'][0]['Location']['Address']['postalCode'];
        street = data['Response']['View'][0]['Result'][0]['Location']['Address']['Street'];
        houseNum = data['Response']['View'][0]['Result'][0]['Location']['Address']['HouseNumber'];

        $("#loc").text("Are you here? \n" + label);
        $("#myModal").modal("show");

        $("#yesBtn").click(function () {
            $.post("/service", {
                label: label,
                country: country,
                city: city,
                zip: zip,
                houseNum: houseNum,
                street: street
            })
        });
    })
}