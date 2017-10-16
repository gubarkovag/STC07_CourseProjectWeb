package com.stc07.gubarkovag.db.dao;

import com.stc07.gubarkovag.db.ConnectionManagerPostgreSQL;
import com.stc07.gubarkovag.db.IConnectionManager;
import com.stc07.gubarkovag.pojo.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserDAOImpl implements UserDAO {
    public static class UserDAOException extends Exception{}

    private static IConnectionManager manager;

    static {
        manager = ConnectionManagerPostgreSQL.getInstance();
    }

    @Override
    public User getUserByLoginAndPassword(String login, String password) {
        User user = null;
        PreparedStatement statement = null;
        try {
            statement = manager.getConnection()
                    .prepareStatement("select * from users where login = ? " +
                            "and password = ?");
            statement.setString(1, login);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new User(resultSet.getInt("id")
                                , resultSet.getString("login")
                                , resultSet.getString("password")
                                , User.Role.valueOf(resultSet.getString("role").toUpperCase()));
            }

            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<User> getAll() throws UserDAOException {
        List<User> userList = new ArrayList<>();
        try {
            Statement statement = manager.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("select * from public.users");
            while(resultSet.next()) {
                User user = new User(
                        resultSet.getInt("id"),
                        resultSet.getString("login"),
                        resultSet.getString("password"),
                        User.Role.valueOf(resultSet.getString("role")));

                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UserDAOException();
        }

        return userList;
    }

    public User getById(Integer id) throws UserDAOException {
        User user = null;
        try {
            PreparedStatement statement = manager.getConnection()
                    .prepareStatement("select * from public.users where id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            user = new User(
                    resultSet.getInt("id"),
                    resultSet.getString("login"),
                    resultSet.getString("password"),
                    User.Role.valueOf(resultSet.getString("role")));

        } catch (SQLException e) {
            e.printStackTrace();
            throw new UserDAOException();
        }

        return user;
    }

    public boolean deleteById(Integer id) throws UserDAOException {
        PreparedStatement statement = null;
        try {
            statement = manager.getConnection()
                    .prepareStatement("delete from public.users where id = ?");
            statement.setInt(1, id);
            return statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UserDAOException();
        }
    }

    public boolean deleteAll() throws UserDAOException {
        try {
            Statement statement = manager.getConnection().createStatement();
            return statement.execute("delete from public.users");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UserDAOException();
        }
    }

    public int insertOne(User user) throws UserDAOException {
        try {
            PreparedStatement statement = manager.getConnection()
                    .prepareStatement("INSERT INTO public.users(id, login, password" +
                            ", role) VALUES(?, ?, ?, ?)");
            statement.setInt(1,
                    user.getId());
            statement.setString(2,
                    user.getLogin());
            statement.setString(3,
                    user.getPassword());
            statement.setString(4,
                    user.getRole().toString());

            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UserDAOException();
        }
    }

    public int[] insertAll(List<User> users) throws UserDAOException {
        try {
            PreparedStatement statement = manager.getConnection()
                    .prepareStatement("INSERT INTO public.users(id, login, password" +
                            ", role) VALUES(?, ?, ?, ?)");
            for (User user : users) {
                statement.setInt(1,
                        user.getId());
                statement.setString(2,
                        user.getLogin());
                statement.setString(3,
                        user.getPassword());
                statement.setString(4,
                        user.getRole().toString());

                statement.addBatch();
            }

            return statement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UserDAOException();
        }
    }

    public int update(User changeData, Integer targetId) throws UserDAOException {
        try {
            PreparedStatement statement = manager.getConnection()
                    .prepareStatement("update public.users set id = ?, login = ?, password = ?" +
                            ", role = ? where id = ?");
            statement.setInt(1, changeData.getId());
            statement.setString(2, changeData.getLogin());
            statement.setString(3, changeData.getPassword());
            statement.setString(4, changeData.getRole().toString());
            statement.setInt(5, targetId);

            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UserDAOException();
        }
    }

    public int[] updateAll(Map<User, Integer> updateData) throws UserDAOException {
        try {
            PreparedStatement statement = manager.getConnection()
                    .prepareStatement("update public.users set id = ?, login = ?, password = ?" +
                            ", role = ? where id = ?");

            for (Map.Entry<User, Integer> entry : updateData.entrySet()) {
                User changeData = entry.getKey();
                Integer targetId = entry.getValue();
                statement.setInt(1, changeData.getId());
                statement.setString(2, changeData.getLogin());
                statement.setString(3, changeData.getPassword());
                statement.setString(4, changeData.getRole().toString());
                statement.setInt(5, targetId);

                statement.addBatch();
            }

            return statement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UserDAOException();
        }
    }
}
