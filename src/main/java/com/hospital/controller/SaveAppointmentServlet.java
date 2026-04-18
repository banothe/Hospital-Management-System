package com.hospital.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.hospital.util.DBConnection;

@WebServlet("/SaveAppointmentServlet")
public class SaveAppointmentServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			int patientId = Integer.parseInt(request.getParameter("patientId"));
			int doctorId = Integer.parseInt(request.getParameter("doctorId"));
			String date = request.getParameter("appointmentDate");
			String time = request.getParameter("appointmentTime");

			Connection conn = DBConnection.getConnection();
			String checkSql = "SELECT * FROM APPOINTMENT "
			        + "WHERE DOCTOR_ID=? AND APPOINTMENT_DATE=? AND APPOINTMENT_TIME=?";

			PreparedStatement checkPs = conn.prepareStatement(checkSql);
			checkPs.setInt(1, doctorId);
			checkPs.setDate(2, java.sql.Date.valueOf(date));
			checkPs.setString(3, time);

			ResultSet checkRs = checkPs.executeQuery();

			if (checkRs.next()) {
			    // 🚫 Slot already booked
			    response.sendRedirect("AddAppointmentServlet?error=Slot already booked");
			    return;
			}

			String sql = "INSERT INTO APPOINTMENT (PATIENT_ID, DOCTOR_ID, APPOINTMENT_DATE, APPOINTMENT_TIME, STATUS) VALUES (?, ?, ?, ?, ?)";

			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(1, patientId);
			ps.setInt(2, doctorId);
			ps.setDate(3, java.sql.Date.valueOf(date));
			ps.setString(4, time);
			ps.setString(5, "PENDING"); // default status

			ps.executeUpdate();

			 // 🔥 important

			response.sendRedirect("ViewAppointmentServlet");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}