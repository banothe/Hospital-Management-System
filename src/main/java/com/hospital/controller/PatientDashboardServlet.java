package com.hospital.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hospital.model.User;
import com.hospital.util.DBConnection;

/**
 * Servlet implementation class PatientDashboardServlet
 */
@WebServlet("/PatientDashboardServlet")
public class PatientDashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session =request.getSession();
		User user=(User) session.getAttribute("currentUser");
		
		//Check login
		if(user==null || !user.getRole().equals("PATIENT")) {
			response.sendRedirect("login.jsp");
			return;
		}
		int PatientId=user.getReferenceId();
		List<Map<String,String>> list=new ArrayList<>();
		try {
			Connection conn=DBConnection.getConnection();
			String sql="SELECT A.APPOINTMENT_ID, D.NAME AS DOCTOR_NAME, "
					+ " A.APPOINTMENT_DATE ,A.APPOINTMENT_TIME, A.STATUS"
					+ " FROM APPOINTMENT A"
					+ " LEFT JOIN DOCTOR D  ON A.DOCTOR_ID=D.DOCTOR_ID "
					+ " WHERE A.PATIENT_ID=?";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, PatientId);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				Map<String ,String > map=new HashMap<>();
				map.put("id",rs.getString("APPOINTMENT_ID"));
				map.put("doctor",rs.getString("DOCTOR_NAME"));
				map.put("date",rs.getString("APPOINTMENT_DATE"));
				map.put("time",rs.getString("APPOINTMENT_TIME"));
				map.put("status",rs.getString("STATUS"));
				list.add(map);
			}
			request.setAttribute("appointmentList",list);
			request.getRequestDispatcher("patientDashboard.jsp").forward(request, response);
	}
		catch(Exception e)
		{
			e.printStackTrace();
		}
}
}