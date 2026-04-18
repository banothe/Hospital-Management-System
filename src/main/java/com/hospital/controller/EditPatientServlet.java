package com.hospital.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hospital.util.DBConnection;
import com.hospital.model.Patient;

@WebServlet("/EditPatientServlet")
public class EditPatientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id=request.getParameter("id");
		try {
			Connection conn=DBConnection.getConnection();
			String sql="Select * from patient where patient_id=?";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1,Integer.parseInt(id));
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				Patient p=new Patient();
				p.setPatientId(rs.getInt("Patient_id"));
				p.setName(rs.getString("name"));
				p.setAge(rs.getInt("AGE"));
				p.setGender(rs.getString("GENDER"));
				p.setPhone(rs.getString("PHONE"));
				p.setEmail(rs.getString("EMAIL"));
				
				request.setAttribute("patient", p);
				request.getRequestDispatcher("editPatient.jsp").forward(request, response);
			}
		}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
}
