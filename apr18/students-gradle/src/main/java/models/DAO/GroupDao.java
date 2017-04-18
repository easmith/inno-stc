package models.DAO;

import models.POJO.Group;
import models.POJO.Student;

import java.util.List;

/**
 * Created by eku on 19.04.17.
 */
public interface GroupDao {
    public void addGroup( Group group );
    public void deleteGroup( int groupId );
    public void updateGroup( Group group );
    public List<Student> getAllGroups();
    public Group getGroupById(int groupId );
}
