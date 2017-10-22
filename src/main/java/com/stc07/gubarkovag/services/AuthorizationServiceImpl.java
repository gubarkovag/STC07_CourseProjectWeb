package com.stc07.gubarkovag.services;

import com.stc07.gubarkovag.db.dao.UserDAO;
import com.stc07.gubarkovag.db.dao.UserDAOImpl;
import com.stc07.gubarkovag.pojo.User;

import java.util.HashMap;
import java.util.Map;

public class AuthorizationServiceImpl implements AuthorizationService {
    public static class AuthServiceException extends Exception{
        private String message;
    }

    private static UserDAO userDAO = new UserDAOImpl();

    @Override
    public Map<Boolean, User> auth(String login, String password) {
        if (login == null || password == null)
            return null;

        User user;
        if ((user = userDAO.getUserByLoginAndPassword(login, /*encode(*/password/*)*/)) != null) {
            return new HashMap<Boolean, User>() {
                {
                    put(true, user);
                }
            };
        }

        return null;
    }
}
