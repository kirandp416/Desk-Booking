// This code was adapted from HOs method called deleteBooking in file Booking.js,
// that was used to delete booking from database using ajax.
//
//This method will delete a room by taking the id of the row and passing it through
//delete request

function deleteBooking(id) {

    // Set up HTTP request

    let params = 'id=' + id;
    let xhttp = new XMLHttpRequest();
    xhttp.open("DELETE", "/admin/booking/delete", true);
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");


    xhttp.onreadystatechange = function () {

        if (xhttp.readyState == 4){
            if (xhttp.status === 200){
                console.log("Removing deleted item from DOM...")
                removeMyParent(id);
                document.getElementById("result").innerText = "Successfully deleted Booking.";
            }
            else{
                window.location.replace("/internal_server_error");
                console.log(xhttp.getAllResponseHeaders());
            }
        }
    }

    // Send the HTTP request

    xhttp.send(params);

    return false;

}

// Calling function to remove parent
//so the deleted item is updated in the page.

function removeMyParent(id) {

    const bookingRow = document.querySelector('[data-id="' + id + '"]');
    bookingRow.parentElement.removeChild(bookingRow);

}
//End of code adapted from HO