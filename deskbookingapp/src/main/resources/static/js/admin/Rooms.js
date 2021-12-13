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
                document.getElementById("result").className="alert alert-success";
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

//This function is called when user clicks on update.
//This changes the style.display of buttons div and hide it
//Also this changes style.display of edit Form and show it.

function editRoom(id){
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
        console.log("went to else");
    }
}

//This Function is called on submit button of the form
//This will take id and name from form and pass it to controller as PUT method.
//Added a event listener to prevent default method as the function is not working when launched for the first time.
//Added a reload method to reload the page if request was successful.
function saveEdit(id) {
    var name = document.forms["editForm" + id]["name"].value;
    let params = 'id=' + id + "&" + 'name=' + name;
    console.log(params);
    let xhttp = new XMLHttpRequest();
    xhttp.open("PUT", "/admin/room/edit", true);
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    document.forms["editForm"+ id].addEventListener("submit",function (e){e.preventDefault();});
    xhttp.onreadystatechange = function () {
        console.log("start");
        if (xhttp.readyState == 4) {
            if (xhttp.status === 200) {
                console.log("done it");
                location.reload();
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