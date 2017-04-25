package models.DAO;

import models.POJO.Group;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import utils.DataSourceFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by eku on 18.04.17.
 */
@Repository
public class GroupDaoImpl implements GroupDao {

    private static final Logger LOGGER = Logger.getLogger(GroupDaoImpl.class);

    @Override
    public void addGroup(Group group) {
        try (Connection connection = DataSourceFactory.getDataSource().getConnection()) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO groups (name) VALUES (?)");
            statement.setString(1, group.getName());
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteGroup(int groupId) {
        try (Connection connection = DataSourceFactory.getDataSource().getConnection()) {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM groups WHERE id = ?");
            statement.setInt(1, groupId);
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void updateGroup(Group group) {
        try (Connection connection = DataSourceFactory.getDataSource().getConnection()) {
            PreparedStatement statement = connection.prepareStatement("UPDATE groups SET name = ? WHERE id = ?");
            statement.setString(1, group.getName());
            statement.setInt(2, group.getId());
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    public List<Group> getAllGroups() {
        List<Group> groups = new ArrayList<>();
        try (Connection connection = DataSourceFactory.getDataSource().getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from groups");
            while (resultSet.next()) {
                Group group = new Group(resultSet.getInt("id"), resultSet.getString("name"));
                groups.add(group);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return groups;
    }

    public Group getGroupById(int groupId) {
        Group group = null;
        try (Connection connection = DataSourceFactory.getDataSource().getConnection()) {
            PreparedStatement statement = connection.prepareStatement("select * from groups where id = ?");
            statement.setInt(1, groupId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                group = new Group(resultSet.getInt("id"), resultSet.getString("name"));
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return group;
    }
}
