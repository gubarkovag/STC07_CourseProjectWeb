package com.stc07.gubarkovag.db.dao;

import com.stc07.gubarkovag.db.pooling.ConnectionManagerPostgreSQL;
import com.stc07.gubarkovag.db.pooling.IConnectionManager;
import com.stc07.gubarkovag.db.pooling.JdbcConnectionsPool;
import com.stc07.gubarkovag.pojo.Application;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ApplicationDAOImpl implements ApplicationDAO {
    private static final Logger logger = Logger.getLogger(ApplicationDAOImpl.class);

    public static class ApplicationDAOException extends Exception{}

    private static JdbcConnectionsPool jdbcConnectionsPool = JdbcConnectionsPool.getInstance();

    /*private static IConnectionManager manager;

    static {
        manager = ConnectionManagerPostgreSQL.getInstance();
    }*/

    public List<Application> getAll() throws ApplicationDAOException {
        List<Application> applicationList = new ArrayList<>();
        logger.info("get all applications query");
        try {
            Connection currentCon = jdbcConnectionsPool.borrowObject();
            Statement statement = currentCon/*manager.getConnection()*/.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from public.\"application\"");
            while(resultSet.next()) {
                Application application = new Application(
                        resultSet.getInt("id"),
                        resultSet.getInt("user_id"),
                        resultSet.getInt("book_id"),
                        Application.Status.valueOf(resultSet.getString("status")));

                applicationList.add(application);
            }

            jdbcConnectionsPool.returnObject(currentCon);
        } catch (SQLException e) {
            logger.error(new StringBuilder().append(e.getErrorCode()).append(" ")
                    .append(e.getMessage()).append(System.lineSeparator())
                    .append(e.getCause()).toString());
            //e.printStackTrace();
            throw new ApplicationDAOException();
        }

        return applicationList;
    }

    public Application getById(Integer id) throws ApplicationDAOException {
        Application application = null;
        logger.info("get application by id "+ id + " query");
        try {
            Connection currentCon = jdbcConnectionsPool.borrowObject();
            PreparedStatement statement = currentCon/*manager.getConnection()*/
                    .prepareStatement("select * from public.\"application\" where id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            application = new Application(
                    resultSet.getInt("id"),
                    resultSet.getInt("book_id"),
                    resultSet.getInt("user_id"),
                    Application.Status.valueOf(resultSet.getString("status")));

            jdbcConnectionsPool.returnObject(currentCon);
        } catch (SQLException e) {
            logger.error(new StringBuilder().append(e.getErrorCode()).append(" ")
                    .append(e.getMessage()).append(System.lineSeparator())
                    .append(e.getCause()).toString());
            //e.printStackTrace();
            throw new ApplicationDAOException();
        }

        return application;
    }

    public boolean deleteById(Integer id) throws ApplicationDAOException {
        PreparedStatement statement = null;
        logger.info("delete application by id " + id + " query");
        try {
            Connection currentCon = jdbcConnectionsPool.borrowObject();
            statement = currentCon/*manager.getConnection()*/
                    .prepareStatement("delete from public.\"application\" where id = ?");
            statement.setInt(1, id);
            jdbcConnectionsPool.returnObject(currentCon);
            return statement.execute();
        } catch (SQLException e) {
            logger.error(new StringBuilder().append(e.getErrorCode()).append(" ")
                    .append(e.getMessage()).append(System.lineSeparator())
                    .append(e.getCause()).toString());
            //e.printStackTrace();
            throw new ApplicationDAOException();
        }
    }

    public boolean deleteAll() throws ApplicationDAOException {
        logger.info("delete all applications query");
        Connection currentCon = jdbcConnectionsPool.borrowObject();
        try {
            Statement statement = currentCon/*manager.getConnection()*/.createStatement();
            return statement.execute("delete from public.\"application\"");
        } catch (SQLException e) {
            logger.error(new StringBuilder().append(e.getErrorCode()).append(" ")
                    .append(e.getMessage()).append(System.lineSeparator())
                    .append(e.getCause()).toString());
            //e.printStackTrace();
            throw new ApplicationDAOException();
        } finally {
            jdbcConnectionsPool.returnObject(currentCon);
        }
    }

    public int insertOne(Application application) throws ApplicationDAOException {
        logger.info("insert application:" + System.lineSeparator() + application + System.lineSeparator() + " query");
        try {
            Connection currentCon = jdbcConnectionsPool.borrowObject();
            PreparedStatement statement = currentCon/*manager.getConnection()*/
                    .prepareStatement("INSERT INTO public.\"application\"(user_id, book_id, status)" +
                            " VALUES(?, ?, ?)");
            statement.setInt(1,
                    application.getUser_id());
            statement.setInt(2,
                    application.getBook_id());
            statement.setString(3,
                    application.getStatus().toString());

            jdbcConnectionsPool.returnObject(currentCon);
            return statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(new StringBuilder().append(e.getErrorCode()).append(" ")
                    .append(e.getMessage()).append(System.lineSeparator())
                    .append(e.getCause()).toString());
            //e.printStackTrace();
            throw new ApplicationDAOException();
        }
    }

    public int[] insertAll(List<Application> applications) throws ApplicationDAOException {
        logger.info("insert set of applications query");
        try {
            Connection currentCon = jdbcConnectionsPool.borrowObject();
            PreparedStatement statement = currentCon/*manager.getConnection()*/
                    .prepareStatement("INSERT INTO public.\"application\"(user_id, book_id, status)" +
                            " VALUES(?, ?, ?)");
            for (Application application : applications) {
                statement.setInt(1,
                        application.getUser_id());
                statement.setInt(2,
                        application.getBook_id());
                statement.setString(3,
                        application.getStatus().toString());

                statement.addBatch();
            }

            jdbcConnectionsPool.returnObject(currentCon);
            return statement.executeBatch();
        } catch (SQLException e) {
            logger.error(new StringBuilder().append(e.getErrorCode()).append(" ")
                    .append(e.getMessage()).append(System.lineSeparator())
                    .append(e.getCause()).toString());
            //e.printStackTrace();
            throw new ApplicationDAOException();
        }
    }

    public int update(String status, Integer targetId) throws ApplicationDAOException {
        logger.info("set status " + status + " to application with id " + targetId + " query");
        try {
            Connection currentCon = jdbcConnectionsPool.borrowObject();
            PreparedStatement statement = currentCon/*manager.getConnection()*/
                    .prepareStatement("update public.\"application\" set status = ?" +
                            " where book_id = ?");
            statement.setString(1, status);
            statement.setInt(2, targetId);

            jdbcConnectionsPool.returnObject(currentCon);
            return statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(new StringBuilder().append(e.getErrorCode()).append(" ")
                    .append(e.getMessage()).append(System.lineSeparator())
                    .append(e.getCause()).toString());
            //e.printStackTrace();
            throw new ApplicationDAOException();
        }
    }

    public int[] updateAll(Map<String, Integer> updateData) throws ApplicationDAOException {
        logger.info("update all given applications query");
        try {
            Connection currentCon = jdbcConnectionsPool.borrowObject();
            PreparedStatement statement = currentCon/*manager.getConnection()*/
                    .prepareStatement("update public.\"application\" set status = ?" +
                            " where id = ?");

            for (Map.Entry<String, Integer> entry : updateData.entrySet()) {
                String status = entry.getKey();
                Integer targetId = entry.getValue();
                statement.setString(1, status);
                statement.setInt(2, targetId);

                statement.addBatch();
            }

            jdbcConnectionsPool.returnObject(currentCon);
            return statement.executeBatch();
        } catch (SQLException e) {
            logger.error(new StringBuilder().append(e.getErrorCode()).append(" ")
                    .append(e.getMessage()).append(System.lineSeparator())
                    .append(e.getCause()).toString());
            //e.printStackTrace();
            throw new ApplicationDAOException();
        }
    }

    @Override
    public boolean putRejectedToWaitingStatus(Integer userId) throws ApplicationDAOException {
        logger.info("change application status from rejected to waiting query where userId = " + userId);
        Connection currentCon = jdbcConnectionsPool.borrowObject();
        try {
            Statement statement = currentCon/*manager.getConnection()*/.createStatement();
            return statement.execute("update public.\"application\" set status = \'WAITING\'" +
                            " where status = \'REJECTED\' and user_id = " + userId);
        } catch (SQLException e) {
            logger.error(new StringBuilder().append(e.getErrorCode()).append(" ")
                    .append(e.getMessage()).append(System.lineSeparator())
                    .append(e.getCause()).toString());
            //e.printStackTrace();
            throw new ApplicationDAOException();
        } finally {
            jdbcConnectionsPool.returnObject(currentCon);
        }
    }
}
