function disableButton($pressedButton) {
    $pressedButton.css("pointer-events", "none");
    setTimeout(function () {
        $pressedButton.css("pointer-events", "auto");
    }, 20000);
}


function main() {
    $('.servicesHref').on('touchend click', function (event) {
        event.stopPropagation();
        event.preventDefault();
        if (touchmoved != true) {
            disableButton($(this));
        }
    }).on('touchmove', function () {
        touchmoved = true;
    }).on('touchstart', function () {
        touchmoved = false;
    });
}

main();