package models.DAO;

import models.POJO.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eku on 18.04.17.
 */
public class StudentDaoImpl implements StudentDao {
    @Override
    public void addStudent(Student student) {

    }

    public void deleteStudent(int userId) {

    }

    @Override
    public void updateStudent(Student student) {

    }

    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<Student>();

        for (int i = 0; i < 3; i++) {
            Student student = new Student();
            student.setId(i);
            student.setName("Name" + i);
            students.add(student);
        }
        return students;
    }

    public Student getStudentById(int userId) {
        return null;
    }
}
