package models.DAO;

import models.POJO.Group;
import models.POJO.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by eku on 18.04.17.
 */
public class GroupDaoImpl implements GroupDao {


    @Override
    public void addGroup(Group group) {

    }

    @Override
    public void deleteGroup(int groupId) {

    }

    @Override
    public void updateGroup(Group group) {

    }

    public List<Student> getAllGroups() {
        List<Student> groups = new ArrayList<>();
//        try {
//            Statement statement = conn.createStatement();
//            ResultSet resultSet = statement.executeQuery("select * from groups");
//            while (resultSet.next()) {
//                Student student = new Student();
//                student.setId(resultSet.getInt("id"));
//                student.setName(resultSet.getString("name"));
//                groups.add(student);
//            }
//            resultSet.close();
//            statement.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        return groups;
    }

    public Group getGroupById(int userId) {
        Group group = new Group();
        group.setId(1);
//        group.setName("name");
        return group;
    }
}
