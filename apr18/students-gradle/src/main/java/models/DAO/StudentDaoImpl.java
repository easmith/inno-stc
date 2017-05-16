package models.DAO;

import ma.glasnost.orika.BoundMapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import models.POJO.Student;
import models.hbn.StudentsEntity;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.stereotype.Repository;
import utils.DataSourceFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by eku on 18.04.17.
 */
@Repository
public class StudentDaoImpl implements StudentDao {
    private static final Logger LOGGER = Logger.getLogger(UserDaoImpl.class);

    @Override
    public void addStudent(Student student) {
        try (Connection connection = DataSourceFactory.getDataSource().getConnection()) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO students (name, age, group_id) VALUES (?, ?, 1)");
            statement.setString(1, student.getName());
            statement.setLong(2, student.getAge());
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertHiber(Student student) {
//        Session session = factory.openSession();
//        session.beginTransaction();
//        models.hbn.StudentsEntity studentsEntity = new StudentsEntity();
//        studentsEntity.setAge(student.getAge());
//        studentsEntity.setName(student.getName());
//
//        session.save(studentsEntity);
//        session.getTransaction().commit();
//        session.close();
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
            statement.setLong(2, student.getId());
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
                Student student = new Student(resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getLong("age"));
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
                student = new Student(resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getLong("age"));
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }
}
