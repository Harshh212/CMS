package com.example.cms;
import java.sql.Connection;
import java.sql.DriverManager;

public class DataBaseConnection {
    public Connection databaselink;

    public Connection getConnection() {
        String databaseName = "cms";
        String databaseUser = "root";
        String databasepassword = "W@2915djkq#";
        String url = "jdbc:mysql://localhost:3306/" + databaseName;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaselink = DriverManager.getConnection(url, databaseUser, databasepassword);

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        return databaselink;

    }
}
