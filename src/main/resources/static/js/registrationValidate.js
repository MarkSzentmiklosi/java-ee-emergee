let password = document.forms['registrationForm']['registration_password'];
let password_confirm = document.forms['registrationForm']['repeat_password'];
let password_error = document.getElementById('password_error');
let email_error = document.getElementById("email_error");
let submitButton = document.getElementById("sign-up");
let emailInput = document.getElementById("registration_email");
let registration = true;
password.addEventListener('input', passwordMatch, true);
password_confirm.addEventListener('input', passwordMatch);
emailInput.addEventListener('input', checkEmail);

function passwordMatch() {
    if (password.value !== password_confirm.value) {
        submitButton.setAttribute("disabled", "true");
        password_error.innerHTML = "Passwords do not match";
    } else if (password.value === password_confirm.value) {
        password_error.innerHTML = "";
    }
    if (registration) {
        submitButton.removeAttribute("disabled");
    }
}

function checkEmail() {
    let email = $(this).val();
    let ExistingEmail = false;
    $.ajax({
        url: "/check_registration_email",
        type: "POST",
        data: {email: email},
        async: false,
        dataType: "text",
        success: function (data) {
            if (data === "existingEmail") {
                alertUser();

            } else if (data === "newEmail") {
                removeAlert();
            }
        },
        error: function (data) {
            alert("An error occured. Please try again." + data)
        }
    });
}

function alertUser() {
    submitButton.setAttribute("disabled", "true");
    email_error.innerHTML = "This e-mail address is already registered";
    registration = false;
}

function removeAlert() {
    submitButton.removeAttribute("disabled");
    email_error.innerHTML = "";
    registration = true;
}