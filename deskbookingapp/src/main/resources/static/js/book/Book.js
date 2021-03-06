// For debugging purposes you can uncomment the code below. It will print whatever is in
// the form fields, whenever there is a change to the data in the fields.
// This debugger code was adapted from https://stackoverflow.com/questions/33697683/html-previo

// $("form :input").change(function () {
//     console.log("Form data changed.")
//     console.log($(this).closest('form').serialize());
// });

// Get elements.
const dateSelect = document.getElementById("bookingDate");
const desksAvailabilityTitle = document.getElementById("desksAvailabilitySubtitle");
const quotaText = document.getElementById("quotaText");
const resultsText = document.getElementById("resultsText");
const roomSelect = document.getElementById("room");
const table = document.getElementsByTagName("tbody")[0];
const progressSpinner = document.getElementById("progressSpinner");
const usernameSelect = document.getElementById("username");

// Table pagination state.
const limit = 10;
let offset = 0;
let totalResults = 0;

// Quota state.
let quota = null;

// Ready function.
$(document).ready(function () {
    // Set the date picker date to today's date.
    setDateToToday();

    // Set advance booking to 30 days maximum.
    setMaxDate();

    // Fetch data.
    fetchData();
});

// Add on change event listener to booking date picker and room select option.
dateSelect.addEventListener("change", dateRoomChanged);
roomSelect.addEventListener("change", dateRoomChanged);

/**
 * Date/room change handler.
 */
function dateRoomChanged() {
    // Validate date.
    // If valid, fetch data.
    // Else clear desk availability data.
    if (validateDate()) {
        progressSpinner.classList.remove("visually-hidden");
        setTimeout(fetchData, 500);
    } else {
        desksAvailabilityTitle.innerText = "";
        quotaText.innerText = "";
        table.innerHTML = "";
        resultsText.innerText = "";
        offset = 0;
        totalResults = 0;
    }
}

// Start of code that was adapted from Hassan's code in manage_desks.js

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

// End of code adapted from Hassan's

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
 * Create a function that we can use when we need to set the date back to today's date
 * e.g. if an invalid date is supplied
 */
function setDateToToday() {
    let todayFormattedString = todaysDateReturner();
    console.log("Setting date to today's date...")
    document.getElementById("bookingDate").value = todayFormattedString;
}

/**
 * Sets the maximum date for the date picker to be 30 days from today.
 */
function setMaxDate() {
    let max = new Date();
    max.setDate(max.getDate() + 30);
    // Canada uses YYYY-MM-DD so we'll use that as a shortcut to get the required date format.
    dateSelect.setAttribute("max", max.toLocaleDateString("en-ca"));
}

/**
 * Create a function that will clears the current date if and when
 * we call it.
 */
// function dateClearer() {
//     let date_input = document.getElementById("bookingDate");
//
//     //erase the input value
//     date_input.value = '';
//
//     //prevent error on older browsers (aka IE8)
//     if (date_input.type === 'date') {
//         //update the input content (visually)
//         date_input.type = 'text';
//         date_input.type = 'date';
//     }
// }

/**
 * Validate the inputted date.
 * If the user selects a date in the past, clear the calendar.
 * @returns {boolean} true if the date is valid, false otherwise.
 */
function validateDate() {
    // Check if a date has been chosen.
    if (dateSelect.value === "") {
        return false;
    }

    // If a change to the date form is heard by the event listener in the form,
    // create a date object that is formatted in such a way that we can compare
    // it the date they just clicked on
    let bookingDate = new Date(document.getElementById("bookingDate").value);
    let today = new Date();
    let todayDate = new Date(today.getFullYear(), today.getMonth(), today.getDate(), 0, -today.getTimezoneOffset());

    // Compare the date they just put in to todays' date. If the date they
    // put in was before today, tell them and then clear the current date.
    if (bookingDate < todayDate) {
        alert('You cannot choose a date in the past.');
        dateSelect.value = "";
        return false;
    }

    return true;
}

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

    // Set subtitle.
    desksAvailabilityTitle.innerText = "Showing desk availability for " + new Date(dateSelect.value).toLocaleDateString();

    // Hide table progress spinner.
    progressSpinner.classList.add("visually-hidden");
}

/**
 * Displays desks in the table.
 * @param json the JSON response that holds the data
 *             on the desks.
 */
function displayDesks(json) {
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

// End of code adapted from Hassan's code in manage_desks.js

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

    // For a check cell we need to load the following html in:

    // <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-check" viewBox="0 0 16 16">
    //   <path d="M10.97 4.97a.75.75 0 0 1 1.07 1.05l-3.99 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425a.267.267 0 0 1 .02-.022z"/>
    // </svg>

    // Reference: https://icons.getbootstrap.com/icons/check/

    // and for a cross we need to load the following html in:

    // <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-x" viewBox="0 0 16 16">
    //   <path d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z"/>
    // </svg>

    //Reference: https://icons.getbootstrap.com/icons/x/

    // Both of the above svgs are in our Book.html as hidden elements. We will
    // access these using the DOM, clone them, switch the clones to default display
    // instead of hidden and then use these cloned elements to add check icons and
    // cross icons to our table (in the available column)

    // Create a cell for our availability icon

    let availabilityCell = document.createElement("td");

    // Uncomment the following to centre the check/cross icon in the
    // column

    // availabilityCell.style.textAlign = "center";

    // If the desk is available on that day, show a check in the
    // available column, else show a cross

    if (desk["available"] === true) {

        let checkIconSVG = checkIconReturner();
        availabilityCell.appendChild(checkIconSVG);

    } else {

        let crossIconSVG = crossIconReturner();
        availabilityCell.appendChild(crossIconSVG);

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

/**
 * Function to delete a booking. The function also reloads desks
 * table in DOM if the delete was a success. This ought to switch
 * the desk's button back to a book button, since you just deleted
 * the booking associated with it.
 * @param id Booking ID
 * @returns {boolean} Always returns false to prevent form submission
 */
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

/**
 * Finds the hidden svg in DOM that is for the Bootstrap styled check icon.
 * Clones that element and points new variable to the clone. Changes display
 * of clone from hidden to inline (this is svg's default display). Returns
 * the cloned element.
 * @returns {Node} An HTML svg element that shows a Bootstrap check icon.
 */
function checkIconReturner(){

    // Clone check element that is in DOM
    // Reference: https://www.w3schools.com/jsref/tryit.asp?filename=tryjsref_node_clonenode

    let checkIconSVG = document.getElementById("bootstrapCheckIcon");
    let checkIconSVGClone = checkIconSVG.cloneNode(true);

    // Check what the default display of an svg is (remove display:none from
    // it to check this)
    // console.log("Default display of svg is:")
    // console.log(window.getComputedStyle(checkIcon).display);

    // Set style of SVG clone from none to the default

    checkIconSVGClone.style.display = "inline";

    return checkIconSVGClone;

}

/**
 * Finds the hidden svg in DOM that is for the Bootstrap styled cross icon.
 * Clones that element and points new variable to the clone. Changes display
 * of clone from hidden to inline (this is svg's default display). Returns
 * the cloned element.
 * @returns {Node} An HTML svg element that shows a Bootstrap cross icon.
 */
function crossIconReturner(){

    // Clone cross element that is in DOM
    // Reference: https://www.w3schools.com/jsref/tryit.asp?filename=tryjsref_node_clonenode

    let crossIconSVG = document.getElementById("bootstrapCrossIcon");
    let crossIconSVGClone = crossIconSVG.cloneNode(true);

    // Set style of SVG clone from none to the default

    crossIconSVGClone.style.display = "inline";

    return crossIconSVGClone;



}
