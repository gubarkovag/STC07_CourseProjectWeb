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
public class ApplicationDAOImplJPA implements ApplicationDAOJPA {
    private Logger logger;

    @PersistenceContext
    private EntityManager em;

    @Transactional(readOnly = true)
    @Override
    public List<Application> getAll() {
        return em.createNamedQuery("Application.getAll", Application.class).getResultList();
    }

    @Transactional(readOnly = true)
    @Override
    public Application getByBookId(Book book) {
        return em.createNamedQuery("Application.getByBookId", Application.class)
                .setParameter("book", book).getSingleResult();
    }

    @Override
    public Application save(Application application) {
        if (application.getId() == null) {
            em.persist(application);
        } else {
            em.merge(application);
        }

        return application;
    }

    @Override
    public void changeApplicationStatus(Application.Status status, Long id) {
        em.createNamedQuery("Application.setStatus").setParameter("status", status)
                .setParameter("id", id).executeUpdate();
    }

    @Override
    public void putRejectedToWaitingStatus(User user) {
        em.createNamedQuery("Application.setRejectedToWaitingStatus")
                .setParameter("prevStatus", Application.Status.REJECTED)
                .setParameter("nextStatus", Application.Status.WAITING)
                .setParameter("user", user)
                .executeUpdate();
    }
}
