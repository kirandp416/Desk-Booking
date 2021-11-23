// For debugging purposes you can uncomment the code below. It will print whatever is in
// the form fields, whenever there is a change to the data in the fields.
// This debugger code was adapted from https://stackoverflow.com/questions/33697683/html-previo

// $("form :input").change(function () {
//     console.log("Form data changed.")
//     console.log($(this).closest('form').serialize());
// });


// Create a function that will set the date in the form to today's date

function setDateToToday() {
    let today = new Date();
    let todayFormatted = today.getFullYear() + '-' + (today.getMonth() + 1) + '-' + today.getDate();
    console.log("Setting date to today's date...")
    document.getElementById("bookingDate").value = todayFormatted;
}

// Set the date to today's date when we first load the page

$(document).ready(setDateToToday);

// If the user selects a date in the past, return the calendar to today's date
// and alert them

function pastDateWarn() {
    // console.log("Date changed");
    let bookingDate = new Date(document.getElementById("bookingDate").value);
    let today = new Date();
    let todayDate = new Date(today.getFullYear(), today.getMonth(), today.getDate(), 0, -today.getTimezoneOffset());

    if (bookingDate < todayDate) {
        alert('You cannot choose a date in the past.');
        setDateToToday();
    }
}

// The following code is an adaption of code in manage_desks.js, written by Hassan

// Get elements.
const resultsText = document.getElementById("resultsText");
const roomSelect = document.getElementById("room");
const dateSelect = document.getElementById("bookingDate");

// Table pagination state.
const limit = 10;
let offset = 0;
let totalResults = 0;

// Add event listener to table previous button.
document.getElementById("tablePreviousBtn").addEventListener("click", function () {
    // If the offset is 0, there are no more previous records.
    if (offset === 0) {
        return;
    }

    // Update offset and get desks.
    offset -= limit;
    getDesks();
});

// Add event listener to table next button.
document.getElementById("tableNextBtn").addEventListener("click", function () {
    // If offset + limit becomes greater or equal to total results, there are no more records.
    if (offset + limit >= totalResults) {
        return;
    }

    // Update offset and get desks.
    offset += limit;
    getDesks();
});

// Add click listener for form submit
document.forms["form"].addEventListener("submit", function (e) {
    // Prevent default behaviour of form.
    e.preventDefault();

    // Get desks.
    getDesks();
});

/**
 * Gets desks using AJAX.
 */
function getDesks() {
    let xhttp = new XMLHttpRequest();
    xhttp.open("GET", "/api/desks_available?room_id=" + roomSelect.value +
        "&date=" + dateSelect.value + "&offset=" + offset + "&limit=" + limit, true);
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

/**
 * Displays desks in the table.
 * @param json the JSON response.
 */
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

        // Create a cell in the last column in our table that
        // can hold a button
        let buttonCell = document.createElement("td");

        // If the desk is available on the selected date, render
        // a button in the buttonCell
        if (desk["available"] === true) {
            let btn = document.createElement("button");
            btn.innerHTML = "Book";
            btn.className = "btn btn-success"
            buttonCell.appendChild(btn);
        }

        row.append(id, name, available, buttonCell);

        table.appendChild(row);
    });
}
