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
            "<tr><td>" + parsedHelpRequest['partner_id'] + "</td></tr>" +
            "<tr><td>" + parsedHelpRequest['user_name'] + "</td></tr>" +
            "<tr><td>" + parsedHelpRequest['request_location'] + "</td></tr>"
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