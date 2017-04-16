package ru.easmith;

import org.apache.log4j.Logger;

import java.sql.*;
import java.util.Arrays;

/**
 * Created by eku on 15.04.17.
 */
public class DatabaseManager {

    protected static class DatabaseManagerHolder {
        public static final DatabaseManager HOLDER_INSTANCE = new DatabaseManager();
    }

    public static DatabaseManager getInstance() {
        return DatabaseManagerHolder.HOLDER_INSTANCE;
    }

    private final Connection connection = createConnection();;

//    private void DatabaseManager() {
//        this.connection = createConnection();
//    }

    private static final Logger LOGGER = Logger.getLogger(XmlManager.class);

    protected Connection createConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            LOGGER.fatal("Отсутствует драйвер базы данных");
        }

        Connection connection = null;
        LOGGER.trace("Соединяемся с базой данных...");
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/mydb?useSSL=false", "root", "pass");
            LOGGER.trace("Соедиение с базой данных установлено");
        } catch (SQLException e) {
            LOGGER.fatal("Не удалось открыть соединение с базой данных");
        }

        return connection;
    }

    protected PreparedStatement getPreparedStatement(String sql, Object... params) throws SQLException {
        PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
        int i = 1;
        for (Object param :
                params) {
            if (param instanceof Boolean) {
                preparedStatement.setBoolean(i++, (boolean)param);
            } else {
                preparedStatement.setString(i++, param.toString());
            }
        }
        return preparedStatement;
    }

    public void insertBatch(String sql, Object[][] objects) {
        final int batchSize = 1000;
        int count = 0;

        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            for (Object[] object: objects) {
                for (int i = 0; i < object.length; i++) {
                    preparedStatement.setString(i + 1, object[i].toString());
                }
                preparedStatement.addBatch();
                if(++count % batchSize == 0) {
                    preparedStatement.executeBatch();
                }
            }
            preparedStatement.executeBatch(); // insert remaining records
            preparedStatement.close();
        } catch (SQLException e) {
            LOGGER.error("sql: " + e.getMessage());
        }

    }

    /**
     * Отключает проверку внешних ключей
     */
    public void disableForeignKey() {
        Statement disableForeign = null;
        try {
            disableForeign = connection.createStatement();
            disableForeign.execute("SET FOREIGN_KEY_CHECKS=0");
        } catch (SQLException e) {
            LOGGER.error("Не удалось отключить проверку внешних ключей: " + e.getMessage());
        }
    }

    /**
     * Включает проверку внешних ключей
     */
    public void enableForeignKey() {
        Statement disableForeign = null;
        try {
            disableForeign = connection.createStatement();
            disableForeign.execute("SET FOREIGN_KEY_CHECKS=1");
        } catch (SQLException e) {
            LOGGER.error("Не удалось включить проверку внешних ключей: " + e.getMessage());
        }
    }

    public boolean execute(String sql, Object... params) {
        try {
            PreparedStatement preparedStatement = getPreparedStatement(sql, params);
            return preparedStatement.execute();
        } catch (SQLException e) {
            LOGGER.error("Ошибка при выполении запроса: " + e.getMessage());
        }
        return false;
    }

    /**
     * Формирует
     * @param sql SQL запрос select
     * @param params
     * @return
     */
    public ResultSet executeQuery(String sql, Object... params) {
        try {
            PreparedStatement preparedStatement = getPreparedStatement(sql, params);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            LOGGER.error("Ошибка при выполении запроса: " + e.getMessage());
        }
        return null;
    }

    /**
     * Чистит базу данных
     */
    public void clearDB() {
        // categories, tasks, answers
        LOGGER.trace("Чистим базу данных...");
        execute("DELETE FROM categories");
        execute("ALTER TABLE categories AUTO_INCREMENT = 1");
        execute("ALTER TABLE tasks AUTO_INCREMENT = 1");
        execute("ALTER TABLE answers AUTO_INCREMENT = 1");

        // users, results, result_task
        execute("DELETE FROM users");
        execute("ALTER TABLE users AUTO_INCREMENT = 1");
        execute("DELETE FROM results");
        execute("ALTER TABLE results AUTO_INCREMENT = 1");
        execute("DELETE FROM result_tasks");
        execute("ALTER TABLE result_tasks AUTO_INCREMENT = 1");
        LOGGER.trace("База данных очищена");
    }

    /**
     * Сохраняет обект в базу данных
     * @param object
     */
    public void storeObject(DBInterface object) {
        LOGGER.trace("Сохраняю в базу данных объект " + object.getClass().getCanonicalName());
        object.toDB();
    }

    /**
     * Заполняет обект данными их базы данных
     * @param object Объект
     * @return
     */
    public DBInterface restoreObject(DBInterface object) {
        LOGGER.trace("Заполняю данными из базы объект " + object.getClass().getCanonicalName());
        object.fromDB();
        return object;
    }

}
