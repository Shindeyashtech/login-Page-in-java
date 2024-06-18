<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registration page</title>
</head>
<body>
<form Method="post" action="Registration">
<div class="container">

  <label>Name : </label>   
  <input type="text" name="RegistrationName"  />
  <label>UserName : </label>   
  <input type="text" name="RegistrationUserName"  />
  <label>Email : </label>   
  <input type="email" name="RegistrationEmail"  />
  <label>Mobile No. : </label>   
  <input type="tel" name="RegistrationNumber"  />
  <label>Enter Your Password : </label>   
  <input type="password" name="RegistrationPassword"  />
  <label>Confirm Your Password : </label>   
  <input type="password" name="RegistrationConfirmPassword"  />
   
</div>
</form>
</body>
</html>