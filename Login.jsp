<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>
</head>
<body>
<form method="post" action="Login">
<label for="username" >UserName</label>
<input type="text" name="username" />
<label for="username" > Password</label>
<input type="text" name="password" />
<button type="submit" >Login</button>
</form>
<h2>Welcome ${username} in ! </h2>
</body>
</html>