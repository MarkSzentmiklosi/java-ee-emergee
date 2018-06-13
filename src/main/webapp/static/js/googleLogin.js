function onSignIn(googleUser) {
    var redirectUrl = 'glogin';
    //using jquery to post data dynamically
    var form = $('<form action="' + redirectUrl + '" method="post">' +
        '<input hidden="true" type="text" name="id_token" value="' +
        googleUser.getAuthResponse().id_token + '" />' +
        '</form>');
    $('body').append(form);
    form.submit();
}