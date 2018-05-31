$("#loginForm").submit(function () {
    var $inputs = $("#loginForm :input");
    var values = {};
    $inputs.each(function () {
        values[this.name] = $(this).val();
    })
    debugger;
});