package models.DAO;

import models.POJO.Student;

import java.util.List;

/**
 * Created by eku on 18.04.17.
 */
public interface StudentDao {
    public void addStudent( Student student );
    public void deleteStudent( int studentId );
    public void updateStudent( Student student );
    public List<Student> getAllStudents();
    public Student getStudentById( int studentId );
}
