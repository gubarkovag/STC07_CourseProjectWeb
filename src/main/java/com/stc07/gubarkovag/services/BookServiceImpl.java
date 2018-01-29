package com.stc07.gubarkovag.services;

import com.stc07.gubarkovag.db.dao.BookDAO;
import com.stc07.gubarkovag.db.dao.BookDAOImpl;
import com.stc07.gubarkovag.pojo.Book;
import com.stc07.gubarkovag.springhelperclasses.Loggable;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Loggable
public class BookServiceImpl implements BookService {
    private Logger logger;

    public static class BookServiceException extends Exception {}

    private BookDAO bookDAO;

    @Autowired
    public BookServiceImpl(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @Override
    public List<Book> getAppsByStatus(String status) throws BookServiceException {
        try {
            return bookDAO.getAppsByStatus(status);
        } catch (BookDAOImpl.BookDAOException e) {
            logger.error(new StringBuilder()
                    .append(e.getMessage()).append(System.lineSeparator())
                    .append(e.getStackTrace()).toString());
            //e.printStackTrace();
            throw new BookServiceException();
        }
    }

    @Override
    public List<Book> getAppsByStatusAndUser(String status, Integer userId) throws BookServiceException {
        try {
            return bookDAO.getAppsByStatusAndUser(status, userId);
        } catch (BookDAOImpl.BookDAOException e) {
            logger.error(new StringBuilder()
                    .append(e.getMessage()).append(System.lineSeparator())
                    .append(e.getStackTrace()).toString());
            //e.printStackTrace();
            throw new BookServiceException();
        }
    }

    @Override
    public List<Book> getAll() throws BookServiceException {
        try {
            return bookDAO.getAll();
        } catch (BookDAOImpl.BookDAOException e) {
            logger.error(new StringBuilder()
                    .append(e.getMessage()).append(System.lineSeparator())
                    .append(e.getStackTrace()).toString());
            //e.printStackTrace();
            throw new BookServiceException();
        }
    }

    @Override
    public Book getById(Integer id) throws BookServiceException {
        try {
            return bookDAO.getById(id);
        } catch (BookDAOImpl.BookDAOException e) {
            logger.error(new StringBuilder()
                    .append(e.getMessage()).append(System.lineSeparator())
                    .append(e.getStackTrace()).toString());
            //e.printStackTrace();
            throw new BookServiceException();
        }
    }

    @Override
    public boolean deleteById(Integer id) throws BookServiceException {
        try {
            return bookDAO.deleteById(id);
        } catch (BookDAOImpl.BookDAOException e) {
            logger.error(new StringBuilder()
                    .append(e.getMessage()).append(System.lineSeparator())
                    .append(e.getStackTrace()).toString());
            //e.printStackTrace();
            throw new BookServiceException();
        }
    }

    @Override
    public boolean deleteAll() throws BookServiceException {
        try {
            return bookDAO.deleteAll();
        } catch (BookDAOImpl.BookDAOException e) {
            logger.error(new StringBuilder()
                    .append(e.getMessage()).append(System.lineSeparator())
                    .append(e.getStackTrace()).toString());
            //e.printStackTrace();
            throw new BookServiceException();
        }
    }

    @Override
    public int insertOne(Book book) throws BookServiceException {
        try {
            return bookDAO.insertOne(book);
        } catch (BookDAOImpl.BookDAOException e) {
            logger.error(new StringBuilder()
                    .append(e.getMessage()).append(System.lineSeparator())
                    .append(e.getStackTrace()).toString());
            //e.printStackTrace();
            throw new BookServiceException();
        }
    }

    @Override
    public int[] insertAll(List<Book> books) throws BookServiceException {
        try {
            return bookDAO.insertAll(books);
        } catch (BookDAOImpl.BookDAOException e) {
            logger.error(new StringBuilder()
                    .append(e.getMessage()).append(System.lineSeparator())
                    .append(e.getStackTrace()).toString());
            //e.printStackTrace();
            throw new BookServiceException();
        }
    }

    @Override
    public int update(Book changeData, Integer targetId) throws BookServiceException {
        try {
            return bookDAO.update(changeData, targetId);
        } catch (BookDAOImpl.BookDAOException e) {
            logger.error(new StringBuilder()
                    .append(e.getMessage()).append(System.lineSeparator())
                    .append(e.getStackTrace()).toString());
            //e.printStackTrace();
            throw new BookServiceException();
        }
    }

    @Override
    public int[] updateAll(Map<Book, Integer> updateData) throws BookServiceException {
        try {
            return bookDAO.updateAll(updateData);
        } catch (BookDAOImpl.BookDAOException e) {
            logger.error(new StringBuilder()
                    .append(e.getMessage()).append(System.lineSeparator())
                    .append(e.getStackTrace()).toString());
            //e.printStackTrace();
            throw new BookServiceException();
        }
    }

    @Override
    public int findMaxId() throws BookServiceException {
        try {
            return bookDAO.findMaxId();
        } catch (BookDAOImpl.BookDAOException e) {
            logger.error(new StringBuilder()
                    .append(e.getMessage()).append(System.lineSeparator())
                    .append(e.getStackTrace()).toString());
            //e.printStackTrace();
            throw new BookServiceException();
        }
    }
}
