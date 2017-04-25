package model.dao;

import model.entity.Student;

import java.util.List;

public interface StudentDao {
    List<Student> findAll();

    Student findById(long id);

    int insert(Student student);

    int delete(long id);

    int update(Student student);

    //int save(Student student);
}
