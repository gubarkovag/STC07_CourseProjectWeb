package com.stc07.gubarkovag.db.dao;

import com.stc07.gubarkovag.db.pooling.JdbcConnectionsPool;
import com.stc07.gubarkovag.pojo.User;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserDAOImpl implements UserDAO {
    private static final Logger logger = Logger.getLogger(UserDAOImpl.class);

    public static class UserDAOException extends Exception{}

    private static JdbcConnectionsPool jdbcConnectionsPool = JdbcConnectionsPool.getInstance();

    /*private static IConnectionManager manager;

    static {
        manager = ConnectionManagerPostgreSQL.getInstance();
    }*/

    @Override
    public User getUserByLoginAndPassword(String login, String password) {
        User user = null;
        PreparedStatement statement = null;
        logger.info("get user instance with login = " + login);
        try {
            Connection currentCon = jdbcConnectionsPool.borrowObject();
            statement = currentCon/*manager.getConnection()*/
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

            jdbcConnectionsPool.returnObject(currentCon);
            return user;
        } catch (SQLException e) {
            logger.error(new StringBuilder().append(e.getErrorCode()).append(" ")
                    .append(e.getMessage()).append(System.lineSeparator())
                    .append(e.getCause()).toString()); //e.printStackTrace();
        }

        return null;
    }

    public List<User> getAll() throws UserDAOException {
        List<User> userList = new ArrayList<>();
        logger.info("get all users query");
        try {
            Connection currentCon = jdbcConnectionsPool.borrowObject();
            Statement statement = currentCon/*manager.getConnection()*/
                    .createStatement();
            ResultSet resultSet = statement.executeQuery("select * from public.users");
            while(resultSet.next()) {
                User user = new User(
                        resultSet.getInt("id"),
                        resultSet.getString("login"),
                        resultSet.getString("password"),
                        User.Role.valueOf(resultSet.getString("role").toUpperCase()));

                userList.add(user);
            }
            jdbcConnectionsPool.returnObject(currentCon);

        } catch (SQLException e) {
            logger.error(new StringBuilder().append(e.getErrorCode()).append(" ")
                    .append(e.getMessage()).append(System.lineSeparator())
                    .append(e.getCause()).toString()); //e.printStackTrace();
            throw new UserDAOException();
        }

        return userList;
    }

    public User getById(Integer id) throws UserDAOException {
        User user = null;
        logger.info("get user by id: " + id + " query");
        try {
            Connection currentCon = jdbcConnectionsPool.borrowObject();
            PreparedStatement statement = currentCon/*manager.getConnection()*/
                    .prepareStatement("select * from public.users where id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            user = new User(
                    resultSet.getInt("id"),
                    resultSet.getString("login"),
                    resultSet.getString("password"),
                    User.Role.valueOf(resultSet.getString("role").toUpperCase()));

            jdbcConnectionsPool.returnObject(currentCon);
        } catch (SQLException e) {
            logger.error(new StringBuilder().append(e.getErrorCode()).append(" ")
                    .append(e.getMessage()).append(System.lineSeparator())
                    .append(e.getCause()).toString()); //e.printStackTrace();
            throw new UserDAOException();
        }

        return user;
    }

    public boolean deleteById(Integer id) throws UserDAOException {
        PreparedStatement statement = null;
        logger.info("delete user with id: " + id + " query");
        try {
            Connection currentCon = jdbcConnectionsPool.borrowObject();
            statement = currentCon/*manager.getConnection()*/
                    .prepareStatement("delete from public.users where id = ?");
            statement.setInt(1, id);

            jdbcConnectionsPool.returnObject(currentCon);
            return statement.execute();
        } catch (SQLException e) {
            logger.error(new StringBuilder().append(e.getErrorCode()).append(" ")
                    .append(e.getMessage()).append(System.lineSeparator())
                    .append(e.getCause()).toString()); //e.printStackTrace();
            throw new UserDAOException();
        }
    }

    public boolean deleteAll() throws UserDAOException {
        logger.info("delete all users query");
        try {
            Connection currentCon = jdbcConnectionsPool.borrowObject();
            Statement statement = currentCon/*manager.getConnection()*/
                    .createStatement();
            jdbcConnectionsPool.returnObject(currentCon);
            return statement.execute("delete from public.users");
        } catch (SQLException e) {
            logger.error(new StringBuilder().append(e.getErrorCode()).append(" ")
                    .append(e.getMessage()).append(System.lineSeparator())
                    .append(e.getCause()).toString()); //e.printStackTrace();
            throw new UserDAOException();
        }
    }

    public int insertOne(User user) throws UserDAOException {
        logger.info("insert user:" + System.lineSeparator() + user + System.lineSeparator() + " query");
        try {
            Connection currentCon = jdbcConnectionsPool.borrowObject();
            PreparedStatement statement = currentCon/*manager.getConnection()*/
                    .prepareStatement("INSERT INTO public.users(login, password" +
                            ", role) VALUES(?, ?, ?)");
            statement.setString(1,
                    user.getLogin());
            statement.setString(2,
                    user.getPassword());
            statement.setString(3,
                    user.getRole().toString());

            jdbcConnectionsPool.returnObject(currentCon);
            return statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(new StringBuilder().append(e.getErrorCode()).append(" ")
                    .append(e.getMessage()).append(System.lineSeparator())
                    .append(e.getCause()).toString()); //e.printStackTrace();
            throw new UserDAOException();
        }
    }

    public int[] insertAll(List<User> users) throws UserDAOException {
        logger.info("insert set of users query");
        try {
            Connection currentCon = jdbcConnectionsPool.borrowObject();
            PreparedStatement statement = currentCon/*manager.getConnection()*/
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

            jdbcConnectionsPool.returnObject(currentCon);
            return statement.executeBatch();
        } catch (SQLException e) {
            logger.error(new StringBuilder().append(e.getErrorCode()).append(" ")
                    .append(e.getMessage()).append(System.lineSeparator())
                    .append(e.getCause()).toString()); //e.printStackTrace();
            throw new UserDAOException();
        }
    }

    public int update(User changeData, Integer targetId) throws UserDAOException {
        logger.info("update user with id " + targetId + " query");
        try {
            Connection currentCon = jdbcConnectionsPool.borrowObject();
            PreparedStatement statement = currentCon/*manager.getConnection()*/
                    .prepareStatement("update public.users set id = ?, login = ?, password = ?" +
                            ", role = ? where id = ?");
            statement.setInt(1, changeData.getId());
            statement.setString(2, changeData.getLogin());
            statement.setString(3, changeData.getPassword());
            statement.setString(4, changeData.getRole().toString());
            statement.setInt(5, targetId);

            jdbcConnectionsPool.returnObject(currentCon);
            return statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(new StringBuilder().append(e.getErrorCode()).append(" ")
                    .append(e.getMessage()).append(System.lineSeparator())
                    .append(e.getCause()).toString()); //e.printStackTrace();
            throw new UserDAOException();
        }
    }

    public int[] updateAll(Map<User, Integer> updateData) throws UserDAOException {
        logger.info("update set of users query");
        try {
            Connection currentCon = jdbcConnectionsPool.borrowObject();
            PreparedStatement statement = currentCon/*manager.getConnection()*/
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

            jdbcConnectionsPool.returnObject(currentCon);
            return statement.executeBatch();
        } catch (SQLException e) {
            logger.error(new StringBuilder().append(e.getErrorCode()).append(" ")
                    .append(e.getMessage()).append(System.lineSeparator())
                    .append(e.getCause()).toString()); //e.printStackTrace();
            throw new UserDAOException();
        }
    }
}
