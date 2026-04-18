<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.* , com.hospital.model.Doctor" %>
<%
    com.hospital.model.User user =
        (com.hospital.model.User) session.getAttribute("currentUser");

    if (user == null || !user.getRole().equals("ADMIN")) {
        response.sendRedirect("login.jsp");
        return;
    }
    List<Doctor> doctorList = (List<Doctor>) request.getAttribute("doctorList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View Doctors</title>
</head>
<body>
<h2>Doctor List</h2>
<% if (doctorList == null || doctorList.isEmpty()) { %>

    <p>No doctors found.</p>

<% } else { %>
<table border="1">
<tr>
<th>ID</th>
<th>Name</th>
<th>Specialization</th>
<th>Email</th>
<th>Phone</th>
<th>Action</th>
</tr>

<%for (Doctor d:doctorList){ %>
<tr>
<td><%= d.getDoctorId() %></td>
<td><%= d.getName() %></td>
<td><%= d.getSpecialization() %></td>
<td><%= d.getEmail() %></td>
<td><%= d.getPhone() %></td>
<td> 
<a href="EditDoctorServlet?id=<%= d.getDoctorId() %>">Edit</a> |
<a href="DeleteDoctorServlet?id=<%= d.getDoctorId() %>"
onclick="return confirm('Are you sure you want to delete this doctor?');">Delete</a>
</td>
</tr>
<% } %>
</table>
<% } %>

<br><br>
<a href="adminDashboard.jsp">Back</a>

</body>
</html>