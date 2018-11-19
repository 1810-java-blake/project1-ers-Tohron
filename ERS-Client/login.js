function login(event) {
  event.preventDefault();
  const username = document.getElementById('inputUsername').value;
  const password = document.getElementById('inputPassword').value;
  const roleSelect = document.getElementById('inputRole');
  const roleString = roleSelect.options [roleSelect.selectedIndex] .value;
  //console.log(roleSelect.options [roleSelect.selectedIndex] .value); // is e or f
  var role;
  if (roleString === "f") {
      role = "FINANCE";
      console.log("Logging in as finance...");
  } else {
      role = "EMPLOYEE";
      console.log("Logging in as employee...");
  }

  const cred = {
    username,
    password,
    role
  }
  console.log(cred);

  fetch('http://localhost:8080/ERS/login', {
    method: 'POST',
    body: JSON.stringify(cred),
    headers: {
      'Content-Type': 'application/json'
    },
    credentials: 'include'
  })
    .then(res => {
      console.log("Response: " + res);
      if (res.status === 200) {
        if (role === "FINANCE") {
          window.location = '../finance.html'
        } else {
          window.location = '../employee.html'
        }
      }
    })
    .catch(err => {
      console.log(err);
    })
}