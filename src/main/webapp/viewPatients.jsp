<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<%@ page import="java.util.List, com.hospital.model.Patient, com.hospital.model.User" %>

<%
User user = (User) session.getAttribute("currentUser");

if (user == null || !user.getRole().equals("ADMIN")) {
response.sendRedirect("login.jsp");
return;
}

List<Patient> patientList = (List<Patient>) request.getAttribute("patientList");
%>

<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>View Patients</title>
</head>

<body>

<h2>Patient List</h2>
Total Patients: <%= patientList == null ? 0 : patientList.size() %>
<a href="adminDashboard.jsp">Back</a>

<br><br>

<% if (patientList == null || patientList.isEmpty()) { %>

```
<p>No patients found.</p>
```

<% } else { %>

<table border="1">
<tr>
    <th>ID</th>
    <th>Name</th>
    <th>Age</th>
    <th>Gender</th>
    <th>Phone</th>
    <th>Email</th>
    <th>Action</th>
</tr>

<% for (Patient p : patientList) { %>

<tr>
    <td><%= p.getPatientId() %></td>
    <td><%= p.getName() %></td>
    <td><%= p.getAge() %></td>
    <td><%= p.getGender() %></td>
    <td><%= p.getPhone() %></td>
    <td><%= p.getEmail() %></td>

```
<td>
    <a href="EditPatientServlet?id=<%= p.getPatientId() %>">Edit</a> |
    <a href="DeletePatientServlet?id=<%= p.getPatientId() %>" onclick="return confirm('Are you sure you want to delete this patient ?');">Delete</a>
</td>
```

</tr>
<% } %>

</table>

<% } %>

</body>
</html>
