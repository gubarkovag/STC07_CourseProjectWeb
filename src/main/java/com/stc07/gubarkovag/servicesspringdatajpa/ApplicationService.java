package com.stc07.gubarkovag.servicesspringdatajpa;

import com.stc07.gubarkovag.db.entities.Application;
import com.stc07.gubarkovag.db.entities.Book;
import com.stc07.gubarkovag.db.entities.User;

import java.util.List;

public interface ApplicationService {
    List<Application> getAll();
    Application getByBookId(Book book);
    Application save(Application application);
    void changeApplicationStatus(Application.Status status, Long id);
    void putRejectedToWaitingStatus(Application.Status newStatus,
                                    Application.Status prevStatus, User user);
}
