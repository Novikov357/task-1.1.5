package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final List<User> USERLIST = new ArrayList<>();
    private final Connection CONNECTION = getConnection();


    public UserDaoJDBCImpl() {

    }

    private static Connection getConnection() {
        Connection connection = null;
        try {
            connection = Util.getConnection();
        } catch (Exception e) {
            System.out.println("Ошибка при установке соединения");
            e.printStackTrace();
        }
        return connection;
    }

    public void createUsersTable() {
        try (Statement statement = CONNECTION.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS user(" +
                    "    id BIGINT PRIMARY KEY AUTO_INCREMENT," +
                    "    name VARCHAR(25)," +
                    "    lastName VARCHAR(25)," +
                    "    age TINYINT UNSIGNED" +
                    ");");
        } catch (SQLException e) {
            System.out.println("Ошибка при создании таблицы");
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Statement statement = CONNECTION.createStatement()) {
            statement.execute("DROP TABLE IF EXISTS user");
        } catch (SQLException e) {
            System.out.println("Ошибка при удалении таблицы");
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement preparedStatement = CONNECTION
                .prepareStatement("INSERT INTO user (name, lastName, age) VALUES (?, ?, ?)")) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.execute();
            System.out.printf("User с именем – %s добавлен в базу данных \n", name);
        } catch (SQLException e) {
            System.out.println("Ошибка при добавлении пользователя");
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (PreparedStatement preparedStatement = CONNECTION.prepareStatement("DELETE FROM user WHERE id = ?")) {
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println("Ошибка при удалении пользователя");
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        try (Statement statement = CONNECTION.createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT * FROM user");
            while (rs.next()) {
                User user = new User(rs.getString(2), rs.getString(3), rs.getByte(4));
                user.setId(rs.getLong(1));
                USERLIST.add(user);
            }
        } catch (SQLException e) {
            System.out.println("Ошибка при получении списка пользователя");
            e.printStackTrace();
        }
        return USERLIST;
    }

    public void cleanUsersTable() {
        try (Statement statement = CONNECTION.createStatement()) {
            statement.execute("TRUNCATE TABLE user");
        } catch (SQLException e) {
            System.out.println("Ошибка при очистке таблицы");
            e.printStackTrace();
        }
    }
}
