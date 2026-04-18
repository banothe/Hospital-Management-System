package com.hospital.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.hospital.util.DBConnection;

@WebServlet("/UpdateStatusServlet")
public class UpdateStatusServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			int id = Integer.parseInt(request.getParameter("id"));
			String status = request.getParameter("status");

			Connection conn = DBConnection.getConnection();

			String sql = "UPDATE APPOINTMENT SET STATUS=? WHERE APPOINTMENT_ID=?";
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, status);
			ps.setInt(2, id);

			ps.executeUpdate();

			conn.commit();

			response.sendRedirect("ViewAppointmentServlet");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}