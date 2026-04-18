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

@WebServlet("/AddPatientServlet")
public class AddPatientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;     
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			String name =request.getParameter("name");
			String ageStr=request.getParameter("age");
			String gender=request.getParameter("gender");
			String phone=request.getParameter("phone");
			String email=request.getParameter("email");
		
			//Handle age (can be null)
			Integer age=null;
			if(ageStr !=null && !ageStr.isEmpty()) {
				age=Integer.parseInt(ageStr);
			}
			Connection conn=DBConnection.getConnection();
			conn.setAutoCommit(true);
			String sql="Insert into patient(name,age,gender,phone,email)" 
					+ "values (?,?,?,?,?)"; 
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1,name);
			if(age!=null) {
				ps.setInt(2,age);
			}else {
				ps.setNull(2,java.sql.Types.INTEGER);
			}
			ps.setString(3,gender);
			ps.setString(4, phone);
			ps.setString(5,email);
			ps.executeUpdate();
			//redirect after success
			response.sendRedirect("addPatient.jsp");	
			System.out.println(name + " " + ageStr + " " + gender + " " + phone + " " + email);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}