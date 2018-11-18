var table;

var reimbursements = [];
var reimb_lines = [];
var selected_line;

var a_field;
var d_field;
var t_select;

var button1;
var button_cancel;

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

    a_field = document.getElementById("inputAmount");
    a_field.disabled = true;
    d_field = document.getElementById("inputDescription");
    d_field.disabled = true;
    t_select = document.getElementById("inputType");
    t_select.disabled = true;
    button1 = document.getElementById("button1");
    button_cancel = document.getElementById("button_cancel");
    button_cancel.disabled = true;
    //loadReimbursements();
});

function create() {
    selected_line = null;
    console.log("Create Call");
    if (button_cancel.disabled == false) { // ready to submit
        amount = a_field.value;
        description = d_field.value;
        type = t_select.value;
        const reimb_dat = {
            id,
            amount, 
            description,
            type
        }
        console.log(reimb_dat);
        
        fetch('http://localhost:8080/ERS/employee/add_reimb', {
        method: 'POST',
        body: JSON.stringify(reimb_dat),
        headers: {
            'Content-Type': 'application/json'
        },
        credentials: 'include'
        })
        .then(res => {
            console.log("Posted: " + res);
        })
        .catch(err => {
            console.log(err);
        })


        button_cancel.disabled = true;
        button1.innerHTML = "Create Reimbursement";
        a_field.disabled = true;
        d_field.disabled = true;
        t_select.disabled = true;
        a_field.value  = "";
        d_field.value  = "";
        t_select.selectedIndex = 0;
        update();
    } else { // not ready to submit
        button1.innerHTML = "Submit Reimbursement";
        a_field.disabled = false;
        d_field.disabled = false;
        t_select.disabled = false;
        button_cancel.disabled = false;
        a_field.value  = "";
        d_field.value  = "";
        t_select.selectedIndex = 0;
    }
}

function cancel() {
    console.log("Cancelling");
    selected_line = null;
    a_field.disabled = true;
    d_field.disabled = true;
    t_select.disabled = true;
    button_cancel.disabled = true;
    button1.innerHTML = "Create Reimbursement";
    a_field.value  = "";
    d_field.value  = "";
    t_select.selectedIndex = 0;
}

function loadReimbursements() {
    table.innerHtml = reimb_lines.join("");
}

function clickHandler(event, id, index) {
    // prevent event from continuing
    // to further elements
    // not recommended!
    // event.stopPropagation();

    //console.log(`target: ${event.target.id}`);

    // stop any default browser behavior
    // that's already on this event
    // event.preventDefault();

    // Deselects last line
    var div1;
    if (selected_line) {
        div1 = document.getElementById(selected_line);
        div1.style.backgroundColor="#ffffff";
        console.log("Deselecting " + selected_line); // never reached!
    }

    cancel();

    console.log("Selecting " + id);
    selected_line = id;
    div1 = document.getElementById(id);
    div1.style.backgroundColor="#33bbff";
    // fills right side with data for selected line
    a_field.value  = reimbursements[index].amount;
    d_field.value  = reimbursements[index].description;
    t_select.value  = reimbursements[index].type;



    
}
function update() {
    fetch('http://localhost:8080/ERS/employee', { // Program will eventually use session ID to determine employee ID
    credentials: 'include'
    })
    .then(resp => resp.json())
    .then(data => {
        const e_table = document.getElementById('e-table');
        reimbursements = data;
        // --------------- Need way to convert nulls and longs into strings!!!
        reimb_lines = reimbursements.map((r, index) => `
        <tr id="r${r.id}" onclick="clickHandler(event, 'r${r.id}', ${index})">
            <td class="c1">$${r.amount}</td>  <td class="c2">${r.type}</td> <td class="c3">${r.submitted}</td> <td class="c4">${r.resolved}</td> <td class="c5">${r.status}</td>
        </tr>
        `);
        e_table.innerHTML = `<thead>
        <tr>
        <th class="c1">Amount</th> <th class="c2">Type</th> <th class="c3">Submitted</th> <th class="c4">Resolved</th> <th class="c5">Status</th>
        </tr> </thead><tbody>` + reimb_lines.join('') + "</tbody>";
        
        
    })
}

update();