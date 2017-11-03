package com.stc07.gubarkovag.servicesjpa;

import com.stc07.gubarkovag.db.entities.Application;
import com.stc07.gubarkovag.db.entities.Book;
import com.stc07.gubarkovag.db.entities.User;

import java.util.List;

public interface BookServiceJPA {
    Book save(Book book);
    Book getById(Long id);
    List<Book> getAppsByStatus(Application.Status status);
    List<Book> getAppsByStatusAndUser(Application.Status status, User user);
}
