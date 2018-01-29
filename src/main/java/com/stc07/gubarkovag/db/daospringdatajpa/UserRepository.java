package com.stc07.gubarkovag.db.daospringdatajpa;

import com.stc07.gubarkovag.db.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByLoginAndPassword(String login, String password);
}
