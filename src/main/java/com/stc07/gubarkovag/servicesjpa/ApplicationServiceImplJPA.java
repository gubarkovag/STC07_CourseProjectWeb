package com.stc07.gubarkovag.servicesjpa;

import com.stc07.gubarkovag.db.daojpa.ApplicationDAOJPA;
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
public class ApplicationServiceImplJPA implements ApplicationServiceJPA {
    private Logger logger;

    private ApplicationDAOJPA applicationDAOJPA;

    @Autowired
    public ApplicationServiceImplJPA(ApplicationDAOJPA applicationDAOJPA) {
        this.applicationDAOJPA = applicationDAOJPA;
    }

    @Override
    public List<Application> getAll() {
        return applicationDAOJPA.getAll();
    }

    @Override
    public Application getByBookId(Book book) {
        return applicationDAOJPA.getByBookId(book);
    }

    @Override
    public Application save(Application application) {
        return applicationDAOJPA.save(application);
    }

    /*@Override
    public List<Application> saveAll(List<Application> applications) {
        return applicationDAOJPA.saveAll(applications);
    }*/

    @Override
    public void changeApplicationStatus(Application.Status status, Long id) {
        applicationDAOJPA.changeApplicationStatus(status, id);
    }

    @Override
    public void putRejectedToWaitingStatus(User user/*Long userId*/) {
        applicationDAOJPA.putRejectedToWaitingStatus(user/*userId*/);
    }
}
