function login(event) {
  event.preventDefault();
  const username = document.getElementById('inputUsername').value;
  const password = document.getElementById('inputPassword').value;
  const roleString = document.getElementById('inputRole').value;
  const role;
  if (roleString === "Finance Manager") {
      role = "FINANCE";
  } else {
      role = "EMPLOYEE";
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