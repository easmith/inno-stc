package com.company.library.Utils;

import com.company.library.models.Book;

import javax.xml.crypto.Data;
import java.sql.*;

/**
 * Created by eku on 13.04.17.
 */
public class DatabaseManager {

    public static Connection initConnection() {
//        try {
//            Class.forName("");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }

        Connection connection = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/library", "root", "pass");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

    public static void select() {
        Connection connection = DatabaseManager.initConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from books");
            while (resultSet.next()) {
                System.out.println(resultSet.getString("author"));
                System.out.println(resultSet.getString("title"));
                System.out.println(resultSet.getInt("year"));
                System.out.println(resultSet.getString(5));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insert() {
        Connection connection = DatabaseManager.initConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into books(id, author, title, year, isbn)" +
                    " VALUES ( ?, ?, ?, ?, ?)");
            preparedStatement.setString(1, "3");
            preparedStatement.setString(2, "author 3");
            preparedStatement.setString(3, "Title 3");
            preparedStatement.setString(4, "2013");
            preparedStatement.setString(5, "isbn12312");
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Book book) {

    }

    public void delete(Book book) {

    }
}
