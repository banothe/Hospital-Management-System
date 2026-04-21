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
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<meta charset="UTF-8">
<title>Doctor Dashboard</title>
</head>
<body class="bg-light">

<div class="container mt-4">

    <h2 class="text-center mb-4">Doctor Dashboard</h2>

    <div class="d-flex justify-content-between mb-3">
        <h5>Welcome Doctor</h5>
        <a href="LogoutServlet" class="btn btn-danger">Logout</a>
    </div>

    <!-- 🔍 Search Form -->
    <form method="get" action="DoctorDashboardServlet" class="row g-3 mb-4">

        <div class="col-md-3">
            <input type="text" class="form-control" name="name"
                   placeholder="Search Patient"
                   value="<%= request.getParameter("name") != null ? request.getParameter("name") : "" %>">
        </div>

        <div class="col-md-3">
            <select name="status" class="form-select">
                <option value="">All Status</option>
                <option value="PENDING">Pending</option>
                <option value="APPROVED">Approved</option>
                <option value="COMPLETED">Completed</option>
                <option value="REJECTED">Rejected</option>
            </select>
        </div>

        <div class="col-md-3">
            <input type="date" name="date" class="form-control"
                   value="<%= request.getParameter("date") != null ? request.getParameter("date") : "" %>">
        </div>

        <div class="col-md-3">
            <button type="submit" class="btn btn-primary w-100">Search</button>
        </div>

    </form>

    <!-- 📋 Table -->
    <table class="table table-bordered table-striped text-center">

        <thead class="table-dark">
            <tr>
                <th>ID</th>
                <th>Patient</th>
                <th>Date</th>
                <th>Time</th>
                <th>Status</th>
                <th>Action</th>
            </tr>
        </thead>

        <tbody>

        <% if (list == null || list.isEmpty()) { %>
            <tr>
                <td colspan="6">No appointments found</td>
            </tr>
        <% } else { %>

        <% for (Map<String, String> a : list) { %>
            <tr>
                <td><%= a.get("id") %></td>
                <td><%= a.get("patient") %></td>
                <td><%= a.get("date") %></td>
                <td><%= a.get("time") %></td>

                <!-- 🎨 Colored Status -->
                <td>
                    <% String status = a.get("status"); %>

                    <% if ("PENDING".equals(status)) { %>
                        <span class="badge bg-warning text-dark">PENDING</span>
                    <% } else if ("APPROVED".equals(status)) { %>
                        <span class="badge bg-success">APPROVED</span>
                    <% } else if ("REJECTED".equals(status)) { %>
                        <span class="badge bg-danger">REJECTED</span>
                    <% } else { %>
                        <span class="badge bg-secondary"><%= status %></span>
                    <% } %>
                </td>

                <td>
                    <a href="UpdateAppointmentStatusServlet?id=<%= a.get("id") %>&status=APPROVED"
                       class="btn btn-success btn-sm">Approve</a>

                    <a href="UpdateAppointmentStatusServlet?id=<%= a.get("id") %>&status=REJECTED"
                       class="btn btn-danger btn-sm">Reject</a>
                </td>
            </tr>
        <% } %>

        <% } %>

        </tbody>
    </table>

</div>

</body>
</html>