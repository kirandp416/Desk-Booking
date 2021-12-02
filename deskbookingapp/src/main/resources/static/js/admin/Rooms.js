// This code was adapted from HOs method called deleteBooking in file Booking.js,
// that was used to delete booking from database using ajax.
//
//This method will delete a room by taking the id of the row and passing it through
//delete request

function deleteRoom(id) {

    // Set up HTTP request

    let params = 'id=' + id;
    let xhttp = new XMLHttpRequest();
    xhttp.open("DELETE", "/admin/room/delete", true);
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");


    xhttp.onreadystatechange = function () {

        if (xhttp.readyState == 4){
            if (xhttp.status === 200){
                console.log("Removing deleted item from DOM...")
                removeMyParent(id);
                document.getElementById("result").innerText = "Successfully deleted room.";
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

    const roomRow = document.querySelector('[data-id="' + id + '"]');
    roomRow.parentElement.removeChild(roomRow);

}
//End of code adapted from HO

function editRoom(id){
    let params = 'id=' + id;
    console.log(id);
    var edit = document.getElementById("edit"+id);
    var buttons = document.getElementById("buttons"+id);
    if(edit.style.display == "none"){
        edit.style.display="inline";
        buttons.style.display="none";
    }
    else{
        edit.style.display="none";
        edit.style.display="inline";
    }
}
function saveEdit() {
    var name = document.forms["editForm"]["name"].value;
    var id = document.forms["editForm"]["id"].value;
    let params = 'id=' + id + "&" + 'name=' + name;
    console.log(params);
    let xhttp = new XMLHttpRequest();
    xhttp.open("POST", "/admin/room/edit", true);
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xhttp.onreadystatechange = function () {
        if (xhttp.readyState == 4) {
            if (xhttp.status === 200) {
                document.getElementById("result").innerText = "Successfully updated room name.";
                console.log("done it")
            } else {
                window.location.replace("/internal_server_error");
                console.log(xhttp.getAllResponseHeaders());
                document.getElementById("result").innerText = "Error in updating room name.";
            }
        }
    }
    xhttp.send(params);
    return false;
}