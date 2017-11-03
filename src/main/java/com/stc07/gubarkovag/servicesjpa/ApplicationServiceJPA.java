package com.stc07.gubarkovag.servicesjpa;

import com.stc07.gubarkovag.db.entities.Application;
import com.stc07.gubarkovag.db.entities.Book;
import com.stc07.gubarkovag.db.entities.User;

import java.util.List;

public interface ApplicationServiceJPA {
    List<Application> getAll();
    Application getByBookId(Book book);
    Application save(Application application);
    void changeApplicationStatus(Application.Status status, Long id);
    void putRejectedToWaitingStatus(User user);
}
