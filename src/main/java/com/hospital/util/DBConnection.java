/*
 * package com.hospital.util; import java.sql.*;
 */
/*
 * public class DBConnection { //STEP1:JDBC driver name and database URL static
 * final String DB_URL="oracle.jdbc.OracleDriver";
 * 
 * //STEP2:Database Credentials static final String USER="System"; static final
 * String PASS="Oracle@654";
 * 
 * public static Connection getConnection() { Connection con=null; try {
 * //STEP3:Register JDBC driver Class.forName(DB_URL); //STEP4:Open a Connection
 * System.out.println("Connecting to the database");
 * con=DriverManager.getConnection(DB_URL, USER, PASS); } catch(Exception e) {
 * e.printStackTrace(); } return con; } }
 */
package com.hospital.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    // Database URL (NOT driver name)
    static final String DB_URL = "jdbc:oracle:thin:@//localhost:1521/ORCLPDB";

    // Database Credentials
    static final String USER = "SYSTEM";
    static final String PASS = "Oracle@654";

    public static Connection getConnection() {
        Connection con = null;
        try {
            // Load driver class
            Class.forName("oracle.jdbc.OracleDriver");

            System.out.println("Connecting to the database...");

            // Use DB_URL here
            con = DriverManager.getConnection(DB_URL, USER, PASS);

            System.out.println("Connected successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }
}
