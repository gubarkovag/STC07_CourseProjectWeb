package com.stc07.gubarkovag.services;

import com.stc07.gubarkovag.db.dao.UserDAO;
import com.stc07.gubarkovag.db.dao.UserDAOImpl;
import com.stc07.gubarkovag.pojo.User;
import com.stc07.gubarkovag.springhelperclasses.Loggable;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Loggable
public class UserServiceImpl implements UserService {
    private Logger logger;

    public static class UserServiceException extends Exception {}

    private UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public List<User> getAll() throws UserServiceException {
        try {
            return userDAO.getAll();
        } catch (UserDAOImpl.UserDAOException e) {
            logger.error(new StringBuilder()
                    .append(e.getMessage()).append(System.lineSeparator())
                    .append(e.getStackTrace()).toString());
            //e.printStackTrace();
            throw new UserServiceException();
        }
    }

    @Override
    public User getById(Integer id) throws UserServiceException {
        try {
            return userDAO.getById(id);
        } catch (UserDAOImpl.UserDAOException e) {
            logger.error(new StringBuilder()
                    .append(e.getMessage()).append(System.lineSeparator())
                    .append(e.getStackTrace()).toString());
            //e.printStackTrace();
            throw new UserServiceException();
        }
    }

    @Override
    public boolean deleteById(Integer id) throws UserServiceException {
        try {
            return userDAO.deleteById(id);
        } catch (UserDAOImpl.UserDAOException e) {
            logger.error(new StringBuilder()
                    .append(e.getMessage()).append(System.lineSeparator())
                    .append(e.getStackTrace()).toString());
            //e.printStackTrace();
            throw new UserServiceException();
        }
    }

    @Override
    public boolean deleteAll() throws UserServiceException {
        try {
            return userDAO.deleteAll();
        } catch (UserDAOImpl.UserDAOException e) {
            logger.error(new StringBuilder()
                    .append(e.getMessage()).append(System.lineSeparator())
                    .append(e.getStackTrace()).toString());
            //e.printStackTrace();
            throw new UserServiceException();
        }
    }

    @Override
    public int insertOne(User user) throws UserServiceException {
        try {
            return userDAO.insertOne(user);
        } catch (UserDAOImpl.UserDAOException e) {
            logger.error(new StringBuilder()
                    .append(e.getMessage()).append(System.lineSeparator())
                    .append(e.getStackTrace()).toString());
            //e.printStackTrace();
            throw new UserServiceException();
        }
    }

    @Override
    public int[] insertAll(List<User> users) throws UserServiceException {
        try {
            return userDAO.insertAll(users);
        } catch (UserDAOImpl.UserDAOException e) {
            logger.error(new StringBuilder()
                    .append(e.getMessage()).append(System.lineSeparator())
                    .append(e.getStackTrace()).toString());
            //e.printStackTrace();
            throw new UserServiceException();
        }
    }

    @Override
    public int update(User changeData, Integer targetId) throws UserServiceException {
        try {
            return userDAO.update(changeData, targetId);
        } catch (UserDAOImpl.UserDAOException e) {
            logger.error(new StringBuilder()
                    .append(e.getMessage()).append(System.lineSeparator())
                    .append(e.getStackTrace()).toString());
            //e.printStackTrace();
            throw new UserServiceException();
        }
    }

    @Override
    public int[] updateAll(Map<User, Integer> updateData) throws UserServiceException {
        try {
            return userDAO.updateAll(updateData);
        } catch (UserDAOImpl.UserDAOException e) {
            logger.error(new StringBuilder()
                    .append(e.getMessage()).append(System.lineSeparator())
                    .append(e.getStackTrace()).toString());
            //e.printStackTrace();
            throw new UserServiceException();
        }
    }

    @Override
    public User getUserByLoginAndPassword(String login, String password) {
        return userDAO.getUserByLoginAndPassword(login, password);
    }
}
