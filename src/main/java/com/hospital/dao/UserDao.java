package com.hospital.dao;

import java.sql.*;
import com.hospital.model.User;

public class UserDao {
	private Connection conn;

    public UserDao(Connection conn) {
        this.conn = conn;
    }

    public User login(String username, String password) {

        User user = null;

        try {
            String sql = "SELECT * FROM USERS WHERE USERNAME=? AND PASSWORD=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setUserId(rs.getInt("USER_ID"));
                user.setUsername(rs.getString("USERNAME"));
                user.setRole(rs.getString("ROLE"));
                user.setReferenceId(rs.getInt("REFERENCE_ID"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("User Found: " + user);
        return user;
    }
}

