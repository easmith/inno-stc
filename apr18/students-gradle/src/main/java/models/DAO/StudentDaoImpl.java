package models.DAO;

import models.POJO.Student;
import org.apache.log4j.Logger;
import utils.DataSourceFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by eku on 18.04.17.
 */
public class StudentDaoImpl implements StudentDao {
    private static final Logger LOGGER = Logger.getLogger(UserDaoImpl.class);

    @Override
    public void addStudent(Student student) {
        try (Connection connection = DataSourceFactory.getDataSource().getConnection()) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO students (name, age, group_id) VALUES (?, ?, 1)");
            statement.setString(1, student.getName());
            statement.setInt(2, student.getAge());
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteStudent(int studentId) {
        try (Connection connection = DataSourceFactory.getDataSource().getConnection()) {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM students WHERE id = ?");
            statement.setInt(1, studentId);
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateStudent(Student student) {
        try (Connection connection = DataSourceFactory.getDataSource().getConnection()) {
            PreparedStatement statement = connection.prepareStatement("UPDATE students SET name = ? WHERE id = ?");
            statement.setString(1, student.getName());
            statement.setInt(2, student.getId());
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();

        try (Connection connection = DataSourceFactory.getDataSource().getConnection()) {
            PreparedStatement statement = connection.prepareStatement("select * from students");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Student student = new Student(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getInt("age"));
                students.add(student);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    public Student getStudentById(int studentId) {
        Student student = null;
        try (Connection connection = DataSourceFactory.getDataSource().getConnection()) {
            PreparedStatement statement = connection.prepareStatement("select * from students where id = ?");
            statement.setInt(1, studentId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                student = new Student(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getInt("age"));
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }
}
