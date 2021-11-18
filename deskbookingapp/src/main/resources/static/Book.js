// For debugging purposes you can uncomment the code below. It will print whatever is in
// the form fields, whenever there is a change to the data in the fields.
// This debugger code was adapted from https://stackoverflow.com/questions/33697683/html-previous-date-validation

// $("form :input").change(function () {
//     console.log($(this).closest('form').serialize());
// });

// Show an alert if the user ever selects a date from in the past:

function pastDateWarn() {
    console.log("Date changed");
    var bookingDate = new Date(document.getElementById("bookingDate").value);
    var today = new Date();
    var todayDate = new Date(today.getFullYear(), today.getMonth(), today.getDate(), 0, -today.getTimezoneOffset());

    if (bookingDate < todayDate) {
        alert('You cannot choose a date in the past.');
    }
}

// Stop the form submission for data we do not want passing into our server:

function validateForm() {

    var bookingDate = document.getElementById("bookingDate").value;

    // If the user submits an empty date, return false

    if (bookingDate.length == 0) {
        alert('You must select a date!');
        return false;
    }

    // If the user submits a past date (despite the warning), return false

    var bookingDate = new Date(document.getElementById("bookingDate").value);
    var today = new Date();
    var todayDate = new Date(today.getFullYear(), today.getMonth(), today.getDate(), 0, -today.getTimezoneOffset());

    if (bookingDate < todayDate) {
        alert('You cannot choose a date in the past.');
        return false;
    }

}
