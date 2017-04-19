package services;

import models.DAO.StudentDao;
import models.DAO.StudentDaoImpl;
import models.POJO.Student;

import java.util.List;

/**
 * Created by eku on 19.04.17.
 */
public class StudentService implements StudentsServiceInterface {
    public static StudentDao studentDao = new StudentDaoImpl();

    public List<Student> getAllStudents() {
        return studentDao.getAllStudents();
    }
}
