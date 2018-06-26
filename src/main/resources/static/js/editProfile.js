let editField = document.getElementsByClassName('profileField');

for (var i = 0; i < editField.length ; i++) {
    editField.item(i).addEventListener('blur', sendRequest)
}

function handleError() {
    alert('An error occured while saving your data. Please try again');
}

function sendRequest () {
    let id = this.getAttribute("id");
    $.ajax({
        url: '/saveProfile',
        data: {dataType: id, data: this.textContent},
        type: 'POST',
        cache: false,
        error: handleError
    })
}