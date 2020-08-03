<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Add Form</title>
  </head>
  <style>
    form {
      display: flex;
      flex-direction: column;
      width: 300px;
    }

    form input {
      margin-bottom: 20px;
      padding: 10px;
    }

    button {
      width: 300px;
    }
  </style>
  <body>
    <button id="return" onclick="window.location.href='/customer/list'">
      return to customer list
    </button>

    <br /><br />
    Add Customer Form
    <br />
    <br />

    <form id="addForm" action="http://localhost:8080/customer/" method="POST">
      <input type="text" name="firstName" placeholder="first name" />
      <input type="text" name="lastName" placeholder="last name" />
      <input type="text" name="email" placeholder="email" />
      <button id="submitButton">Submit</button>
      <br />
    </form>

    <script>
      document.getElementById("submitButton").onclick = function (e) {
        e.preventDefault();
        let formData = new FormData(document.getElementById("addForm"));
        let firstName = formData.get("firstName");
        let lastName = formData.get("lastName");
        let email = formData.get("email");

        fetch("http://localhost:8080/customer/", {
          body: JSON.stringify({
            firstName: firstName,
            lastName: lastName,
            email: email
          }),
          headers: {
            "content-type": "application/json"
          },
          method: "POST"
        }).then(function (data) {
          console.log(data);
          window.location.href = "/customer/list";
        });
      };
    </script>
  </body>
</html>
