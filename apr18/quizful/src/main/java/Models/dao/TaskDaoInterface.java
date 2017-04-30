package Models.dao;

import Models.pojo.Task;
import exceptions.QuizInternalException;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by eku on 01.05.17.
 */
public interface TaskDaoInterface {
    Task createFromResultSet(ResultSet set) throws SQLException, QuizInternalException;
}
