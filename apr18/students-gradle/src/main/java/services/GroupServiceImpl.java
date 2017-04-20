package services;

import models.DAO.GroupDao;
import models.DAO.GroupDaoImpl;
import models.DAO.StudentDao;
import models.DAO.StudentDaoImpl;
import models.POJO.Group;
import models.POJO.Student;

import java.util.List;

/**
 * Created by eku on 19.04.17.
 */
public class GroupServiceImpl implements GroupService {
    public static GroupDao groupDao = new GroupDaoImpl();

    public List<Group> getAllGroups() {
        return groupDao.getAllGroups();
    }

    @Override
    public void deleteGroup(Integer studentId) {
        groupDao.deleteGroup(studentId);
    }

    @Override
    public Group getGroupById(int groupId) {
        return groupDao.getGroupById(groupId);
    }

    @Override
    public void addGroup(Group group) {
        groupDao.addGroup(group);
    }

    @Override
    public void updateGroup(Group group) {
        groupDao.updateGroup(group);
    }
}
