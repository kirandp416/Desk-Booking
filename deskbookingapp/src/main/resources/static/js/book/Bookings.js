

/**
 * A function for the employee user that takes an id (in our case this
 * will be booking id which we have mapped to the id of the button) and
 * then sends a request to the database to delete the booking with that
 * id. If the request comes back and was successful, it will also call a
 * function that removes that booking from the DOM.
 *
 * @param id
 * @returns {boolean}
 */
function deleteBookingForEmployeeViaManageBookingsPage(id) {

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

        if (xhttp.readyState == 4){
            if (xhttp.status === 200){
                console.log("Removing deleted item from DOM...")

                // Call function from Book.js that will find an element
                // in DOM by id. It will remove that element from DOM
                // i.e. the button and then append a bootStrap loading
                // icon to its parent i.e. the table cell.

                showLoaderById(id);

                // After showing the ID for a short period of time, call
                // removeContentsOfRow which selects the row by data-id (not id!
                // Id and data-id will have same numerical value but they are not
                // the same attribute). After selecting the row, the function
                // removes the cells in it i.e. makes the row invisible.

                setTimeout(function () {
                    removeContentsOfRow(id);
                }, 1000);


            }
            else{
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
 *
 * A function for the admin that takes an id (in our case this will
 * be booking id which we have mapped to the id of the button) and
 * then sends a request to the database to delete the booking with
 * that id. If the request comes back and was successful, it will
 * also call a function that removes that booking from the DOM
 *
 * @param id
 * @returns {boolean}
 */
function deleteBookingForAdminViaManageBookingsPage(id) {

    // Set up HTTP request

    let params = 'id=' + id;
    let xhttp = new XMLHttpRequest();
    xhttp.open("DELETE", "/admin/booking/delete", true);
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");

    // Make the document listen for a change of state to the object
    // that holds the HTTP request. If the state changes, see if that
    // state change was that the HTTP request was a success and if so
    // call a function to also remove that booking from the DOM. Otherwise,
    // print what the state change was to the console.

    xhttp.onreadystatechange = function () {

        if (xhttp.readyState == 4){
            if (xhttp.status === 200){
                console.log("Removing deleted item from DOM...")

                // Call function from Book.js that will find an element
                // in DOM by id. It will remove that element from DOM
                // i.e. the button and then append a bootStrap loading
                // icon to its parent i.e. the table cell.

                showLoaderById(id);

                // After showing the ID for a short period of time, call
                // removeContentsOfRow which selects the row by data-id (not id!
                // Id and data-id will have same numerical value but they are not
                // the same attribute). After selecting the row, the function
                // removes the cells in it i.e. makes the row invisible.

                setTimeout(function () {
                    removeContentsOfRow(id);
                }, 1000);


            }
            else{
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
 *
 * A function that can be called via an onclick on an element. That
 * function will remove the parent of that element (and therefore the
 * element itself), if that element is clicked.
 *
 * @param dataId
 */
function removeContentsOfRow(dataId) {

    const bookingRow = document.querySelector('[data-id="' + dataId + '"]');
    bookingRow.parentElement.removeChild(bookingRow);

}

/**
 * Before sending the booking post request, we show the user
 * a loading icon for a short period of time so that they
 * know something has happened
 *
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

