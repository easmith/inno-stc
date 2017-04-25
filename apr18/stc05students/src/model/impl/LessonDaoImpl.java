package model.impl;

import model.dao.LessonDao;
import model.entity.Lesson;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LessonDaoImpl implements LessonDao {

    private static final Logger LOG = Logger.getLogger(LessonDaoImpl.class);
    private DataSource dataSource;

    public LessonDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Lesson> findAll() {
        List<Lesson> list = new ArrayList<>();
        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM lesson");
            while (resultSet.next()) {
                Lesson lesson = new Lesson();
                lesson.setId(resultSet.getLong(1));
                lesson.setGroupId(resultSet.getLong(2));
                lesson.setGroup(new GroupDaoImpl(dataSource).findById(lesson.getGroupId()));
                lesson.setLessonDate(resultSet.getTimestamp(3));
                lesson.setRoom(resultSet.getInt(4));
                lesson.setDescription(resultSet.getString(5));
                list.add(lesson);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Lesson findById(long id) {
        Lesson lesson = null;
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM lesson WHERE id = ?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                lesson = new Lesson();
                lesson.setId(resultSet.getLong(1));
                lesson.setGroupId(resultSet.getLong(2));
                lesson.setGroup(new GroupDaoImpl(dataSource).findById(lesson.getGroupId()));
                lesson.setLessonDate(resultSet.getTimestamp(3));
                lesson.setRoom(resultSet.getInt(4));
                lesson.setDescription(resultSet.getString(5));
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lesson;
    }

    public int insert(Lesson lesson) {
        int lastId = 0;

        if (lesson == null)
            return lastId;
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO lesson (study_group_id, lesson_date, room, description) VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, lesson.getGroupId());
            preparedStatement.setTimestamp(2, new Timestamp(lesson.getLessonDate().getTime()));
            preparedStatement.setInt(3, lesson.getRoom());
            preparedStatement.setString(4, lesson.getDescription());
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
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM lesson WHERE id = ?");
            preparedStatement.setLong(1, id);
            result = preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int update(Lesson lesson) {
        int result = 0;

        if (lesson == null)
            return result;

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE lesson SET study_group_id = ?, lesson_date = ?, room = ?, description = ? WHERE id = ?");
            preparedStatement.setLong(1, lesson.getGroupId());
            preparedStatement.setTimestamp(2, new Timestamp(lesson.getLessonDate().getTime()));
            preparedStatement.setInt(3, lesson.getRoom());
            preparedStatement.setString(4, lesson.getDescription());
            preparedStatement.setLong(5, lesson.getId());

            result = preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
