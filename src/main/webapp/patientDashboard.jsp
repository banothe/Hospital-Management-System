<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
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
<title>Patient Dashboard</title>
</head>
<body>
<H1>Patient Dashboard</H1>
<h3>Welcome Patient , <%= user.getUsername() %> </h3>
<a href="LogoutServlet">Logout</a>
</body>
</html> --%>

<%@ page import="java.util.*" %>
<%@ page import="com.hospital.model.User" %>

<%
User user = (User) session.getAttribute("currentUser");

if (user == null || !user.getRole().equals("PATIENT")) {
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
<title>Patient Dashboard</title>
</head>
<body>

<h2>My Appointments</h2>

<% if (list == null || list.isEmpty()) { %>
    <p>No appointments found.</p>
<% } else { %>

<table border="1">
<tr>
    <th>ID</th>
    <th>Doctor</th>
    <th>Date</th>
    <th>Time</th>
    <th>Status</th>
</tr>

<% for (Map<String, String> a : list) { %>
<tr>
<td><%= a.get("id") %></td>
<td><%= a.get("doctor") %></td>
<td><%= a.get("date") %></td>
<td><%= a.get("time") %></td>
<td><%= a.get("status") %></td>
</tr>
<% } %>

</table>

<% } %>

<br><br>
<a href="logout.jsp">Logout</a>

</body>
</html>