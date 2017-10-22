package com.stc07.gubarkovag.services;

import com.stc07.gubarkovag.pojo.Book;

import java.util.List;
import java.util.Map;

public interface BookService {
    List<Book> getAll() throws BookServiceImpl.BookServiceException;
    Book getById(Integer id) throws BookServiceImpl.BookServiceException;
    boolean deleteById(Integer id) throws BookServiceImpl.BookServiceException;
    boolean deleteAll() throws BookServiceImpl.BookServiceException;
    int insertOne(Book book) throws BookServiceImpl.BookServiceException;
    int[] insertAll(List<Book> books) throws BookServiceImpl.BookServiceException;
    int update(Book changeData, Integer targetId) throws BookServiceImpl.BookServiceException;
    int[] updateAll(Map<Book, Integer> updateData) throws BookServiceImpl.BookServiceException;
    int findMaxId() throws BookServiceImpl.BookServiceException;
    List<Book> getAppsByStatus(String status) throws BookServiceImpl.BookServiceException;
    List<Book> getAppsByStatusAndUser(String status, Integer userId) throws BookServiceImpl.BookServiceException;

}
