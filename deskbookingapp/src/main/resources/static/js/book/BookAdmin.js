/**
 * Create a method that returns a date string that is formatted in such
 * a way that it can be passed to the html date input and it will load
 * a date into that. In other words, the string can be used to reset
 * the html calendar interface that comes as part of inputs of type
 * date.
 * @returns {string}
 */
function todaysDateReturner() {
    let today = new Date();
    return today.getFullYear() + '-' + (today.getMonth() + 1) + '-' + String(today.getDate()).padStart(2, '0');
}

/**
 * Set the date to today's date when we first load the page
 */
$(document).ready(setDateToToday);

/**
 * Create a function that we can use when we need to set the date back to today's date
 * e.g. if an invalid date is supplied
 */
function setDateToToday() {
    let todayFormattedString = todaysDateReturner();
    console.log("Setting date to today's date...")
    document.getElementById("bookingDate").value = todayFormattedString;
}

/**
 * Create a function that will clears the current date if and when
 * we call it.
 */
function dateClearer() {
    let date_input = document.getElementById("bookingDate");

    //erase the input value
    date_input.value = '';

    //prevent error on older browsers (aka IE8)
    if (date_input.type === 'date') {
        //update the input content (visually)
        date_input.type = 'text';
        date_input.type = 'date';
    }
}

/**
 * If the user selects a date in the past, clear the calendar
 */
function pastDateWarn() {

    // If a change to the date form is heard by the event listener in the form,
    // create a date object that is formatted in such a way that we can compare
    // it the date they just clicked on

    let bookingDate = new Date(document.getElementById("bookingDate").value);
    let today = new Date();
    let todayDate = new Date(today.getFullYear(), today.getMonth(), today.getDate(), 0, -today.getTimezoneOffset());

    // Compare the date they just put in to todays' date. If the date they
    // put in was before today, tell them and then reset the date to today's
    // date.

    if (bookingDate < todayDate) {
        alert('You cannot choose a date in the past.');
        dateClearer();
    }
}

/**
 * Create function that checks that the user has selected a date
 * @returns {boolean}
 */
function viewDesksButtonDataValidator() {

    if (document.getElementById("bookingDate").value.length == 0) {
        console.log("You pressed view desks but there is no date");
        return false;
    } else
        return true;
}

// Start of code adapted from HI's manage_desks.js

// Get elements.
const quotaText = document.getElementById("quotaText");
const resultsText = document.getElementById("resultsText");
const roomSelect = document.getElementById("room");
const dateSelect = document.getElementById("bookingDate");
const usernameSelect = document.getElementById("username");

// Table pagination state.
const limit = 10;
let offset = 0;
let totalResults = 0;

// Quota state.
let quota = null;

// Add event listener to table previous button.
document.getElementById("tablePreviousBtn").addEventListener("click", function () {
    // If the offset is 0, there are no more previous records.
    if (offset === 0) {
        return;
    }

    // Update offset and get desks.
    offset -= limit;
    getDesksForAdmin();
});

// Add event listener to table next button.
document.getElementById("tableNextBtn").addEventListener("click", function () {
    // If offset + limit becomes greater or equal to total results, there are no more records.
    if (offset + limit >= totalResults) {
        return;
    }

    // Update offset and get desks.
    offset += limit;
    getDesksForAdmin();
});

// Add click listener for form submit
document.forms["form"].addEventListener("submit", function (e) {
    // Prevent default behaviour of form.
    e.preventDefault();


    // If the form is validated, load DOM with desks. Otherwise,
    // let them know the error. Since there is only one place the
    // form can currently go wrong and that is for an empty date
    // input, we alert them to an empty date.
    if (viewDesksButtonDataValidator() == true) {
        // Fetch data.
        getDesksForAdmin();
    } else {
        alert('You must select a date!');
    }


});

// End of code adapted from HI's manage_desks.js

/**
 * Gets desks (with their availability) using AJAX.
 */
function getDesksForAdmin() {

    let xhttp = new XMLHttpRequest();


    xhttp.open("GET", "/api/desks_available_admin?" +
        "room_id=" + roomSelect.value +
        "&date=" + dateSelect.value +
        "&offset=" + offset +
        "&limit=" + limit, true);

    xhttp.onreadystatechange = function () {
        if (xhttp.readyState === 4) {
            if (xhttp.status === 200) {
                // Parse JSON.
                const json = JSON.parse(xhttp.responseText);

                // Store total results count.
                totalResults = json["total_results"];

                // Display desks.
                displayDesks(json);

                // Update results text in table.
                if (limit + offset > totalResults) {
                    resultsText.innerText = "Displaying " + totalResults + " of " + totalResults + " results";
                } else {
                    resultsText.innerText = "Displaying " + (limit + offset) + " of " + totalResults + " results";
                }



            } else {
                // Redirect to error page.
                window.location.replace("/internal_server_error");
            }
        }
    }

    xhttp.send();
}

function displayDesks(json) {
    // Get table body.
    let table = document.getElementsByTagName("tbody")[0];

    // Remove all child elements.
    table.innerHTML = "";

    // Create rows for each desk.
    json["results"].forEach(function (desk) {

        let row = document.createElement("tr");

        let id = document.createElement("td");
        id.innerText = desk["id"];

        let name = document.createElement("td");
        name.innerText = desk["name"];

        let type = document.createElement("td");
        type.innerText = desk["desk_type"]["name"];

        let notes = document.createElement("td");
        notes.innerText = desk["notes"];

        let available = document.createElement("td");
        available.innerText = desk["available"];

        let bookedBy = document.createElement("td");
        bookedBy.innerText = desk["booked_by"];

        let bookingId = document.createElement("td");
        bookingId.innerText = desk["booking_id"];

        // let availabilityCell = availabilityCellConfigurer(desk);

        // let buttonCell = buttonCellConfigurer(desk);

        row.append(id, name, type, notes, available, bookedBy, bookingId);

        table.appendChild(row);
    });

}
