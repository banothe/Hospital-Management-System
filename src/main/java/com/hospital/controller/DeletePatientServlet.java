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

/**
 * Servlet implementation class DeletePatientServlet
 */
@WebServlet("/DeletePatientServlet")
public class DeletePatientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("id");
		try {
			Connection conn=DBConnection.getConnection();
			conn.setAutoCommit(true);
			String sql="Delete from patient where patient_id=?";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(id));
			ps.executeUpdate();
			response.sendRedirect("ViewPatientServlet");
			conn.commit();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
