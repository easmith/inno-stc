package Utils;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by eku on 15.04.17.
 */
public class DatabaseManager {

    private static final Logger LOGGER = Logger.getLogger(DatabaseManager.class);

    private static Connection conn;

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            LOGGER.fatal("Отсутствует драйвер базы данных");
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
