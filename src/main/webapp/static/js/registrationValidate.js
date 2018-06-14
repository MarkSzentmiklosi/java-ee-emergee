let password = document.forms['registrationForm']['registration_password'];
let password_confirm = document.forms['registrationForm']['repeat_password'];
let password_error = document.getElementById('password_error');
let submitButton = document.getElementById("sign-up");
password.addEventListener('input', passwordMatch, true);
password_confirm.addEventListener('input', passwordMatch);

function passwordMatch() {
    if (password.value != password_confirm.value) {
        submitButton.setAttribute("disabled", "true");
        password_error.innerHTML = "Passwords do not match";
    } else {
        submitButton.removeAttribute("disabled");
        password_error.innerHTML = "";
    }

}