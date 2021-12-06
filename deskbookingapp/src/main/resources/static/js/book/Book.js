// For debugging purposes you can uncomment the code below. It will print whatever is in
// the form fields, whenever there is a change to the data in the fields.
// This debugger code was adapted from https://stackoverflow.com/questions/33697683/html-previo

// $("form :input").change(function () {
//     console.log("Form data changed.")
//     console.log($(this).closest('form').serialize());
// });

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
function reset_date_native() {
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
        reset_date_native();
    }
}

function viewDesksButtonDataValidator() {

    if (document.getElementById("bookingDate").value.length == 0) {
        console.log("You pressed view desks but there is no date");
        return false;
    } else
        return true;
}

// Start of code that was adapted from Hassan's code in manage_desks.js

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
    fetchData();
});

// Add event listener to table next button.
document.getElementById("tableNextBtn").addEventListener("click", function () {
    // If offset + limit becomes greater or equal to total results, there are no more records.
    if (offset + limit >= totalResults) {
        return;
    }

    // Update offset and get desks.
    offset += limit;
    fetchData();
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
        fetchData();
    } else {
        alert('You must select a date!');
    }


});

// End of code adapted from Hassan's

/**
 * Fetches booking quota and desk data via the API and renders the page.
 */
async function fetchData() {
    // Use Fetch API to get booking quota and desks.
    const quotaResponse = await fetch("/api/booking_quota/rooms/" + roomSelect.value +
        "/users/" + usernameSelect.value);
    const desksResponse = await fetch("/api/desks_available?" + "username=" + usernameSelect.value +
        "&room_id=" + roomSelect.value + "&date=" + dateSelect.value + "&offset=" + offset + "&limit=" + limit);

    // If there was an error, redirect to 500 page.
    if (!quotaResponse.ok || !desksResponse.ok) {
        window.location.replace("/internal_server_error");
        return;
    }

    // Parse responses.
    const quotaJson = await quotaResponse.json();
    const desksJson = await desksResponse.json();

    // Process.

    // Store quota.
    quota = quotaJson;

    // Display remaining quota.
    quotaText.innerText = "Remaining quota: " + quota["remaining"];

    // Store desks total results count.
    totalResults = desksJson["total_results"];

    // Display desks.
    displayDesks(desksJson);

    // Update results text in table.
    if (limit + offset > totalResults) {
        resultsText.innerText = "Displaying " + totalResults + " of " + totalResults + " results";
    } else {
        resultsText.innerText = "Displaying " + (limit + offset) + " of " + totalResults + " results";
    }
}

// Start of code that was adapted from Hassan's code in manage_desks.js

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

        let type = document.createElement("td");
        type.innerText = desk["desk_type"]["name"];

        let notes = document.createElement("td");
        notes.innerText = desk["notes"];

        let availabilityCell = availabilityCellConfigurer(desk);

        let buttonCell = buttonCellConfigurer(desk);

        row.append(id, name, type, notes, availabilityCell, buttonCell);

        table.appendChild(row);
    });

}

// End of code adapted from Hassan's

/**
 * Creates a table cell. If the desk that is being iterated
 * over is available on the given day, add a booking button
 * to that cell. If the person who logged in has booked it,
 * show a delete button. If the person has one booking on
 * that day but the desk is available, added a faded book
 * button.
 * @param desk A single desk object from a JSON of desks
 * @returns {HTMLTableDataCellElement} The td to add to table
 * row
 */
function buttonCellConfigurer(desk) {

    // Create a cell that will be placed in last column in our table

    let buttonCell = document.createElement("td");

    // If-else statement to set what is in the last cell of a desk row:

    // 1) Solid delete button = You have booked this desk
    // 2) Solid book button = You can book this desk
    // 3) Faded book button = You cannot book this button (either because
    // other user has booked it or you already have a booking that day)

    if (desk["does_user_have_that_desk_booked_on_that_day"] == true) {
        let btn = document.createElement("button");
        btn.innerHTML = "Delete";
        btn.className = "btn btn-danger btn-sm"
        btn.id = desk["booking_id"];
        btn.addEventListener("click", function () {
            deleteBookingViaBookPage(this.id);
        });
        buttonCell.appendChild(btn);
    } else if (quota["remaining"] === 0 && !desk["does_user_have_booking_on_that_day"]) {
        let btn = document.createElement("button");
        btn.innerHTML = "Book";
        btn.className = "btn btn-success btn-sm"
        btn.id = desk["id"];
        btn.style.opacity = "0.6";
        btn.setAttribute("data-mdb-toggle", "modal");
        btn.setAttribute("data-mdb-target", "#noQuotaRemaining");
        btn.setAttribute('title', 'You have no quota remaining.');
        buttonCell.appendChild(btn);
    } else if (desk["available"] === true && desk["does_user_have_booking_on_that_day"] == false) {
        let btn = document.createElement("button");
        btn.innerHTML = "Book";
        btn.className = "btn btn-success btn-sm"
        btn.id = desk["id"];
        btn.addEventListener("click", function () {
            postBooking(this.id);
        });
        buttonCell.appendChild(btn);
    } else if (desk["available"] === false && desk["does_user_have_that_desk_booked_on_that_day"] == false) {
        let btn = document.createElement("button");
        btn.innerHTML = "Book";
        btn.className = "btn btn-success btn-sm"
        btn.id = desk["id"];
        // Attributes needed for modal:
        // data-mdb-toggle="modal" data-mdb-target="#bookedOut"
        btn.style.opacity = "0.6";
        btn.setAttribute("data-mdb-toggle", "modal");
        btn.setAttribute("data-mdb-target", "#bookedOut");
        btn.setAttribute('title', 'Someone else has booked this desk out.');
        buttonCell.appendChild(btn);
    } else {
        let btn = document.createElement("button");
        btn.innerHTML = "Book";
        btn.className = "btn btn-success btn-sm"
        btn.id = desk["id"];
        // Attributes needed for modal:
        // data-mdb-toggle="modal" data-mdb-target="#oneBookingPerDay"
        btn.style.opacity = "0.6";
        btn.setAttribute("data-mdb-toggle", "modal");
        btn.setAttribute("data-mdb-target", "#oneBookingPerDay");
        btn.setAttribute('title', 'You may only book one desk per day.');
        buttonCell.appendChild(btn);
    }

    return buttonCell;

}

/**
 * Create function that will take a desk object in (as we are iterating over
 * all desks) and populate the column titled "Available" with either a check
 * (if it is available) and a cross (if it is not available).
 * @param desk
 * @returns {HTMLTableDataCellElement}
 */
function availabilityCellConfigurer(desk) {

    // Create a cell for our availability icon

    let availabilityCell = document.createElement("td");
    let spanText = document.createElement("i");

    // If the desk is available on that day, show a check in the
    // available column, else show a cross

    if (desk["available"] === true) {

        spanText.innerHTML = "✓";
        // add bootstrap styling for check marks
        spanText.className = "bi bi-check";
        availabilityCell.appendChild(spanText);

    } else {

        spanText.innerHTML = "✕";
        // add bootstrap styling for cross marks
        spanText.className = "bi bi-x";
        availabilityCell.appendChild(spanText);

    }

    return availabilityCell;


}

/**
 * A function that the book buttons will fire off
 * if clicked, that will making a booking post
 * request to the booking database with all of the
 * paramters needed to do so from the DOM.
 * @param deskId The ID of the desk in question
 * which will be stored in the button pressed
 * as the element id.
 */
function postBooking(deskId) {

    let roomIdParam = roomSelect.value;
    let dateParam = dateSelect.value;
    let usernameParam = usernameSelect.value;

    // Uncomment the following to check params
    // console.log("Making booking for desk number " + deskId + " in room number " + roomIdParam + " on " + dateParam + " for user with username: " + usernameParam);

    let params = 'bookingDeskId=' + deskId + '&bookingRoomId=' + roomIdParam + '&bookingDate=' + dateParam + '&username=' + usernameParam;
    // console.log(params);
    let xhttp = new XMLHttpRequest();
    xhttp.open("POST", "/booking/add/process_form", true);
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");

    // Configure the request object to listen for changes in state and if the right
    // state is heard then hide button, show loading icon, then display "booked".

    xhttp.onreadystatechange = function () {

        if (xhttp.readyState == 4) {

            if (xhttp.status === 200) {

                // Show loading icon to user

                showLoaderById(deskId);

                // After short period of time, refresh desks in DOM

                setTimeout(function () {
                    fetchData();
                }, 1000);

                // Otherwise, print errors to console if there were any

            } else {
                console.error(xhttp.statusText);
            }
        }
    }

    // Send the booking post request to sever

    xhttp.send(params);

}

/**
 * Before sending the booking post request, we show the user
 * a loading icon for a short period of time so that they
 * know something has happened
 * @param id The ID of the button pressed
 */
function showLoaderById(id) {

    // We want to create the following piece of HTML and add it to our DOM

    // <div className="spinner-border text-dark spinner-border-sm" role="status">
    //     <span className="sr-only"></span>
    // </div>

    // Create a div with the above attributes and its own ID

    let loaderDiv = document.createElement("div");
    loaderDiv.className = "spinner-border text-dark spinner-border-sm";
    loaderDiv.role = 'status';
    loaderDiv.id = 'loaderDiv';

    // Create span object with above attributes and put it in the
    // div as above.

    let loaderSpan = document.createElement("span");
    loaderSpan.className = "sr-only";
    loaderDiv.appendChild(loaderSpan);

    // Test to see what we created:
    // console.log("Element with id loaderDiv is:");
    // console.log(loaderDiv);

    // Create a variable that points to cell before
    // we remove button from DOM

    let cell = document.getElementById(id).parentElement;

    // Remove button from DOM (when hiding it, it still
    // took up space in table cell so better to remove it)

    document.getElementById(id).remove();

    // Add loader icon to parent of button, the cell

    cell.appendChild(loaderDiv);

}

// Function to delete a booking. The function reloads desks
// when delete was a success, which will switch the desk row
// back to having a book button.
function deleteBookingViaBookPage(id) {

    // Set up HTTP request

    let params = 'id=' + id;
    let xhttp = new XMLHttpRequest();
    xhttp.open("DELETE", "/booking/delete", true);
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");

    // Make the document listen for a change of state to the object
    // that holds the HTTP request. If the state changes, see if that
    // state change was that the HTTP request was a success and if so
    // call a function to also remove that booking from the DOM. Otherwise,
    // print what the state change was to the console.

    xhttp.onreadystatechange = function () {

        if (xhttp.readyState == 4) {
            if (xhttp.status === 200) {

                showLoaderById(id);


                // After showing loader icon for a short period,
                // reload the desks (this will remove the delete
                // button too)
                setTimeout(function () {
                    fetchData();
                }, 1000);

            } else {
                console.error(xhttp.statusText);
            }
        }
    }

    // Send the HTTP request

    xhttp.send(params);

    // Return false to the form that called this function, to prevent
    // that form from forming its own HTTP request and sending it.

    return false;

}
