package model.impl;

import model.dao.StudentDao;
import model.entity.Student;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements StudentDao {

    private static final Logger LOG = Logger.getLogger(StudentDaoImpl.class);
    private DataSource dataSource;

    public StudentDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Student> findAll() {
        List<Student> list = new ArrayList<>();
        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM student");
            while (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getLong(1));
                student.setName(resultSet.getString(2));
                student.setAge(resultSet.getInt(3));
                student.setGroupId(resultSet.getLong(4));
                student.setGroup(new GroupDaoImpl(dataSource).findById(student.getGroupId()));
                list.add(student);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (
                SQLException e)

        {
            e.printStackTrace();
        }
        return list;
    }

    public Student findById(long id) {
        Student student = null;
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM student WHERE id = ?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                student = new Student();
                student.setId(resultSet.getLong(1));
                student.setName(resultSet.getString(2));
                student.setAge(resultSet.getInt(3));
                student.setGroupId(resultSet.getLong(4));
                student.setGroup(new GroupDaoImpl(dataSource).findById(student.getGroupId()));
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    public int insert(Student student) {
        int lastId = 0;

        if (student == null)
            return lastId;
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO student (name, age, group_id) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, student.getName());
            preparedStatement.setInt(2, student.getAge());
            preparedStatement.setLong(3, student.getGroupId());
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
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM student WHERE id = ?");
            preparedStatement.setLong(1, id);
            result = preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int update(Student student) {
        int result = 0;

        if (student == null)
            return result;

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE student SET name = ?, age = ?, group_id = ? WHERE id = ?");
            preparedStatement.setString(1, student.getName());
            preparedStatement.setInt(2, student.getAge());
            preparedStatement.setLong(3, student.getGroupId());
            preparedStatement.setLong(4, student.getId());

            result = preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

/*

    public int delete(Student student) {
        if (student == null)
            return 0;

        return delete(student.getId());
    }
*/


/*    public int save(Student student) {
        if (student == null)
            return 0;

        if (student.getId() != 0)
            return update(student);
        else
            return insert(student);
    }*/
}
