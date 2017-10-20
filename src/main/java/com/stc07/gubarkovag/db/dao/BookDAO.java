package com.stc07.gubarkovag.db.dao;

import com.stc07.gubarkovag.pojo.Book;

import java.util.List;
import java.util.Map;

public interface BookDAO {
    List<Book> getAll() throws BookDAOImpl.BookDAOException;
    Book getById(Integer id) throws BookDAOImpl.BookDAOException;
    boolean deleteById(Integer id) throws BookDAOImpl.BookDAOException;
    boolean deleteAll() throws BookDAOImpl.BookDAOException;
    int insertOne(Book book) throws BookDAOImpl.BookDAOException;
    int[] insertAll(List<Book> books) throws BookDAOImpl.BookDAOException;
    int update(Book changeData, Integer targetId) throws BookDAOImpl.BookDAOException;
    int[] updateAll(Map<Book, Integer> updateData) throws BookDAOImpl.BookDAOException;
    int findMaxId() throws BookDAOImpl.BookDAOException;
    List<Book> getAppsByStatus(String status) throws BookDAOImpl.BookDAOException;
    List<Book> getAppsByStatusAndUser(String status, Integer userId) throws BookDAOImpl.BookDAOException;
}
