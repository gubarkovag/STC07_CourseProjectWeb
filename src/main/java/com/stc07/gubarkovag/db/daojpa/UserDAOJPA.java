package com.stc07.gubarkovag.db.daojpa;

import com.stc07.gubarkovag.db.entities.User;

import java.util.List;

public interface UserDAOJPA {
    List<User> getAll();
    User getById(Long id);
    int deleteById(Long id);
    User save(User user);
    User getUserByLoginAndPassword(String login, String password);
}
