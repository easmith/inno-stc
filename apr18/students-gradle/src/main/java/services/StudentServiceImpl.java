package services;

import ma.glasnost.orika.BoundMapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import models.DAO.StudentDao;
import models.POJO.Student;
import models.hbn.StudentsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eku on 19.04.17.
 */
@Service
public class StudentServiceImpl implements StudentService {

//    private static MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

//    private StudentRepo studentRepo;
//    private static BoundMapperFacade<StudentsEntity, Student> boundMapper = mapperFactory.getMapperFacade(StudentsEntity.class, Student.class);
    private StudentDao studentDao;// = new StudentDaoImpl();

//    {
//        mapperFactory.classMap(
//                Student.class, StudentsEntity.class).field("group.id", "groupId");
//    }

    @Autowired
    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public List<Student> getAllStudents() {
//        return studentDao.getAllStudents();
//        List<StudentsEntity> studentEntities = (List<StudentsEntity>) studentRepo.findAll();
//        List<Student> students = new ArrayList<>();
//        studentEntities.forEach(student -> {
//            students.add(boundMapper.map(student));
//        });
        List students = studentDao.getAllStudents();
        return new ArrayList();
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

//    @Autowired
//    public void setStudentRepo(StudentRepo studentRepo) {
//        this.studentRepo = studentRepo;
//    }
}
