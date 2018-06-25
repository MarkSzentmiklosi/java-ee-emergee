function disableButton() {
    $pressedButton = $(this);
    $pressedButton.prop('disabled',true);
    setTimeout(function () {
        $pressedButton.prop('disabled',false);
    }, 10000);
}


function main() {
$(".servicesHref").click(disableButton);
}

main();