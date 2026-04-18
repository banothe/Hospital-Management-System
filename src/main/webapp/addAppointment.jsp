<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<%@ page import="java.util.*, com.hospital.model.*" %>

<%
List<Patient> patientList = (List<Patient>) request.getAttribute("patientList");
List<Doctor> doctorList = (List<Doctor>) request.getAttribute("doctorList");
%>

<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>Book Appointment</title>
</head>

<body>

<h2>Book Appointment</h2>

<a href="adminDashboard.jsp">Back</a>

<br><br>
<%
String error = request.getParameter("error");
if (error != null) {
%>
<p style="color:red;"><%= error %></p>
<%
}
%>
<form action="SaveAppointmentServlet" method="post">

<label>Patient:</label><br> <select name="patientId" required> <option value="">Select Patient</option>

<% for (Patient p : patientList) { %> <option value="<%= p.getPatientId() %>">
<%= p.getName() %> </option>
<% } %> </select> <br><br>

<label>Doctor:</label><br> <select name="doctorId" required> <option value="">Select Doctor</option>
<% for (Doctor d : doctorList) { %> <option value="<%= d.getDoctorId() %>">
<%= d.getName() %> (<%= d.getSpecialization() %>) </option>
<% } %> </select> <br><br>

<label>Date:</label><br> <input type="date" name="appointmentDate" required> <br><br>

<label>Time:</label><br> <input type="time" name="appointmentTime" required> <br><br>

<button type="submit">Book Appointment</button>

</form>

</body>
</html>
