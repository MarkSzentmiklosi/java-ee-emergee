let editName = document.getElementById('name');
let editCountry = document.getElementById('country');
let editCity = document.getElementById('city');
let editZipCode = document.getElementById('zipCode');
let editStreet= document.getElementById('street');
let editHouseNum = document.getElementById('houseNum');
let editPhoneNum = document.getElementById('phoneNumber');
let editIdCardNum = document.getElementById('idCardNum');

editName.addEventListener('blur', function() {
    $.ajax({
        url: '/saveName',
        data: {name: editName.textContent},
        type: 'POST',
        cache: false,
        success: handleData,
        error: handleError
    })
});

editCountry.addEventListener('blur', function() {
    $.ajax({
        url: '/saveCountry',
        data: {country: editCountry.textContent},
        type: 'POST',
        cache: false,
        success: handleData,
        error: handleError
    })
});

editCity.addEventListener('blur', function() {
    $.ajax({
        url: '/saveCity',
        data: {city: editCity.textContent},
        type: 'POST',
        cache: false,
        success: handleData,
        error: handleError
    })
});

editZipCode.addEventListener('blur', function() {
    $.ajax({
        url: '/saveZipCode',
        data: {zipCode: editZipCode.textContent},
        type: 'POST',
        cache: false,
        success: handleData,
        error: handleError
    })
});

editStreet.addEventListener('blur', function() {
    $.ajax({
        url: '/saveStreet',
        data: {street: editStreet.textContent},
        type: 'POST',
        cache: false,
        success: handleData,
        error: handleError
    })
});

editHouseNum.addEventListener('blur', function() {
    $.ajax({
        url: '/saveHouseNum',
        data: {houseNum: editHouseNum.textContent},
        type: 'POST',
        cache: false,
        success: handleData,
        error: handleError
    })
});

editPhoneNum.addEventListener('blur', function() {
    $.ajax({
        url: '/savePhoneNumber',
        data: {phoneNumber: editPhoneNum.textContent},
        type: 'POST',
        cache: false,
        success: handleData,
        error: handleError
    })
});

editIdCardNum.addEventListener('blur', function() {
    $.ajax({
        url: '/saveIdCardNum',
        data: {idCardNum: editIdCardNum.textContent},
        type: 'POST',
        cache: false,
        success: handleData,
        error: handleError
    })
});


function handleData (data) {
    if (data === "success") {
        alert('Update successful!');
    }
}

function handleError() {
    alert('An error occured while saving your data. Please try again');
}