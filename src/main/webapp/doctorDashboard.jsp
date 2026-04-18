<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page import="java.util.*, com.hospital.model.User" %>
<%
User user = (User) session.getAttribute("currentUser");
if (user == null || !user.getRole().equals("DOCTOR")) {
response.sendRedirect("login.jsp");
return;
}
List<Map<String, String>> list =
(List<Map<String, String>>) request.getAttribute("appointmentList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Doctor Dashboard</title>
</head>
<body>
<h2>Doctor Dashboard</h2>
<br><br>
<a href="login.jsp" >Back</a>
<% if (list == null || list.isEmpty()) { %>
<p>No appointments assigned.</p>

<% } else { %>

<table border="1">
<tr>
<th>ID</th>
<th>Patient</th>
<th>Date</th>
<th>Time</th>
<th>Status</th>
</tr>
<% for (Map<String, String> a : list) { %>
<tr>
<td><%= a.get("id") %></td>
<td><%= a.get("patient") %></td>
<td><%= a.get("date") %></td>
<td><%= a.get("time") %></td>
<td><%= a.get("status") %></td>
<td>
<a href="UpdateAppointmentStatusServlet?id=<%= a.get("id") %>&status=APPROVED">Approve</a>
|
<a href="UpdateAppointmentStatusServlet?id=<%= a.get("id") %>&status=REJECTED">Reject</a>
</td>
</tr>
<% } %>
</table>
<% } %>
</body>
</html>