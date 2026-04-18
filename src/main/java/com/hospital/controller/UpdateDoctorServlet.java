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
 * Servlet implementation class UpdateDoctorServlet
 */
@WebServlet("/UpdateDoctorServlet")
public class UpdateDoctorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try
		{
			int id =Integer.parseInt(request.getParameter("id"));
			String name=request.getParameter("name");
			String specialization=request.getParameter("specialization");
			String email=request.getParameter("email");
			String phone=request.getParameter("phone");
			Connection conn =DBConnection.getConnection();
			String sql="update doctor set name=? ,specialization=? ,email=? ,phone=? where doctor_id=?";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1,name);
			ps.setString(2, specialization);
			ps.setString(3, email);
			ps.setString(4, phone);
			ps.setInt(5, id);
			ps.executeUpdate();
		    response.sendRedirect("ViewDoctorServlet");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}