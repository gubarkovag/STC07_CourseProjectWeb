package com.stc07.gubarkovag.servicesjpa;

import com.stc07.gubarkovag.db.daojpa.UserDAOJPA;
import com.stc07.gubarkovag.db.entities.User;
import com.stc07.gubarkovag.springhelperclasses.Loggable;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Loggable
public class UserServiceImplJPA implements UserServiceJPA {
    private Logger logger;

    private UserDAOJPA userDAOJPA;

    @Autowired
    public UserServiceImplJPA(UserDAOJPA userDAOJPA) {
        this.userDAOJPA = userDAOJPA;
    }

    @Override
    public List<User> getAll() {
        return userDAOJPA.getAll();
    }

    @Override
    public User getById(Long id) {
        return userDAOJPA.getById(id);
    }

    @Override
    public int deleteById(Long id) {
        return userDAOJPA.deleteById(id);
    }

    @Override
    public User save(User user) {
        return userDAOJPA.save(user);
    }

    @Override
    public User getUserByLoginAndPassword(String login, String password) {
        return userDAOJPA.getUserByLoginAndPassword(login, password);
    }
}
