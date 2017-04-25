package model.impl;

import model.dao.GroupDao;
import model.entity.Group;
import model.entity.Group;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GroupDaoImpl implements GroupDao {

    private static final Logger LOG = Logger.getLogger(GroupDaoImpl.class);
    private DataSource dataSource;

    public GroupDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Group> findAll() {
        List<Group> list = new ArrayList<>();
        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM study_group");
            while (resultSet.next()) {
                Group group = new Group();
                group.setId(resultSet.getLong(1));
                group.setName(resultSet.getString(2));
                list.add(group);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Group findById(long id) {
        Group group = null;
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM study_group WHERE id = ?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                group = new Group();
                group.setId(resultSet.getLong(1));
                group.setName(resultSet.getString(2));
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return group;
    }

    public int insert(Group group) {
        int lastId = 0;

        if (group == null)
            return lastId;
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO study_group (name) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, group.getName());
            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) lastId = rs.getInt(1);
            rs.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lastId;
    }

    public int delete(long id) {
        int result = 0;
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM study_group WHERE id = ?");
            preparedStatement.setLong(1, id);
            result = preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int update(Group group) {
        int result = 0;

        if (group == null)
            return result;

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE study_group SET name = ?, WHERE id = ?");
            preparedStatement.setString(1, group.getName());

            result = preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
