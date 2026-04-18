package com.hospital.controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.hospital.dao.UserDao;
import com.hospital.model.User;
import com.hospital.util.DBConnection;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	 protected void doPost(HttpServletRequest request, HttpServletResponse response)
		        throws ServletException, IOException {

		    String username = request.getParameter("username");
		    String password = request.getParameter("password");

		    try {
		        Connection conn = DBConnection.getConnection();
		        UserDao userDao = new UserDao(conn);
		        User user = userDao.login(username, password);

		        if (user != null) {

		            HttpSession session = request.getSession();
		            session.setAttribute("currentUser", user);

		            if (user.getRole().equals("ADMIN")) {
		                response.sendRedirect("adminDashboard.jsp");

		            } else if (user.getRole().equals("DOCTOR")) {
		                response.sendRedirect("DoctorDashboardServlet");

		            } else if (user.getRole().equals("PATIENT")) {
		                response.sendRedirect("PatientDashboardServlet");
		            }

		        } else {
		            response.sendRedirect("login.jsp?error=Invalid Credentials");
		        }
		        System.out.println("Username: " + username);
		        System.out.println("Password: " + password);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
}