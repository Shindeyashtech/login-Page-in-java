<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registration Page</title>
<style>
    .container {
        width: 300px;
        margin: 0 auto;
    }
    .container label, .container input, .container button {
        display: block;
        margin-bottom: 10px;
    }
    .message {
        color: red;
        font-weight: bold;
    }
</style>

</head>
<body>
<div class="container">
    <% 
        String message = request.getParameter("message");
        if (message != null) {
    %>
    <p class="message"><%= message %></p>
    <% 
        }
    %>
    <form method="post" action="Registration">
        <label>Name:</label>
        <input type="text" name="RegistrationName" required />
        <label>UserName:</label>
        <input type="text" name="RegistrationUserName" required />
        <label>Email:</label>
        <input type="email" name="RegistrationEmail" required />
        <label>Mobile No.:</label>
        <input type="tel" name="RegistrationNumber" required />
        <label>Enter Your Password:</label>
        <input type="password" name="RegistrationPassword" required />
        <label>Confirm Your Password:</label>
        <input type="password" name="RegistrationConfirmPassword" required />
        <button type="submit">Sign Up</button>
    </form>
</div>
</body>
</html>