package com.hospital.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hospital.util.DBConnection;


@WebServlet("/UpdateAppointmentStatusServlet")
public class UpdateAppointmentStatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id =Integer.parseInt(request.getParameter("id"));
		String status=request.getParameter("status");
		try {
			Connection conn=DBConnection.getConnection();
			String sql="UPDATE APPOINTMENT SET STATUS=? WHERE APPOINTMENT_ID=? ";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1,status);
			ps.setInt(2, id);
			ps.executeUpdate();
			response.sendRedirect("DoctorDashboardServlet");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
