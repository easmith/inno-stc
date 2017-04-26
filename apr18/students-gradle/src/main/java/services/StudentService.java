package services;

import models.POJO.Student;

import java.util.List;

/**
 * Created by eku on 19.04.17.
 */
public interface StudentService {
    public List<Student> getAllStudents();

    void deleteStudent(Integer studentId);

    Student getStudentById(int studentId);

    void addStudent(Student student);

    void updateStudent(Student student);
}
