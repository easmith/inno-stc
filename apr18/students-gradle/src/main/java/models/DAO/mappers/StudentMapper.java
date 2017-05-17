package models.DAO.mappers;

import models.POJO.Student;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by eku on 17.05.17.
 */
@Component
public interface StudentMapper {
    List getAllStudents();
    Student getStudentById(Long id);

}
