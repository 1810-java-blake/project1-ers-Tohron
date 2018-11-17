var table;

var reimbursements = [];
var reimb_lines = [];
var selected_line;
/*
class Reimbursement {
    constructor(id, amt, submitted, resolved, author, author_id, resolver, resolver_id, description) {
        this.html_id = "";
        this.id = id;
        this.amt = amt;
        this.submitted = submitted;
        this.resolved = resolved;
        this.author = author;
        this.resolver = resolver;
        this.author_id = author_id;
        this.resolver_id = resolver_id;
        this.description = description;
    }
}
*/
document.addEventListener("DOMContentLoaded", function() {
    table = document.getElementById("table");
    //loadReimbursements();
});

function loadReimbursements() {
    table.innerHtml = reimb_lines.join("");
}

function clickHandler(event, id) {
    // prevent event from continuing
    // to further elements
    // not recommended!
    // event.stopPropagation();

    //console.log(`target: ${event.target.id}`);

    // stop any default browser behavior
    // that's already on this event
    // event.preventDefault();

    console.log(id);
    let div1 = document.getElementById(id);
    div1.style.backgroundColor="#00ffff";
}

fetch('http://localhost:8080/ERS/employee/9', { // Program will eventually use session ID to determine employee ID
  credentials: 'include'
})
.then(resp => resp.json())
.then(data => {
    const e_table = document.getElementById('e-table');
    reimbursements = data;
    // --------------- Need way to convert nulls and longs into strings!!!
    reimb_lines = reimbursements.map(r => `
    <tr id="r${r.id}" onclick="clickHandler(event, 'r${r.id}')">
        <td class="c1">$${r.amount}</td>  <td class="c2">${r.type}</td> <td class="c3">${r.submitted}</td> <td class="c4">${r.resolved}</td> <td class="c5">${r.status}</td>
    </tr>
    `);
    e_table.innerHTML = `<thead>
    <tr>
    <th class="c1">Amount</th> <th class="c2">Type</th> <th class="c3">Submitted</th> <th class="c4">Resolved</th> <th class="c5">Status</th>
    </tr> </thead><tbody>` + reimb_lines.join('') + "</tbody>";
    
    
})