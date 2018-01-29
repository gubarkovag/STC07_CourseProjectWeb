package com.stc07.gubarkovag.db.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "book")
@NamedQueries({
        @NamedQuery(name = "Book.getById", query = "select b from Book b where b.id = :id"),
        @NamedQuery(name = "Book.getAppsByStatus", query = "select b from Book b where b in" +
                " (select a.book from Application a where a.status = :status)"),
        @NamedQuery(name = "Book.getAppsByStatusAndUser", query = "select b from Book b where" +
                " b.user = :user and b in (select a.book from Application a" +
                " where a.status = :status)")
})
public class Book implements Serializable {
    private static final long serialVersionUID = 2L;

    private Long id;
    private User user;
    private String name;
    private String genre;
    private Application application;

    public Book() {}

    public Book(User user, String name, String genre) {
        this.user = user;
        this.name = name;
        this.genre = genre;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_id_seq")
    @SequenceGenerator(name = "book_id_seq", sequenceName = "book_id_seq")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Column
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "book")
    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }
}
