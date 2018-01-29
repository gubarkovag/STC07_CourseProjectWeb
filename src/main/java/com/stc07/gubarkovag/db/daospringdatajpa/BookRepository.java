package com.stc07.gubarkovag.db.daospringdatajpa;

import com.stc07.gubarkovag.db.entities.Application;
import com.stc07.gubarkovag.db.entities.Book;
import com.stc07.gubarkovag.db.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long> {
    @Query("select b from Book b where b in" +
            " (select a.book from Application a where a.status = ?1)")
    List<Book> getAppsByStatus(Application.Status status);

    @Query("select b from Book b where" +
            " b.user = ?2 and b in (select a.book from Application a" +
            " where a.status = ?1)")
    List<Book> getAppsByStatusAndUser(Application.Status status, User user);
}
