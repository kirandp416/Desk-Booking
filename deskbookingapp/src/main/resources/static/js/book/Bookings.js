
// A function that takes an id (in our case this will be booking
// id which we have mapped to the id of the button) and then sends
// a request to the database to delete the booking with that id. If
// the request comes back and was successful, it will also call a
// function that removes that booking from the DOM

function deleteBookingViaManageBookingsPage(id) {

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

// A function that can be called via an onclick on an element. That
// function will remove the parent of that element (and therefore the
// element itself), if that element is clicked.

function removeContentsOfRow(dataId) {

    const bookingRow = document.querySelector('[data-id="' + dataId + '"]');
    bookingRow.parentElement.removeChild(bookingRow);

}

