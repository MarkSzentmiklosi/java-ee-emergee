var stompClient = null;

function connect() {
    var socket = new SockJS('/emergee');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/observeRequest', function (helpRequest) {
            displayRequest(JSON.parse(helpRequest.body));
        });
    });
}

function displayRequest(helpRequest) {
    $(".requests").append("<tr><td>" + helpRequest['partner_id'] + "</td></tr>" +
        "<tr><td>" + helpRequest['user_name'] + "</td></tr>");
}

$(document).ready(function () {
    connect();
});