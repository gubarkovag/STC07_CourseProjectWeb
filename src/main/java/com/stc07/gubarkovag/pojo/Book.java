package com.stc07.gubarkovag.pojo;

import javax.xml.bind.annotation.*;
import java.io.Serializable;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Book implements Serializable {
    private static final long serialVersionUID = 1L;

    @XmlAttribute
    private Integer id;
    @XmlElement
    private Integer userId;
    @XmlElement
    private String name;
    @XmlElement
    private String genre;

    public Book() {

    }

    public Book(Integer id, Integer userId, String name, String genre) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.genre = genre;
    }

    public Book(Integer userId, String name, String genre) {
        this.userId = userId;
        this.name = name;
        this.genre = genre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Book: [id = ").append(id)
            .append(", user_id = ").append(userId).append(", name = ")
            .append(name).append(", genre = ").append(genre).append("]");

        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (!id.equals(book.id)) return false;
        if (!userId.equals(book.userId)) return false;
        if (!name.equals(book.name)) return false;
        return genre != null ? genre.equals(book.genre) : book.genre == null;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + userId.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + (genre != null ? genre.hashCode() : 0);
        return result;
    }
}
