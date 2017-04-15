package ru.easmith;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by eku on 15.04.17.
 */
public class DatabaseManager {

    public static Connection initConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection connection = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/library", "root", "pass");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }
}
