<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    com.hospital.model.User user =
        (com.hospital.model.User) session.getAttribute("currentUser");

    if (user == null || !user.getRole().equals("ADMIN")) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Dashboard</title>
</head>
<body>
<h1>admin dashboard</h1>
<h3>Welcome Admin ,<%= user.getUsername() %> </h3>
<br><br>
<a href="addDoctor.jsp">Add Doctor</a><br>
<a href="ViewDoctorServlet">View Doctors</a><br>
<a href="ViewPatientServlet">View Patients</a><br>
<a href="addPatient.jsp">Add Patient</a><br>
<a href="AddAppointmentServlet">Add Appointment</a><br>
<a href="ViewAppointmentServlet">View Appointment</a><br>
<a href="DoctorDashboardServlet">Doctor Dashboard</a><br>
<a href="PatientDashboardServlet">Patient Dashboard</a><br>
<a href="LogoutServlet">Logout</a>
</body>
</html>