package com.stc07.gubarkovag.servicesspringdatajpa;

import com.stc07.gubarkovag.db.entities.User;

import java.util.List;

public interface UserService {
    List<User> getAll();
    User getById(Long id);
    void deleteById(Long id);
    User save(User user);
    User findByLoginAndPassword(String login, String password);
}
