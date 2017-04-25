package model.dao;

import model.entity.Group;
import model.entity.Student;

import java.util.List;

public interface GroupDao {

    List<Group> findAll();

    Group findById(long id);

    int insert(Group group);

    int delete(long id);

    int update(Group group);
    //int delete(Group group);
    //int save(Group group);
}
