package com.stc07.gubarkovag.db.dao;

import com.stc07.gubarkovag.db.pooling.ConnectionManagerPostgreSQL;
import com.stc07.gubarkovag.db.pooling.IConnectionManager;
import com.stc07.gubarkovag.db.pooling.JdbcConnectionsPool;
import com.stc07.gubarkovag.pojo.Application;
import com.stc07.gubarkovag.springhelperclasses.Loggable;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
@Loggable
public class ApplicationDAOImpl implements ApplicationDAO {
    private Logger logger;

    public static class ApplicationDAOException extends Exception{}

    private static JdbcConnectionsPool jdbcConnectionsPool = JdbcConnectionsPool.getInstance();

    /*private static IConnectionManager manager;

    static {
        manager = ConnectionManagerPostgreSQL.getInstance();
    }*/

    public List<Application> getAll() throws ApplicationDAOException {
        Connection currentCon = jdbcConnectionsPool.borrowObject();
        List<Application> applicationList = new ArrayList<>();
        logger.info("get all applications query");
        try {
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
        } catch (SQLException e) {
            logger.error(new StringBuilder().append(e.getErrorCode()).append(" ")
                    .append(e.getMessage()).append(System.lineSeparator())
                    .append(e.getStackTrace()).toString());
            //e.printStackTrace();
            throw new ApplicationDAOException();
        } finally {
            jdbcConnectionsPool.returnObject(currentCon);
        }

        return applicationList;
    }

    public Application getById(Integer id) throws ApplicationDAOException {
        Connection currentCon = jdbcConnectionsPool.borrowObject();
        Application application = null;
        logger.info("get application by id "+ id + " query");
        try {
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
        } catch (SQLException e) {
            logger.error(new StringBuilder().append(e.getErrorCode()).append(" ")
                    .append(e.getMessage()).append(System.lineSeparator())
                    .append(e.getStackTrace()).toString());
            //e.printStackTrace();
            throw new ApplicationDAOException();
        } finally {
            jdbcConnectionsPool.returnObject(currentCon);
        }

        return application;
    }

    public boolean deleteById(Integer id) throws ApplicationDAOException {
        Connection currentCon = jdbcConnectionsPool.borrowObject();
        PreparedStatement statement = null;
        logger.info("delete application by id " + id + " query");
        try {
            statement = currentCon/*manager.getConnection()*/
                    .prepareStatement("delete from public.\"application\" where id = ?");
            statement.setInt(1, id);
            return statement.execute();
        } catch (SQLException e) {
            logger.error(new StringBuilder().append(e.getErrorCode()).append(" ")
                    .append(e.getMessage()).append(System.lineSeparator())
                    .append(e.getStackTrace()).toString());
            //e.printStackTrace();
            throw new ApplicationDAOException();
        } finally {
            jdbcConnectionsPool.returnObject(currentCon);
        }
    }

    public boolean deleteAll() throws ApplicationDAOException {
        Connection currentCon = jdbcConnectionsPool.borrowObject();
        logger.info("delete all applications query");
        try {
            Statement statement = currentCon/*manager.getConnection()*/.createStatement();
            return statement.execute("delete from public.\"application\"");
        } catch (SQLException e) {
            logger.error(new StringBuilder().append(e.getErrorCode()).append(" ")
                    .append(e.getMessage()).append(System.lineSeparator())
                    .append(e.getStackTrace()).toString());
            //e.printStackTrace();
            throw new ApplicationDAOException();
        } finally {
            jdbcConnectionsPool.returnObject(currentCon);
        }
    }

    public int insertOne(Application application) throws ApplicationDAOException {
        Connection currentCon = jdbcConnectionsPool.borrowObject();
        logger.info("insert application:" + System.lineSeparator() + application + System.lineSeparator() + " query");
        try {
            PreparedStatement statement = currentCon/*manager.getConnection()*/
                    .prepareStatement("INSERT INTO public.\"application\"(user_id, book_id, status)" +
                            " VALUES(?, ?, ?)");
            statement.setInt(1,
                    application.getUser_id());
            statement.setInt(2,
                    application.getBook_id());
            statement.setString(3,
                    application.getStatus().toString());
            return statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(new StringBuilder().append(e.getErrorCode()).append(" ")
                    .append(e.getMessage()).append(System.lineSeparator())
                    .append(e.getStackTrace()).toString());
            //e.printStackTrace();
            throw new ApplicationDAOException();
        } finally {
            jdbcConnectionsPool.returnObject(currentCon);
        }
    }

    public int[] insertAll(List<Application> applications) throws ApplicationDAOException {
        Connection currentCon = jdbcConnectionsPool.borrowObject();
        logger.info("insert set of applications query");
        try {
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
            return statement.executeBatch();
        } catch (SQLException e) {
            logger.error(new StringBuilder().append(e.getErrorCode()).append(" ")
                    .append(e.getMessage()).append(System.lineSeparator())
                    .append(e.getStackTrace()).toString());
            //e.printStackTrace();
            throw new ApplicationDAOException();
        } finally {
            jdbcConnectionsPool.returnObject(currentCon);
        }
    }

    public int update(String status, Integer targetId) throws ApplicationDAOException {
        Connection currentCon = jdbcConnectionsPool.borrowObject();
        logger.info("set status " + status + " to application with id " + targetId + " query");
        try {
            PreparedStatement statement = currentCon/*manager.getConnection()*/
                    .prepareStatement("update public.\"application\" set status = ?" +
                            " where book_id = ?");
            statement.setString(1, status);
            statement.setInt(2, targetId);

            return statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(new StringBuilder().append(e.getErrorCode()).append(" ")
                    .append(e.getMessage()).append(System.lineSeparator())
                    .append(e.getStackTrace()).toString());
            //e.printStackTrace();
            throw new ApplicationDAOException();
        } finally {
            jdbcConnectionsPool.returnObject(currentCon);
        }
    }

    public int[] updateAll(Map<String, Integer> updateData) throws ApplicationDAOException {
        Connection currentCon = jdbcConnectionsPool.borrowObject();
        logger.info("update all given applications query");
        try {
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

            return statement.executeBatch();
        } catch (SQLException e) {
            logger.error(new StringBuilder().append(e.getErrorCode()).append(" ")
                    .append(e.getMessage()).append(System.lineSeparator())
                    .append(e.getStackTrace()).toString());
            //e.printStackTrace();
            throw new ApplicationDAOException();
        } finally {
            jdbcConnectionsPool.returnObject(currentCon);
        }
    }

    @Override
    public boolean putRejectedToWaitingStatus(Integer userId) throws ApplicationDAOException {
        Connection currentCon = jdbcConnectionsPool.borrowObject();
        logger.info("change application status from rejected to waiting query where userId = " + userId);
        try {
            Statement statement = currentCon/*manager.getConnection()*/.createStatement();
            return statement.execute("update public.\"application\" set status = \'WAITING\'" +
                            " where status = \'REJECTED\' and user_id = " + userId);
        } catch (SQLException e) {
            logger.error(new StringBuilder().append(e.getErrorCode()).append(" ")
                    .append(e.getMessage()).append(System.lineSeparator())
                    .append(e.getStackTrace()).toString());
            //e.printStackTrace();
            throw new ApplicationDAOException();
        } finally {
            jdbcConnectionsPool.returnObject(currentCon);
        }
    }
}
