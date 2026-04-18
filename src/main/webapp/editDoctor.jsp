<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.hospital.model.Doctor" %>
<%@ page import="com.hospital.model.User" %>
<%
//Session protection
User user=(User)session.getAttribute("currentUser");
if(user==null||!user.getRole().equals("ADMIN")){
	response.sendRedirect("login.jsp");
	return;
}
Doctor d=(Doctor)request.getAttribute("doctor");
%>
<h2>Edit Doctor</h2>
<form action="UpdateDoctorServlet" method="post">
<input type="hidden" name="id" value="<%= d.getDoctorId() %>">
<div>
<label>Name:</label>
<input type="text" name="name" value=<%= d.getName() %> required >
<br><br>
</div>
<div></div>
<label>Specialization:</label>
<input type="text" name="specialization" value="<%= d.getSpecialization() %>" >
<br><br>

<div>
<label>Email:</label>
<input type="email" name="email" value="<%= d.getEmail() %>">
<br><br>
</div>
<div>
<label>Phone:</label>
<input type="text" name="phone" value="<%= d.getPhone() %>" >
<br> <br>
</div>
<div>
<button type="submit" > Update Doctor</button>
</div>
</form>