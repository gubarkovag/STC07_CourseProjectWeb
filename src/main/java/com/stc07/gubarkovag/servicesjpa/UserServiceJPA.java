package com.stc07.gubarkovag.servicesjpa;

import com.stc07.gubarkovag.db.entities.User;

import java.util.List;

public interface UserServiceJPA {
    List<User> getAll();
    User getById(Long id);
    int deleteById(Long id);
    User save(User user);
    User getUserByLoginAndPassword(String login, String password);
}
