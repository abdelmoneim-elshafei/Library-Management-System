package com.noob.lms.service;

import com.noob.lms.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    public Book addBook(Book book);
    public Optional<Book> getBookById(Long id);
    public List<Book> getBooks();
    public Optional<Book> updateBook(Book book, Long id);
    public Book deleteBook(Long id);
}
