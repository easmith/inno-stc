package services;

import models.POJO.Group;
import models.POJO.Student;

import java.util.List;

/**
 * Created by eku on 19.04.17.
 */
public interface GroupService {
    public List<Group> getAllGroups();

    void deleteGroup(Integer studentId);

    Group getGroupById(int studentId);

    void addGroup(Group student);

    void updateGroup(Group student);
}
