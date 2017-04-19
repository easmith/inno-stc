package models.DAO;

import models.POJO.Group;
import models.POJO.Student;
import org.apache.log4j.Logger;
import services.DataSourceFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by eku on 18.04.17.
 */
public class GroupDaoImpl implements GroupDao {

    private static Connection connection;
    private static final Logger LOGGER = Logger.getLogger(GroupDaoImpl.class);

    public GroupDaoImpl() {
        try {
            connection = DataSourceFactory.getDataSource().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addGroup(Group group) {

    }

    @Override
    public void deleteGroup(int groupId) {

    }

    @Override
    public void updateGroup(Group group) {

    }

    public List<Group> getAllGroups() {
        List<Group> groups = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from groups");
            while (resultSet.next()) {
                Group group = new Group();
                group.setId(resultSet.getInt("id"));
                group.setName(resultSet.getString("name"));
                groups.add(group);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return groups;
    }

    public Group getGroupById(int userId) {
        Group group = new Group();
        group.setId(1);
//        group.setName("name");
        return group;
    }
}
