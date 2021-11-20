
// A function that takes an id (in our case this will be booking
// id which we have mapped to the id of the button) and then sends
// a request to the database to delete the booking with that id. If
// the request comes back and was successful, it will also call a
// function that removes that booking from the DOM

function deleteBooking(id) {

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
                removeMyParent(id);
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

function removeMyParent(id) {

    const bookingRow = document.querySelector('[data-id="' + id + '"]');
    bookingRow.parentElement.removeChild(bookingRow);

}

