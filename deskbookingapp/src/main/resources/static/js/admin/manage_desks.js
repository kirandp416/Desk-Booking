// Get elements.
const desksTableBody = document.getElementById("desksTable").getElementsByTagName("tbody")[0];
const resultsText = document.getElementById("resultsText");
const roomSelect = document.getElementById("room");

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

    // Get desks.
    getDesks();
});

/**
 * Gets desks using AJAX.
 * If successful, the desks are displayed in a table.
 * Otherwise, user is redirected to 500 error page.
 */
function getDesks() {
    let xhttp = new XMLHttpRequest();
    xhttp.open("GET", "/api/desks?room_id=" + roomSelect.value +
        "&offset=" + offset + "&limit=" + limit, true);
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
 * Deletes a desk.
 * @param id the ID of the desk.
 */
function deleteDesk(id) {
    // Create AJAX request to delete desk.
    let xhttp = new XMLHttpRequest();
    xhttp.open("DELETE", "/api/admin/desks/" + id, true);
    xhttp.onreadystatechange = function () {
        if (xhttp.readyState === 4) {
            if (xhttp.status === 204) {
                // If response status is 204, deletion was successful.
                // Refresh desks table.
                getDesks();
            } else if (xhttp.status === 404) {
                // If response status is 404, desk was not found.
                // Somebody else probably deleted the desk, refresh desks table.
                getDesks();
            } else {
                // All other statuses.
                // Show an alert.
                alert("There was an error deleting the desk.");
            }
        }
    };

    // Execute the AJAX request.
    xhttp.send();
}

/**
 * Displays desks in the table.
 * @param json the JSON response.
 */
function displayDesks(json) {
    // Remove all child elements from table.
    desksTableBody.innerHTML = "";

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

        // Action buttons.
        let actions = document.createElement("td");

        // Delete button.
        let deleteBtn = document.createElement("button");
        deleteBtn.className = "btn btn-danger btn-sm";
        deleteBtn.innerText = "Delete";
        deleteBtn.dataset.id = desk["id"]; // data-id=id of desk
        deleteBtn.addEventListener("click", function (e) {
            deleteDesk(e.currentTarget.dataset.id);
        });

        // Append elements to parent containers.
        actions.appendChild(deleteBtn);
        row.append(id, name, type, notes, actions);
        desksTableBody.appendChild(row);
    });
}
