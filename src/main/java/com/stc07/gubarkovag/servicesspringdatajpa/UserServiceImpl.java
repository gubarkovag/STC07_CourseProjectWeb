package com.stc07.gubarkovag.servicesspringdatajpa;

import com.google.common.collect.Lists;
import com.stc07.gubarkovag.db.daospringdatajpa.UserRepository;
import com.stc07.gubarkovag.db.entities.User;
import com.stc07.gubarkovag.springhelperclasses.Loggable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Loggable
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAll() {
        return Lists.newArrayList(userRepository.findAll());
    }

    @Override
    public User getById(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.delete(id);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findByLoginAndPassword(String login, String password) {
        return userRepository.findByLoginAndPassword(login, password);
    }
}
