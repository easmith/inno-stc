package models.DAO;

import Utils.DatabaseManager;
import models.POJO.Student;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by eku on 18.04.17.
 */
public class StudentDaoImpl implements StudentDao {
//    private final Connection conn;

    public StudentDaoImpl() {
//        conn = DatabaseManager.getConnection();
    }

    @Override
    public void addStudent(Student student) {

    }

    public void deleteStudent(int userId) {

    }

    @Override
    public void updateStudent(Student student) {

    }

    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        Student student = new Student();
        student.setId(1);
        student.setName("name");
        students.add(student);

//        try {
//            Statement statement = conn.createStatement();
//            ResultSet resultSet = statement.executeQuery("select * from groups");
//            while (resultSet.next()) {
//                Student student = new Student();
//                student.setId(resultSet.getInt("id"));
//                student.setName(resultSet.getString("name"));
//                students.add(student);
//            }
//            resultSet.close();
//            statement.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        return students;
    }

    public Student getStudentById(int userId) {
        Student student = new Student();
        student.setId(1);
        student.setName("name");
        return student;
    }
}
