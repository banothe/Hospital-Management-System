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

import com.hospital.model.Doctor;
import com.hospital.util.DBConnection;

/**
 * Servlet implementation class EditDoctorServlet
 */
@WebServlet("/EditDoctorServlet")
public class EditDoctorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditDoctorServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String id=request.getParameter("id");
		try {
			Connection conn=DBConnection.getConnection();
			String sql ="Select *  from doctor where doctor_id=?";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, Integer.parseInt(id));
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				Doctor d=new Doctor();
				d.setDoctorId(rs.getInt("Doctor_ID"));
				d.setName(rs.getString("NAME"));
				d.setSpecialization(rs.getString("Specialization"));
				d.setEmail(rs.getString("EMAIL"));
				d.setPhone(rs.getString("PHONE"));
				request.setAttribute("doctor",d);
				request.getRequestDispatcher("editDoctor.jsp").forward(request, response);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
