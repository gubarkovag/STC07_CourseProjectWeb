package com.stc07.gubarkovag.services;

import com.stc07.gubarkovag.db.dao.UserDAO;
import com.stc07.gubarkovag.db.dao.UserDAOImpl;
import com.stc07.gubarkovag.pojo.User;

import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {
    public static class UserServiceException extends Exception {}

    private static UserDAO userDAO = new UserDAOImpl();

    @Override
    public List<User> getAll() throws UserServiceException {
        try {
            return userDAO.getAll();
        } catch (UserDAOImpl.UserDAOException e) {
            e.printStackTrace();
            throw new UserServiceException();
        }
    }

    @Override
    public User getById(Integer id) throws UserServiceException {
        try {
            return userDAO.getById(id);
        } catch (UserDAOImpl.UserDAOException e) {
            e.printStackTrace();
            throw new UserServiceException();
        }
    }

    @Override
    public boolean deleteById(Integer id) throws UserServiceException {
        try {
            return userDAO.deleteById(id);
        } catch (UserDAOImpl.UserDAOException e) {
            e.printStackTrace();
            throw new UserServiceException();
        }
    }

    @Override
    public boolean deleteAll() throws UserServiceException {
        try {
            return userDAO.deleteAll();
        } catch (UserDAOImpl.UserDAOException e) {
            e.printStackTrace();
            throw new UserServiceException();
        }
    }

    @Override
    public int insertOne(User user) throws UserServiceException {
        try {
            return userDAO.insertOne(user);
        } catch (UserDAOImpl.UserDAOException e) {
            e.printStackTrace();
            throw new UserServiceException();
        }
    }

    @Override
    public int[] insertAll(List<User> users) throws UserServiceException {
        try {
            return userDAO.insertAll(users);
        } catch (UserDAOImpl.UserDAOException e) {
            e.printStackTrace();
            throw new UserServiceException();
        }
    }

    @Override
    public int update(User changeData, Integer targetId) throws UserServiceException {
        try {
            return userDAO.update(changeData, targetId);
        } catch (UserDAOImpl.UserDAOException e) {
            e.printStackTrace();
            throw new UserServiceException();
        }
    }

    @Override
    public int[] updateAll(Map<User, Integer> updateData) throws UserServiceException {
        try {
            return userDAO.updateAll(updateData);
        } catch (UserDAOImpl.UserDAOException e) {
            e.printStackTrace();
            throw new UserServiceException();
        }
    }

    @Override
    public User getUserByLoginAndPassword(String login, String password) {
        return userDAO.getUserByLoginAndPassword(login, password);
    }
}
