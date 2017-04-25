package model.impl;

import model.dao.JournalDao;
import model.dao.LessonDao;
import model.entity.Journal;
import model.entity.Lesson;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JournalDaoImpl implements JournalDao {

    private static final Logger LOG = Logger.getLogger(JournalDaoImpl.class);
    private DataSource dataSource;

    public JournalDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Journal> findAll() {
        List<Journal> list = new ArrayList<>();
        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM journal");
            while (resultSet.next()) {
                Journal journal = new Journal();
                journal.setId(resultSet.getLong(1));
                journal.setLessonId(resultSet.getLong(2));
                journal.setStudentId(resultSet.getLong(3));
                journal.setLesson(new LessonDaoImpl(dataSource).findById(journal.getLessonId()));
                journal.setStudent(new StudentDaoImpl(dataSource).findById(journal.getStudentId()));
                list.add(journal);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Journal findById(long id) {
        Journal journal = null;
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM journal WHERE id = ?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                journal = new Journal();
                journal.setId(resultSet.getLong(1));
                journal.setLessonId(resultSet.getLong(2));
                journal.setStudentId(resultSet.getLong(3));
                journal.setLesson(new LessonDaoImpl(dataSource).findById(journal.getLessonId()));
                journal.setStudent(new StudentDaoImpl(dataSource).findById(journal.getStudentId()));
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return journal;
    }

    public int insert(Journal journal) {
        int lastId = 0;

        if (journal == null)
            return lastId;
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO journal (lesson_id, student_id) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, journal.getLessonId());
            preparedStatement.setLong(2, journal.getStudentId());

            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) lastId = rs.getInt(1);
            rs.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lastId;
    }

    public int delete(long id) {
        int result = 0;
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM journal WHERE id = ?");
            preparedStatement.setLong(1, id);
            result = preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int update(Journal journal) {
        int result = 0;

        if (journal == null)
            return result;

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE journal SET lesson_id = ?, student_id = ?  WHERE id = ?");
            preparedStatement.setLong(1, journal.getLessonId());
            preparedStatement.setLong(2, journal.getStudentId());
            preparedStatement.setLong(3, journal.getId());

            result = preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
