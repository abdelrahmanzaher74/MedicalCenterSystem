package com.medical.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection getConnection() {
        try {

            String url = "jdbc:sqlserver://127.0.0.1:1433;databaseName=MedicalCenter;encrypt=true;trustServerCertificate=true";

            String user = "sa";
            String password = "123456";

            Connection con = DriverManager.getConnection(url, user, password);

            System.out.println("✅ CONNECTED SUCCESS");

            return con;

        } catch (Exception e) {
            System.out.println("❌ DB ERROR: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}