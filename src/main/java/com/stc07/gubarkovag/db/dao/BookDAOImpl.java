package com.stc07.gubarkovag.db.dao;

import com.stc07.gubarkovag.db.pooling.JdbcConnectionsPool;
import com.stc07.gubarkovag.pojo.Book;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BookDAOImpl implements BookDAO {
    private static final Logger logger = Logger.getLogger(BookDAOImpl.class);

    public static class BookDAOException extends Exception{}

    private static JdbcConnectionsPool jdbcConnectionsPool = JdbcConnectionsPool.getInstance();

    /*private static IConnectionManager manager;

    static {
        manager = ConnectionManagerPostgreSQL.getInstance();
    }*/

    public List<Book> getAll() throws BookDAOException {
        Connection currentCon = jdbcConnectionsPool.borrowObject();
        List<Book> bookList = new ArrayList<>();
        logger.info("get all books query");
        try {
            Statement statement = currentCon/*manager.getConnection()*/.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from public.\"book\"");
            while(resultSet.next()) {
                Book book = new Book(
                        resultSet.getInt("id"),
                        resultSet.getInt("user_id"),
                        resultSet.getString("name"),
                        resultSet.getString("genre"));

                bookList.add(book);
            }
        } catch (SQLException e) {
            logger.error(new StringBuilder().append(e.getErrorCode()).append(" ")
                    .append(e.getMessage()).append(System.lineSeparator())
                    .append(e.getStackTrace()).toString()); //e.printStackTrace();
            throw new BookDAOException();
        } finally {
            jdbcConnectionsPool.returnObject(currentCon);
        }

        return bookList;
    }

    public Book getById(Integer id) throws BookDAOException {
        Connection currentCon = jdbcConnectionsPool.borrowObject();
        Book book = null;
        logger.info("get book by id: " + id + " query");
        try {
            PreparedStatement statement = currentCon/*manager.getConnection()*/
                    .prepareStatement("select * from public.\"book\" where id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            book = new Book(
                    resultSet.getInt("id"),
                    resultSet.getInt("user_id"),
                    resultSet.getString("genre"),
                    resultSet.getString("name"));

        } catch (SQLException e) {
            logger.error(new StringBuilder().append(e.getErrorCode()).append(" ")
                    .append(e.getMessage()).append(System.lineSeparator())
                    .append(e.getStackTrace()).toString()); //e.printStackTrace();
            throw new BookDAOException();
        } finally {
            jdbcConnectionsPool.returnObject(currentCon);
        }

        return book;
    }

    public boolean deleteById(Integer id) throws BookDAOException {
        Connection currentCon = jdbcConnectionsPool.borrowObject();
        PreparedStatement statement = null;
        logger.info("delete book with id: " + id + " query");
        try {
            statement = currentCon/*manager.getConnection()*/
                    .prepareStatement("delete from public.\"book\" where id = ?");
            statement.setInt(1, id);

            return statement.execute();
        } catch (SQLException e) {
            logger.error(new StringBuilder().append(e.getErrorCode()).append(" ")
                    .append(e.getMessage()).append(System.lineSeparator())
                    .append(e.getStackTrace()).toString()); //e.printStackTrace();
            throw new BookDAOException();
        } finally {
            jdbcConnectionsPool.returnObject(currentCon);
        }
    }

    public boolean deleteAll() throws BookDAOException {
        Connection currentCon = jdbcConnectionsPool.borrowObject();
        logger.info("delete all books query");
        try {
            Statement statement = currentCon/*manager.getConnection()*/
                    .createStatement();

            return statement.execute("delete from public.\"book\"");
        } catch (SQLException e) {
            logger.error(new StringBuilder().append(e.getErrorCode()).append(" ")
                    .append(e.getMessage()).append(System.lineSeparator())
                    .append(e.getStackTrace()).toString()); //e.printStackTrace();
            throw new BookDAOException();
        } finally {
            jdbcConnectionsPool.returnObject(currentCon);
        }
    }

    public int insertOne(Book book) throws BookDAOException {
        Connection currentCon = jdbcConnectionsPool.borrowObject();
        logger.info("insert book:" + System.lineSeparator() + book + System.lineSeparator() + " query");
        try {
            PreparedStatement statement = currentCon/*manager.getConnection()*/
                    .prepareStatement("INSERT INTO public.\"book\"(user_id, name, genre)" +
                            " VALUES(?, ?, ?)");
            statement.setInt(1,
                    book.getUserId());
            statement.setString(2,
                    book.getName());
            statement.setString(3,
                    book.getGenre());

            return statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(new StringBuilder().append(e.getErrorCode()).append(" ")
                    .append(e.getMessage()).append(System.lineSeparator())
                    .append(e.getStackTrace()).toString()); //e.printStackTrace();
            throw new BookDAOException();
        } finally {
            jdbcConnectionsPool.returnObject(currentCon);
        }
    }

    public int[] insertAll(List<Book> books) throws BookDAOException {
        Connection currentCon = jdbcConnectionsPool.borrowObject();
        logger.info("insert set of books query");
        try {
            PreparedStatement statement = currentCon/*manager.getConnection()*/
                    .prepareStatement("INSERT INTO public.\"book\"(user_id, name, genre)" +
                            " VALUES(?, ?, ?)");
            for (Book book : books) {
                statement.setInt(1,
                        book.getUserId());
                statement.setString(2,
                        book.getName());
                statement.setString(3,
                        book.getGenre());

                statement.addBatch();
            }

            return statement.executeBatch();
        } catch (SQLException e) {
            logger.error(new StringBuilder().append(e.getErrorCode()).append(" ")
                    .append(e.getMessage()).append(System.lineSeparator())
                    .append(e.getStackTrace()).toString()); //e.printStackTrace();
            throw new BookDAOException();
        } finally {
            jdbcConnectionsPool.returnObject(currentCon);
        }
    }

    public int update(Book changeData, Integer targetId) throws BookDAOException {
        Connection currentCon = jdbcConnectionsPool.borrowObject();
        logger.info("update book with id " + targetId + " query");
        try {
            PreparedStatement statement = currentCon/*manager.getConnection()*/
                    .prepareStatement("update public.\"book\" set user_id = ?, name = ?, genre = ?" +
                            " where id = ?");
            statement.setInt(1, changeData.getUserId());
            statement.setString(2, changeData.getName());
            statement.setString(3, changeData.getGenre());
            statement.setInt(4, targetId);

            return statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(new StringBuilder().append(e.getErrorCode()).append(" ")
                    .append(e.getMessage()).append(System.lineSeparator())
                    .append(e.getStackTrace()).toString()); //e.printStackTrace();
            throw new BookDAOException();
        } finally {
            jdbcConnectionsPool.returnObject(currentCon);
        }
    }

    public int[] updateAll(Map<Book, Integer> updateData) throws BookDAOException {
        Connection currentCon = jdbcConnectionsPool.borrowObject();
        logger.info("update set of books query");
        try {
            PreparedStatement statement = currentCon/*manager.getConnection()*/
                    .prepareStatement("update public.\"book\" set user_id = ?, name = ?, genre = ?" +
                            " where id = ?");

            for (Map.Entry<Book, Integer> entry : updateData.entrySet()) {
                Book changeData = entry.getKey();
                Integer targetId = entry.getValue();
                statement.setInt(1, changeData.getUserId());
                statement.setString(2, changeData.getName());
                statement.setString(3, changeData.getGenre());
                statement.setInt(4, targetId);

                statement.addBatch();
            }

            return statement.executeBatch();
        } catch (SQLException e) {
            logger.error(new StringBuilder().append(e.getErrorCode()).append(" ")
                    .append(e.getMessage()).append(System.lineSeparator())
                    .append(e.getStackTrace()).toString()); //e.printStackTrace();
            throw new BookDAOException();
        } finally {
            jdbcConnectionsPool.returnObject(currentCon);
        }
    }

    @Override
    public List<Book> getAppsByStatus(String status) throws BookDAOException {
        Connection currentCon = jdbcConnectionsPool.borrowObject();
        logger.info("get books/applications by status " + status);
        List<Book> bookList = new ArrayList<>();
        try {
            Statement statement = currentCon/*manager.getConnection()*/.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from public.\"book\"" +
                    " where id in (select book_id from public.\"application\" where status = \'" + status + "\')");
            while(resultSet.next()) {
                Book book = new Book(
                        resultSet.getInt("id"),
                        resultSet.getInt("user_id"),
                        resultSet.getString("name"),
                        resultSet.getString("genre"));

                bookList.add(book);
            }
        } catch (SQLException e) {
            logger.error(new StringBuilder().append(e.getErrorCode()).append(" ")
                    .append(e.getMessage()).append(System.lineSeparator())
                    .append(e.getStackTrace()).toString()); //e.printStackTrace();
            throw new BookDAOException();
        } finally {
            jdbcConnectionsPool.returnObject(currentCon);
        }

        return bookList;
    }

    @Override
    public List<Book> getAppsByStatusAndUser(String status, Integer userId) throws BookDAOException {
        Connection currentCon = jdbcConnectionsPool.borrowObject();
        List<Book> bookList = new ArrayList<>();
        logger.info("get books/applications by status " + status + " and userId " + userId);
        try {
            Statement statement = currentCon/*manager.getConnection()*/.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from public.\"book\"" +
                    " where user_id = " + userId +
                    " and id in (select book_id from public.\"application\" where status = \'" + status + "\')");
            while(resultSet.next()) {
                Book book = new Book(
                        resultSet.getInt("id"),
                        resultSet.getInt("user_id"),
                        resultSet.getString("name"),
                        resultSet.getString("genre"));

                bookList.add(book);
            }
        } catch (SQLException e) {
            logger.error(new StringBuilder().append(e.getErrorCode()).append(" ")
                    .append(e.getMessage()).append(System.lineSeparator())
                    .append(e.getStackTrace()).toString()); //e.printStackTrace();
            throw new BookDAOException();
        } finally {
            jdbcConnectionsPool.returnObject(currentCon);
        }

        return bookList;
    }

    @Override
    public int findMaxId() throws BookDAOException {
        Connection currentCon = jdbcConnectionsPool.borrowObject();
        List<Book> bookList = new ArrayList<>();
        logger.info("find max recent id in books table");
        try {
            Statement statement = currentCon/*manager.getConnection()*/.createStatement();
            ResultSet resultSet = statement.executeQuery("select max(id) from public.\"book\"");
            if (resultSet.next()) {
                return resultSet.getInt("max");
            }
        } catch (SQLException e) {
            logger.error(new StringBuilder().append(e.getErrorCode()).append(" ")
                    .append(e.getMessage()).append(System.lineSeparator())
                    .append(e.getStackTrace()).toString()); //e.printStackTrace();
            throw new BookDAOException();
        } finally {
            jdbcConnectionsPool.returnObject(currentCon);
        }

        return 0;
    }
}
