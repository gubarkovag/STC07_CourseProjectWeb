package com.stc07.gubarkovag.db.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "users")
@NamedQueries({
        @NamedQuery(name = "User.getAll", query = "select u from User u"),
        @NamedQuery(name = "User.getById", query = "select u from User u where u.id = :id"),
        @NamedQuery(name = "User.deleteById", query = "delete from User u where u.id = :id"),
        @NamedQuery(name = "User.getUserByLoginAndPassword", query = "select u from User u where " +
                                            "u.login = :login and u.password = :password")
})
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    public enum Role {
        ROLE_ADMIN,
        ROLE_USER
    }

    private Long id;
    private String login;
    private String password;
    private Role role;
    private Set<Book> books;
    private Set<Application> applications;

    public User() {}

    public User(String login, String password, Role role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public User(Long id, String login, String password, Role role) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
    @SequenceGenerator(name = "user_id_seq", sequenceName = "user_id_seq")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Column
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Enumerated(EnumType.STRING)
    @Column
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "user")
    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "user")
    public Set<Application> getApplications() {
        return applications;
    }

    public void setApplications(Set<Application> applications) {
        this.applications = applications;
    }
}
