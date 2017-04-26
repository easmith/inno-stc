package services;

import models.DAO.GroupDao;
import models.DAO.GroupDaoImpl;
import models.POJO.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utils.profiling.ProfilingAnnotation;

import java.util.List;

/**
 * Created by eku on 19.04.17.
 */
@Service
public class GroupServiceImpl implements GroupService {
    public GroupDao groupDao;// = new GroupDaoImpl();

    @Autowired
    public void setGroupDao(GroupDao groupDao) {
        this.groupDao = groupDao;
    }

    @Override
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
