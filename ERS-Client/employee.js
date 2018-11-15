var table;

var reimbursements = [];
var reimb_lines = [];
var selected_line;

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

document.addEventListener("DOMContentLoaded", function() {
    table = document.getElementById("table");
    loadReimbursements();
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