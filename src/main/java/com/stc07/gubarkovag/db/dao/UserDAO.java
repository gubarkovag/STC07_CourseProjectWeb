package com.stc07.gubarkovag.db.dao;

import com.stc07.gubarkovag.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserDAO {
    List<User> getAll() throws UserDAOImpl.UserDAOException;
    User getById(Integer id) throws UserDAOImpl.UserDAOException;
    boolean deleteById(Integer id) throws UserDAOImpl.UserDAOException;
    boolean deleteAll() throws UserDAOImpl.UserDAOException;
    int insertOne(User user) throws UserDAOImpl.UserDAOException;
    int[] insertAll(List<User> users) throws UserDAOImpl.UserDAOException;
    int update(User changeData, Integer targetId) throws UserDAOImpl.UserDAOException;
    int[] updateAll(Map<User, Integer> updateData) throws UserDAOImpl.UserDAOException;
    User getUserByLoginAndPassword(String login, String password);
}
