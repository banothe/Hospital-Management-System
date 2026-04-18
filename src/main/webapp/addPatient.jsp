<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<%@ page import="com.hospital.model.User" %>

<%
User user = (User) session.getAttribute("currentUser");
if (user == null || !user.getRole().equals("ADMIN")) {
response.sendRedirect("login.jsp");
return;
}
%>

<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>Add Patient</title>
</head>

<body>

<h2>Add Patient</h2>

<a href="adminDashboard.jsp">Back</a>

<br><br>

<form action="AddPatientServlet" method="post">

Name:<br> <input type="text" name="name" required> <br><br>

Age:<br> <input type="number" name="age"> <br><br>

Gender:<br> <select name="gender">

<option value="Male">Male</option>
<option value="Female">Female</option>
<option value="Other">Other</option>
</select>
<br><br>

Phone:<br> <input type="text" name="phone"> <br><br>

Email:<br> <input type="email" name="email"> <br><br>

<button type="submit">Add Patient</button>
</form>
</body>
</html>