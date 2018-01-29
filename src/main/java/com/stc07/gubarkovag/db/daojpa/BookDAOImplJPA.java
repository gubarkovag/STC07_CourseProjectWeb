package com.stc07.gubarkovag.db.daojpa;

import com.stc07.gubarkovag.db.entities.Application;
import com.stc07.gubarkovag.db.entities.Book;
import com.stc07.gubarkovag.db.entities.User;
import com.stc07.gubarkovag.springhelperclasses.Loggable;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
@Loggable
public class BookDAOImplJPA implements BookDAOJPA {
    private Logger logger;

    @PersistenceContext
    private EntityManager em;

    @Override
    public Book save(Book book) {
        if (book.getId() == null) {
            em.persist(book);
        } else {
            em.merge(book);
        }

        return book;
    }

    @Transactional(readOnly = true)
    @Override
    public Book getById(Long id) {
        return em.createNamedQuery("Book.getById", Book.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Book> getAppsByStatus(Application.Status status) {
        return em.createNamedQuery("Book.getAppsByStatus", Book.class)
                .setParameter("status", status)
                .getResultList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Book> getAppsByStatusAndUser(Application.Status status, User user) {
        return em.createNamedQuery("Book.getAppsByStatusAndUser", Book.class)
                .setParameter("user", user)
                .setParameter("status", status)
                .getResultList();
    }
}
