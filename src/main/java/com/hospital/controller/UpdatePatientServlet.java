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

@WebServlet("/UpdatePatientServlet")
public class UpdatePatientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int id =Integer.parseInt(request.getParameter("id"));
			String name=request.getParameter("name");
			String ageStr=request.getParameter("age");
			String gender=request.getParameter("gender");
			String phone=request.getParameter("phone");
			String email=request.getParameter("email");
			
			Integer age=null;
			if(ageStr != null && !ageStr.isEmpty()) {
				age=Integer.parseInt(ageStr);
			}
			Connection conn=DBConnection.getConnection();
			String sql ="update patient set name=? ,age=? ,gender=? ,phone=? ,email=? where patient_id=?";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1,name);
			if(age != null) {
				ps.setInt(2,age);
			}else {
				ps.setNull(2,java.sql.Types.INTEGER);
			}
			ps.setString(3,gender);
			ps.setString(4,phone);
			ps.setString(5, email);
			ps.setInt(6,id);
			ps.executeUpdate();
			
			//redirect back to list 
			response.sendRedirect("ViewPatientServlet");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
