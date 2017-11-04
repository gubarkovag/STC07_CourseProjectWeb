package com.stc07.gubarkovag.servicesspringdatajpa;

import com.google.common.collect.Lists;
import com.stc07.gubarkovag.db.daospringdatajpa.ApplicationRepository;
import com.stc07.gubarkovag.db.entities.Application;
import com.stc07.gubarkovag.db.entities.Book;
import com.stc07.gubarkovag.db.entities.User;
import com.stc07.gubarkovag.springhelperclasses.Loggable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Loggable
public class ApplicationServiceImpl implements ApplicationService {
    private ApplicationRepository applicationRepository;

    @Autowired
    public ApplicationServiceImpl(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Application> getAll() {
        return Lists.newArrayList(applicationRepository.findAll());
    }

    @Transactional(readOnly = true)
    @Override
    public Application getByBookId(Book book) {
        return applicationRepository.getByBookId(book);
    }

    @Override
    public Application save(Application application) {
        return applicationRepository.save(application);
    }

    @Override
    public void changeApplicationStatus(Application.Status status, Long id) {
        applicationRepository.changeApplicationStatus(status, id);
    }

    @Override
    public void putRejectedToWaitingStatus(Application.Status newStatus, Application.Status prevStatus, User user) {
        applicationRepository.putRejectedToWaitingStatus(Application.Status.WAITING,
                                                Application.Status.REJECTED, user);
    }
}
