<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%
List<Map<String, String>> appointmentList =
(List<Map<String, String>>) request.getAttribute("appointmentList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View Appointments</title>
</head>

<body>

<h2>Appointment List</h2>

<a href="adminDashboard.jsp">Back</a>

<br><br>
<form method="get" action="ViewAppointmentServlet">

Search Patient:
<input type="text" name="name"
value="<%= request.getParameter("name") != null ? request.getParameter("name") : "" %>">

Status:
<select name="status">
    <option value="">All</option>
    <option value="PENDING">Pending</option>
    <option value="APPROVED">Approved</option>
    <option value="COMPLETED">Completed</option>
</select>

Date:
<input type="date" name="date"
value="<%= request.getParameter("date") != null ? request.getParameter("date") : "" %>">

<button type="submit">Search</button>

</form>

<br><br>
<% if (appointmentList == null || appointmentList.isEmpty()) { %>

<p>No appointments found.</p>

<% } else { %>

<table border="1">
<tr>
<th>ID</th>
<th>Patient</th>
<th>Doctor</th>
<th>Date</th>
<th>Time</th>
<th>Status</th>
<th>Action</th>
</tr>

<% for (Map<String, String> a : appointmentList) { %>

<tr>
<td><%= a.get("id") %></td>
<td><%= a.get("patient") %></td>
<td><%= a.get("doctor") %></td>
<td><%= a.get("date") %></td>
<td><%= a.get("time") %></td>
<td><%= a.get("status") %></td>
<td>
<a href="UpdateStatusServlet?id=<%= a.get("id") %>&status=APPROVED">Approve</a> |
<a href="UpdateStatusServlet?id=<%= a.get("id") %>&status=COMPLETED">Complete</a>
</td>
</tr>
<% } %>
</table>
<% } %>
</body>
</html>