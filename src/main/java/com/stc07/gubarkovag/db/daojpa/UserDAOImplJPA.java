package com.stc07.gubarkovag.db.daojpa;

import com.stc07.gubarkovag.db.entities.User;
import com.stc07.gubarkovag.springhelperclasses.Loggable;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
@Loggable
public class UserDAOImplJPA implements UserDAOJPA {
    private Logger logger;

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<User> getAll() {
        return em.createNamedQuery("User.getAll", User.class).getResultList();
    }

    @Override
    public User getById(Long id) {
        return em.createNamedQuery("User.getById", User.class).setParameter("id", id).getSingleResult();
    }

    @Override
    public int deleteById(Long id) {
        return em.createNamedQuery("User.deleteById").setParameter("id", id).executeUpdate();
    }

    @Override
    public User save(User user) {
        if (user.getId() == null) {
            em.persist(user);
        } else {
            em.merge(user);
        }

        return user;
    }

    @Override
    public User getUserByLoginAndPassword(String username, String password) {
        User user = null;
        try {
            user = em.createNamedQuery("User.getUserByLoginAndPassword", User.class)
                    .setParameter("login", username)
                    .setParameter("password", password).getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }

        return user;
    }
}
