package com.stc07.gubarkovag.servicesjpa;

import com.stc07.gubarkovag.db.daojpa.BookDAOJPA;
import com.stc07.gubarkovag.db.entities.Application;
import com.stc07.gubarkovag.db.entities.Book;
import com.stc07.gubarkovag.db.entities.User;
import com.stc07.gubarkovag.springhelperclasses.Loggable;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Loggable
public class BookServiceImplJPA implements BookServiceJPA {
    private Logger logger;

    private BookDAOJPA bookDAOJPA;

    @Autowired
    public BookServiceImplJPA(BookDAOJPA bookDAOJPA) {
        this.bookDAOJPA = bookDAOJPA;
    }

    @Override
    public Book save(Book book) {
        return bookDAOJPA.save(book);
    }

    @Override
    public Book getById(Long id) {
        return bookDAOJPA.getById(id);
    }

    @Override
    public List<Book> getAppsByStatus(Application.Status status) {
        return bookDAOJPA.getAppsByStatus(status);
    }

    @Override
    public List<Book> getAppsByStatusAndUser(Application.Status status, User user/*Long userId*/) {
        return bookDAOJPA.getAppsByStatusAndUser(status, user/*userId*/);
    }
}
