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

    // Create AJAX request to create desk.
    let xhttp = new XMLHttpRequest();
    xhttp.open("POST", "/api/admin/desks", true);
    xhttp.setRequestHeader("Content-Type", "application/json");
    xhttp.onreadystatechange = function () {
        // If response is 201 created, the request was successful.
        if (xhttp.readyState === 4) {
            if (xhttp.status === 201) {
                document.getElementById("result").innerText = "Successfully create desk.";
            } else {
                document.getElementById("result").innerText = "An error occurred.";
            }
        }
    }

    // Execute AJAX request, sending the form inputs as JSON.
    xhttp.send(JSON.stringify({
        room: room,
        name: name
    }));
}
