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

import com.hospital.util.DBConnection;

@WebServlet("/ViewAppointmentServlet")
public class ViewAppointmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Map<String,String>> appointmentList=new ArrayList<>();
		try {
			Connection conn=DBConnection.getConnection();
			String name = request.getParameter("name");
			String status = request.getParameter("status");
			String date = request.getParameter("date");

			String sql = "select a.appointment_id , p.name as patient_name , d.name as doctor_name, "
			        + "a.appointment_date , a.appointment_time , a.status "
			        + "from appointment a "
			        + "join patient p on a.patient_id=p.patient_id "
			        + "join doctor d on a.doctor_id=d.doctor_id "
			        + "where 1=1";

			if (name != null && !name.isEmpty()) {
			    sql += " AND LOWER(p.name) LIKE ?";
			}

			if (status != null && !status.isEmpty()) {
			    sql += " AND a.status = ?";
			}

			if (date != null && !date.isEmpty()) {
			    sql += " AND a.appointment_date = ?";
			}

			PreparedStatement ps = conn.prepareStatement(sql);

			int i = 1;

			if (name != null && !name.isEmpty()) {
			    ps.setString(i++, "%" + name.toLowerCase() + "%");
			}

			if (status != null && !status.isEmpty()) {
			    ps.setString(i++, status);
			}

			if (date != null && !date.isEmpty()) {
			    ps.setDate(i++, java.sql.Date.valueOf(date));
			}

			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				Map<String,String> map=new HashMap<>();
				map.put("id",rs.getString("appointment_id"));
				map.put("patient", rs.getString("PATIENT_NAME"));
				map.put("doctor", rs.getString("DOCTOR_NAME"));
				map.put("date", rs.getString("APPOINTMENT_DATE"));
				map.put("time", rs.getString("APPOINTMENT_TIME"));
				map.put("status", rs.getString("STATUS"));
				appointmentList.add(map);
			}
			request.setAttribute("appointmentList", appointmentList);
			request.getRequestDispatcher("ViewAppointments.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			}
		}
	}