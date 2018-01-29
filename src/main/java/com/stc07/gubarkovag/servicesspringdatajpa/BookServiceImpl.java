package com.stc07.gubarkovag.servicesspringdatajpa;

import com.stc07.gubarkovag.db.daospringdatajpa.BookRepository;
import com.stc07.gubarkovag.db.entities.Application;
import com.stc07.gubarkovag.db.entities.Book;
import com.stc07.gubarkovag.db.entities.User;
import com.stc07.gubarkovag.springhelperclasses.Loggable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Loggable
public class BookServiceImpl implements BookService {
    private BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book getById(Long id) {
        return bookRepository.findOne(id);
    }

    @Override
    public List<Book> getAppsByStatus(Application.Status status) {
        return bookRepository.getAppsByStatus(status);
    }

    @Override
    public List<Book> getAppsByStatusAndUser(Application.Status status, User user) {
        return bookRepository.getAppsByStatusAndUser(status, user);
    }
}
