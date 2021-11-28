// Add on submit listener to form.
document.forms["form"].addEventListener("submit", onSubmit);

/**
 * Form submission handler.
 * @param e the event.
 */
function onSubmit(e) {
    // Prevent default behaviour of form.
    e.preventDefault();

    // Get the form.
    let form = document.forms["form"];

    // Get inputs.
    let room = form["room"].value;
    let name = form["name"].value;
    let deskType = form["deskType"].value;
    let notes = form["notes"].value;

    // Create AJAX request to create desk.
    let xhttp = new XMLHttpRequest();
    xhttp.open("POST", "/api/admin/desks", true);
    xhttp.setRequestHeader("Content-Type", "application/json");
    xhttp.onreadystatechange = function () {
        // Check response status.
        if (xhttp.readyState === 4) {
            // 201 created: success.
            if (xhttp.status === 201) {
                document.getElementById("result").innerText = "Successfully create desk.";
            }
            // 409 conflict: desk with same name for the room already exists.
            else if (xhttp.status === 409) {
                document.getElementById("result").innerText =
                    "A desk with the same name already exists for the selected room.";
            }
            // Any other status.
            else {
                document.getElementById("result").innerText = "An error occurred.";
            }
        }
    }

    // Execute AJAX request, sending the form inputs as JSON.
    xhttp.send(JSON.stringify({
        room: room,
        name: name,
        deskType: deskType,
        notes: notes
    }));
}
