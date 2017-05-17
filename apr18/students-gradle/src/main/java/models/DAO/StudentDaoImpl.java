package models.DAO;

import models.DAO.mappers.StudentMapper;
import models.POJO.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import utils.DataSourceFactory;

import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by eku on 18.04.17.
 */
@Repository
public class StudentDaoImpl implements StudentDao {
    private static final Logger LOGGER = Logger.getLogger(UserDaoImpl.class);

    @Autowired
    private StudentMapper studentMapper;

//    @Autowired
//    public void setStudentMapper(StudentMapper studentMapper) {
//        this.studentMapper = studentMapper;
//    }

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
//        try {
//            Reader reader = Resources.getResourceAsReader("mybatis.xml");
//            SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);
//            StudentMapper mapper = factory.openSession().getMapper(StudentMapper.class);
//            return mapper.getAllStudents();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;

        return studentMapper.getAllStudents();
    }

    public Student getStudentById(int studentId) {
        try {
            Reader reader = Resources.getResourceAsReader("mybatis.xml");
            SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);
            StudentMapper mapper = factory.openSession().getMapper(StudentMapper.class);
            return mapper.getStudentById(Long.valueOf(studentId));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
