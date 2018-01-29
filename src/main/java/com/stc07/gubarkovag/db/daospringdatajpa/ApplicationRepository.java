package com.stc07.gubarkovag.db.daospringdatajpa;

import com.stc07.gubarkovag.db.entities.Application;
import com.stc07.gubarkovag.db.entities.Book;
import com.stc07.gubarkovag.db.entities.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ApplicationRepository extends CrudRepository<Application, Long> {

    @Query("select a from Application a where a.book = ?1")
    Application getByBookId(Book book);

    @Modifying
    @Query("update Application a set a.status = :status where a.id = :id")
    void changeApplicationStatus(@Param("status") Application.Status status, @Param("id") Long id);

    @Modifying
    @Query(value = "update Application a set a.status = :newStatus where" +
            " a.status = :prevStatus and a.user = :user")
    void putRejectedToWaitingStatus(@Param("newStatus") Application.Status newStatus,
                                    @Param("prevStatus") Application.Status prevStatus,
                                    @Param("user") User user);
}
