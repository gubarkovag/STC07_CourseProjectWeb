package com.stc07.gubarkovag.db.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "application")
@NamedQueries({
        @NamedQuery(name = "Application.getAll", query = "select a from Application a"),
        @NamedQuery(name = "Application.getByBookId", query = "select a from Application a where a.book = :book"),
        @NamedQuery(name = "Application.setStatus", query = "update Application a set a.status = :status where a.id = :id"),
        @NamedQuery(name = "Application.setRejectedToWaitingStatus",
                query = "update Application a set a.status = :nextStatus where a.status = :prevStatus and a.user = :user")
})
public class Application implements Serializable {
    private static final long serialVersionUID = 3L;

    public enum Status {
        APPROVED,
        WAITING,
        REJECTED
    }

    private Long id;
    private Status status;
    private User user;
    private Book book;

    public Application() {}

    public Application(User user, Book book, Status status) {
        this.user = user;
        this.book = book;
        this.status = status;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "application_id_seq")
    @SequenceGenerator(name = "application_id_seq", sequenceName = "application_id_seq")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Enumerated(EnumType.STRING)
    @Column
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
