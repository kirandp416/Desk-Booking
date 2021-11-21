// Get elements.
const resultsText = document.getElementById("resultsText");
let roomSelect = document.getElementById("room");
const tablePreviousBtn = document.getElementById("tablePreviousBtn");
const tableNextBtn = document.getElementById("tableNextBtn");
const viewDesksBtn = document.getElementById("viewDesksBtn");

// Table pagination state.
const limit = 10;
let offset = 0;
let totalResults = 0;

// Add event listener to table previous button.
tablePreviousBtn.addEventListener("click", function () {
    // If the offset is 0, there are no more previous records.
    if (offset === 0) {
        return;
    }

    // Update offset and get desks.
    offset -= limit;
    getDesks();
});

// Add event listener to table next button.
tableNextBtn.addEventListener("click", function () {
    // If offset + limit becomes greater or equal to total results, there are no more records.
    if (offset + limit >= totalResults) {
        return;
    }

    // Update offset and get desks.
    offset += limit;
    getDesks();
});

// Add click listener for view desks button.
viewDesksBtn.addEventListener("click", function () {
    // Get desks.
    getDesks();
});

/**
 * Gets desks using AJAX.
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
 * Displays desks in the table.
 * @param json the JSON response.
 */
function displayDesks(json) {
    // Get table body.
    let table = document.getElementsByTagName("tbody")[0];

    // Remove all child elements.
    table.innerHTML = "";

    // Create rows for each desk.
    json["results"].forEach(function (desk) {
        let row = document.createElement("tr");

        let id = document.createElement("td");
        id.innerText = desk["id"];

        let name = document.createElement("td");
        name.innerText = desk["name"];

        row.append(id, name);

        table.appendChild(row);
    });
}
