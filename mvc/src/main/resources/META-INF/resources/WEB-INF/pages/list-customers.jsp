<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <style>
      th {
        text-align: left;
      }
    </style>
    <title>Customers</title>
  </head>
  <body>
    <div>
      list of customers:
    </div>
    <br />

    <button onclick="window.location.href='/customer/add-form'">Add More Customer</button>
    <br />
    <br />
    <table>
      <thead>
        <tr>
          <th>
            First Name
          </th>
          <th>
            Last Name
          </th>
          <th>
            Email
          </th>
        </tr>
      </thead>
      <tbody>
        <c:forEach var="customer" items="${customers}">
          <tr>
            <td>${customer.firstName}</td>
            <td>${customer.lastName}</td>
            <td>${customer.email}</td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
  </body>
</html>
