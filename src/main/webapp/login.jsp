<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>
</head>
<body>
<H1>Hospital Login</H1>

<a href="Welcome.html">Home page</a>

<form action="LoginServlet" method="Post">

<div>
<label 	for="name" >Name:</label>
<input type="text" id="name" name="username"> 
</div>

<div>
<label for="password">Password:</label>
<input type="password" id="password" name="password">
</div>

<div>
<button type="submit">Submit</button>
</div>

</form>

</body>
</html>