package com.hospital.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.hospital.model.User;
import com.hospital.util.DBConnection;

@WebServlet("/UpdateAppointmentStatusServlet")
public class UpdateAppointmentStatusServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("currentUser");

			// 🔒 Check login + role
			if (user == null || !user.getRole().equals("DOCTOR")) {
				response.sendRedirect("login.jsp");
				return;
			}

			int doctorId = user.getReferenceId();
			int id = Integer.parseInt(request.getParameter("id"));
			String status = request.getParameter("status");

			Connection conn = DBConnection.getConnection();

			// 🔐 Secure update (VERY IMPORTANT)
			String sql = "UPDATE APPOINTMENT SET STATUS=? WHERE APPOINTMENT_ID=? AND DOCTOR_ID=?";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, status);
			ps.setInt(2, id);
			ps.setInt(3, doctorId);

			ps.executeUpdate();

			response.sendRedirect("DoctorDashboardServlet");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}