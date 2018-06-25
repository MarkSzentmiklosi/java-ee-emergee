function checkLoginData(email, password) {
    let userData = {};
    userData["email"] = email;
    userData["password"] = password;

    let isValid = false;

    $.ajax({
        url: "/check_user_login",
        type: "POST",
        data: userData,
        async: false,
        success: function (data) {
            if (data === "valid") {
                isValid = true;
            }
        }
    });

    return isValid;
}

function chechLoginForm() {
    let email = $("#email").val();
    let password = $("#password").val();

    let isLoginValid = checkLoginData(email, password);

    return isLoginValid;
}

function handleInvalidLogin() {
    alert("Cannot login.")
}

function setEventHandlers() {
    $(document).ready(function () {
            $("#submitLogin").on("click", function () {
                if (chechLoginForm()) {
                    $("#loginForm").submit()
                } else {
                    handleInvalidLogin();
                }
            })
        }
    );
}

function main() {
    setEventHandlers();
}

main();
