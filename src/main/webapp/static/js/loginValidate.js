function checkLoginData(email, password) {
    let userData = {};
    userData["email"] = email;
    userData["password"] = password;

    $.ajax({
        url: "/check_login_details",
        type: "POST",
        data: userData,
        success: function (data) {
            if (data === "valid") {
                return true;
            } else {
                alert("Cannot login!")
            }
        }
    });

    return false;
}

function chechLoginForm() {
    let email = $("#email").val();
    let password = $("#password").val();

    let isLoginValid = checkLoginData(email, password);

    $("#loginForm").submit(function () {
        return isLoginValid;
    })
}

function setEventHandlers() {
    $(document).ready(function () {
            $("#submitLogin").on("click", function () {
                chechLoginForm();
            })
        }
    );
}

function main() {
    setEventHandlers();
}

main();
