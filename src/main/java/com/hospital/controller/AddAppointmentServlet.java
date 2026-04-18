package com.hospital.controller;

import java.io.IOException;
import java.sql.*;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.hospital.model.Patient;
import com.hospital.model.Doctor;
import com.hospital.util.DBConnection;
//test change
@WebServlet("/AddAppointmentServlet")
public class AddAppointmentServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Patient> patientList = new ArrayList<>();
		List<Doctor> doctorList = new ArrayList<>();

		try {
			Connection conn = DBConnection.getConnection();

			// 🔹 Fetch Patients
			String psql = "SELECT * FROM PATIENT";
			PreparedStatement pps = conn.prepareStatement(psql);
			ResultSet prs = pps.executeQuery();

			while (prs.next()) {
				Patient p = new Patient();
				p.setPatientId(prs.getInt("PATIENT_ID"));
				p.setName(prs.getString("NAME"));
				patientList.add(p);
			}

			// 🔹 Fetch Doctors
			String dsql = "SELECT * FROM DOCTOR";
			PreparedStatement dps = conn.prepareStatement(dsql);
			ResultSet drs = dps.executeQuery();

			while (drs.next()) {
				Doctor d = new Doctor();
				d.setDoctorId(drs.getInt("DOCTOR_ID"));
				d.setName(drs.getString("NAME"));
				d.setSpecialization(drs.getString("SPECIALIZATION"));
				doctorList.add(d);
			}

			// send to JSP
			request.setAttribute("patientList", patientList);
			request.setAttribute("doctorList", doctorList);

			request.getRequestDispatcher("addAppointment.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}