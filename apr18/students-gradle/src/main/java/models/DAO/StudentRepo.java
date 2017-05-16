package models.DAO;

import models.POJO.Student;
import models.hbn.StudentsEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * Created by eku on 16.05.17.
 */

@Repository
public interface StudentRepo extends CrudRepository<StudentsEntity, Integer>{
}
