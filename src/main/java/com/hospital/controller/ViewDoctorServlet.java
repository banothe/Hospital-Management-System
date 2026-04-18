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

import com.hospital.model.Doctor;
import com.hospital.util.DBConnection;

/**
 * Servlet implementation class viewDoctorServlet
 */
@WebServlet("/ViewDoctorServlet")
public class ViewDoctorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
			List<Doctor> doctorList=new ArrayList<>();
		try {
			Connection conn=DBConnection.getConnection();
			String sql="Select * from doctor";
			PreparedStatement ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				Doctor d=new Doctor();
				d.setDoctorId(rs.getInt("doctor_id"));
				d.setName(rs.getString("name"));
				d.setSpecialization(rs.getString("specialization"));
				d.setEmail(rs.getString("email"));
				d.setPhone(rs.getString("phone"));
				doctorList.add(d);
			}
			request.setAttribute("doctorList",doctorList);
			request.getRequestDispatcher("viewDoctor.jsp").forward(request,response);
		  }
		catch (Exception e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}}
		}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	/*
	 * protected void doPost(HttpServletRequest request, HttpServletResponse
	 * response) throws ServletException, IOException { // TODO Auto-generated
	 * method stub doGet(request, response); }
	 */
