var stompClient = null;
var partnerId;

function connect() {
    var socket = new SockJS('/emergee');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/observeRequest', function (helpRequest) {
            let parsedHelpRequest = JSON.parse(helpRequest.body);
            if (partnerId === parsedHelpRequest['partner_id']) {
                displayRequest(parsedHelpRequest);
            }
        });
    });
}

function displayRequest(parsedHelpRequest) {
    $(".requests")
        .append(
            "<div class=\"single-products-catagory clearfix \">" +
            "<a class=\"partner-page\" href=\"#\">" +
            "<div class=\"img-container\">" +
            "<img src=\"/img/simple.png\" alt=\"\">" +
            "</div>" +
            "<div class=\"hover-content\">" +
            "<div class=\"line\"></div>" +
            "<h4>" + parsedHelpRequest['request_location'] + "</h4>" +
            "<h6>" + parsedHelpRequest['creationDate'] + "</h6>" +
            "<h6>" + parsedHelpRequest['user_name'] + "</h6>" +
            "<h6>" + parsedHelpRequest['user_phone_number'] + "</h6>" +
            "</div>" +
            "</a>" +
            "</div>"
        );
}

$(document).ready(function () {
    connect();
    getPartnerId();
});

function getPartnerId() {
    $.ajax({
        url: '/partner_session_id',
        type: 'POST',
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        success: function (response) {
            partnerId = response.partner_id;
        }
    });
}