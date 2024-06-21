<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
String username = (String ) session.getAttribute("username");
if (username == null){
response.sendRedirect("Login.jsp");

}
%>
<h1>Welcome , <%= username %> !</h1>
<a href="Logout">Logout</a>
</body>
</html>