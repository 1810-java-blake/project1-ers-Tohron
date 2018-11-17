const id = 100;
const resolverID = 11;
const status = "APPROVED";

const reimb_res = {
    id,
    resolverID,
    status
}
console.log(reimb_res);

fetch('http://localhost:8080/ERS/finance', {
method: 'POST',
body: JSON.stringify(reimb_res),
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