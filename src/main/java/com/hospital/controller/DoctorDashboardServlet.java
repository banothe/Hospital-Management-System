package com.hospital.controller;

import java.io.IOException;
import java.sql.*;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.hospital.model.User;
import com.hospital.util.DBConnection;

@WebServlet("/DoctorDashboardServlet")
public class DoctorDashboardServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("currentUser");

		// check login
		if (user == null || !user.getRole().equals("DOCTOR")) {
			response.sendRedirect("login.jsp");
			return;
		}

		int doctorId = user.getReferenceId();

		List<Map<String, String>> list = new ArrayList<>();

		try {
			Connection conn = DBConnection.getConnection();

			String sql = "SELECT A.APPOINTMENT_ID, P.NAME AS PATIENT_NAME, "
			        + "A.APPOINTMENT_DATE, A.APPOINTMENT_TIME, A.STATUS "
			        + "FROM APPOINTMENT A "
			        + "LEFT JOIN PATIENT P ON A.PATIENT_ID = P.PATIENT_ID "
			        + "WHERE A.DOCTOR_ID=?";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, doctorId);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Map<String, String> map = new HashMap<>();

				map.put("id", rs.getString("APPOINTMENT_ID"));
				map.put("patient", rs.getString("PATIENT_NAME"));
				map.put("date", rs.getString("APPOINTMENT_DATE"));
				map.put("time", rs.getString("APPOINTMENT_TIME"));
				map.put("status", rs.getString("STATUS"));

				list.add(map);
			}

			request.setAttribute("appointmentList", list);
			request.getRequestDispatcher("doctorDashboard.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}