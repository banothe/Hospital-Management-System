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
 * Servlet implementation class AddDoctorServlet
 */
@WebServlet("/AddDoctorServlet")
public class AddDoctorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String name =request.getParameter("name");
		String specialization=request.getParameter("specialization");
		String email=request.getParameter("email");
		String phone=request.getParameter("phone");
		System.out.println("Phone value: " + phone);
		
		try {
			Connection conn =DBConnection.getConnection();
			String sql = "INSERT INTO DOCTOR (NAME, SPECIALIZATION, EMAIL, PHONE) VALUES (?, ?, ?, ?)";
			PreparedStatement ps= conn.prepareStatement(sql);
			ps.setString(1,name);
			ps.setString(2,specialization);
			ps.setString(3,email);
			ps.setString(4,phone);
			
			ps.executeUpdate();
			response.sendRedirect("adminDashboard.jsp");
		}
		catch(Exception e) {
			e.printStackTrace();
		
		}
		
	}

}
