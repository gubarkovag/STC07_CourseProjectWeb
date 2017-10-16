package com.stc07.gubarkovag.services;

import com.stc07.gubarkovag.db.dao.UserDAOImpl;
import com.stc07.gubarkovag.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    List<User> getAll() throws UserServiceImpl.UserServiceException;
    User getById(Integer id) throws UserServiceImpl.UserServiceException;
    boolean deleteById(Integer id) throws UserServiceImpl.UserServiceException;
    boolean deleteAll() throws UserServiceImpl.UserServiceException;
    int insertOne(User user) throws UserServiceImpl.UserServiceException;
    int[] insertAll(List<User> users) throws UserServiceImpl.UserServiceException;
    int update(User changeData, Integer targetId) throws UserServiceImpl.UserServiceException;
    int[] updateAll(Map<User, Integer> updateData) throws UserServiceImpl.UserServiceException;
    User getUserByLoginAndPassword(String login, String password);
}
