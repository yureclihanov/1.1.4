package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {
    private long PEOPLE_COUNT;

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String sql = "CREATE TABLE if NOT EXISTS Users" +
                " (id INT PRIMARY KEY NOT NULL, name VARCHAR(45), lastName VARCHAR(45), age INT);";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String sql = "DROP TABLE Users;";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO Users VALUES (?, ?, ?, ?);";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setLong(1, ++PEOPLE_COUNT);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, lastName);
            preparedStatement.setByte(4, age);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM Users WHERE id = ?;";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        String sql = "SELECT * FROM Users;";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                long id = rs.getLong(1);
                String name = rs.getString(2);
                String lastName = rs.getString(3);
                byte age = rs.getByte(4);

                list.add(new User(id, name, lastName, age));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void cleanUsersTable() {
        String sql = "DELETE FROM Users;";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
