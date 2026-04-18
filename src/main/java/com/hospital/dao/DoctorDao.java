package com.hospital.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.hospital.util.DBConnection;

public class DoctorDao {
	public boolean CheckCredentials(String username,String password) { boolean
		  status = false; 
		 try { Connection con = DBConnection.getConnection();
		 String sql = "SELECT * FROM admin WHERE username=? AND password=?";
		  PreparedStatement ps = con.prepareStatement(sql);
		  ps.setString(1, username);
		  ps.setString(2, password);
		 
		  ResultSet rs = ps.executeQuery();
		  if (rs.next()) { 
			  status = true; // ⭐ THIS WAS MISSING
		  System.out.println("Login Success"); 
		  } 
		  else {
		  System.out.println("Login Failed"); 
		  } }
		  catch(Exception e) 
		 {
		  e.printStackTrace(); 
		  }
		 return status; 
		 }
}