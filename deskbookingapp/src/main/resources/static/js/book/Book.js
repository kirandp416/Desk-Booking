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
function todaysDateReturner(){
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

function viewDesksButtonDataValidator(){

    if (document.getElementById("bookingDate").value.length == 0){
        console.log("You pressed view desks but there is no date");
        return false;
    }
    else
        return true;
}



// Start of code that was adapted from Hassan's code in manage_desks.js

// Get elements.
const resultsText = document.getElementById("resultsText");
const roomSelect = document.getElementById("room");
const dateSelect = document.getElementById("bookingDate");
const usernameSelect = document.getElementById("username")

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


    // If the form is validated, load DOM with desks. Otherwise,
    // let them know the error. Since there is only one place the
    // form can currently go wrong and that is for an empty date
    // input, we alert them to an empty date.
    if (viewDesksButtonDataValidator() == true){
        // Get desks.
        getDesks();
    }
    else{
        alert('You must select a date!');
    }


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
    console.log("tbdy [0]:")
    console.log(table);

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

        let buttonCell = buttonConfigurer(desk);

        row.append(id, name, type, notes, buttonCell);

        table.appendChild(row);
    });

}
// End of code adapted from Hassan's


/**
 * Creates a table cell. Uf the desk that is being iterated
 * over is available on the given day, add a booking button
 * to that cell, otherwise just add the text "booked".
 * @param desk A single desk object from a JSON of desks
 * @returns {HTMLTableDataCellElement} The td to add to table
 * row
 */
function buttonConfigurer(desk) {

    // Create a cell in the last column in our table that
    // can hold a button

    let buttonCell = document.createElement("td");

    // If the desk is available on the selected date, render
    // a button in the buttonCell. Otherwise, just show the
    // word "Booked".

    if (desk["available"] === true) {
        let btn = document.createElement("button");
        btn.innerHTML = "Book";
        btn.className = "btn btn-success btn-sm"
        btn.id = desk["id"];
        btn.addEventListener("click", function () {
            postBooking(this.id)
        });
        buttonCell.appendChild(btn);
    } else {
        let spanText = document.createElement("span");
        spanText.innerHTML = "Booked";
        buttonCell.appendChild(spanText);
    }

    return buttonCell;

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
    console.log(params);
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
                    getDesks();
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
    console.log("Element with id loaderDiv is:");
    console.log(loaderDiv);

    // Create a variable that points to cell before
    // we remove button from DOM

    let cell = document.getElementById(id).parentElement;

    // Remove button from DOM (when hiding it, it still
    // took up space in table cell so better to remove it)

    document.getElementById(id).remove();

    // Add loader icon to parent of button, the cell

    cell.appendChild(loaderDiv);

}

