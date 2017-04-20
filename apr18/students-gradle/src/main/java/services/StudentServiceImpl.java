package services;

import models.DAO.StudentDao;
import models.DAO.StudentDaoImpl;
import models.POJO.Student;

import java.util.List;

/**
 * Created by eku on 19.04.17.
 */
public class StudentServiceImpl implements StudentService {
    public static StudentDao studentDao = new StudentDaoImpl();

    public List<Student> getAllStudents() {
        return studentDao.getAllStudents();
    }

    @Override
    public void deleteStudent(Integer studentId) {
        studentDao.deleteStudent(studentId);
    }

    @Override
    public Student getStudentById(int studentId) {
        return studentDao.getStudentById(studentId);
    }

    @Override
    public void addStudent(Student student) {
        studentDao.addStudent(student);
    }

    @Override
    public void updateStudent(Student student) {
        studentDao.updateStudent(student);
    }
}
