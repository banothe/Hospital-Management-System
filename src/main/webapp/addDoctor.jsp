<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.hospital.model.User" %>
<% User user=(User) session.getAttribute("currentUser");
if(user==null || !user.getRole().equals("ADMIN")){
	response.sendRedirect("login.jsp");
	return ;
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Doctor</title>
</head>
<body>
<h2>Add Doctor</h2>
<form action="AddDoctorServlet" method="post">

<label>Name:</label>
<input type="text" name="name" required><br><br>

<label>Specialization:</label>
<input type="text" name="specialization" required><br><br>

<label>Email:</label>
<input type="email" name="email"><br><br>

<label>Phone:</label>
<input type="text"  name="phone"><br><br>

<button type="submit">save button</button>

</form>
<br>
<a href="adminDashboard.jsp">Back</a>
</body>
</html>