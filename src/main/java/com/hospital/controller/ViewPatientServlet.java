package com.hospital.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hospital.model.Patient;
import com.hospital.util.DBConnection;

@WebServlet("/ViewPatientServlet")
public class ViewPatientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Patient> patientList =new ArrayList<>();
		try {
			Connection conn=DBConnection.getConnection();
			String sql="select * from SYSTEM.patient";
			PreparedStatement ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				Patient p=new Patient();
				p.setPatientId(rs.getInt("PATIENT_ID"));
				p.setName(rs.getString("NAME"));
				p.setAge(rs.getInt("AGE"));
				p.setGender(rs.getString("GENDER"));
				p.setPhone(rs.getString("PHONE"));
				p.setEmail(rs.getString("EMAIL"));
				patientList.add(p);
			}
			System.out.println("Total Patients: " + patientList.size());
			request.setAttribute("patientList",patientList);
			request.getRequestDispatcher("viewPatients.jsp").forward(request, response);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}