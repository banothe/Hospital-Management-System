<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<%@ page import="com.hospital.model.Patient, com.hospital.model.User" %>

<%
User user = (User) session.getAttribute("currentUser");

if (user == null || !user.getRole().equals("ADMIN")) {
response.sendRedirect("login.jsp");
return;
}

Patient p = (Patient) request.getAttribute("patient");
%>

<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>Edit Patient</title>
</head>

<body>

<h2>Edit Patient</h2>

<a href="ViewPatientServlet">Back</a>

<br><br>

<form action="UpdatePatientServlet" method="post">

<input type="hidden" name="id" value="<%= p.getPatientId() %>">

<label>Name:</label><br> <input type="text" name="name" value="<%= p.getName() %>" required> <br><br>

<label>Age:</label><br> <input type="number" name="age" value="<%= p.getAge() %>"> <br><br>

<label>Gender:</label><br> <select name="gender">
<option value="Male" <%= p.getGender().equals("Male") ? "selected" : "" %>>Male</option>
<option value="Female" <%= p.getGender().equals("Female") ? "selected" : "" %>>Female</option>
<option value="Other" <%= p.getGender().equals("Other") ? "selected" : "" %>>Other</option> </select> <br><br>

<label>Phone:</label><br> <input type="text" name="phone" value="<%= p.getPhone() %>"> <br><br>

<label>Email:</label><br> <input type="email" name="email" value="<%= p.getEmail() %>"> <br><br>

<button type="submit">Update Patient</button>

</form>

</body>
</html>
