package com.noob.lms.service;

import com.noob.lms.entity.Book;
import com.noob.lms.repository.BookRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepo bookRepo;

    @Override
    public Book addBook(Book book) {
        return bookRepo.save(book);
    }

    @Override
    public Optional<Book> getBookById(Long id) {
        return bookRepo.findById(id);
    }

    @Override
    public List<Book> getBooks() {
        return bookRepo.findAll();
    }

    @Override
    public Optional<Book> updateBook(Book book, Long id) {
        Book b = bookRepo.findById(id).orElseThrow(()-> new RuntimeException("Not Found"));
        b.setAuthor(book.getAuthor());
        b.setIsbn(book.getIsbn());
        b.setTitle(book.getTitle());
        b.setPublicationYear(book.getPublicationYear());
        b.setCopiesAvailable(book.getCopiesAvailable());
        return Optional.of(bookRepo.save(b));
    }

    @Override
    public Book deleteBook(Long id) {
        Book b = bookRepo.findById(id).orElseThrow(()-> new RuntimeException("Not Found"));
        bookRepo.deleteById(id);
        return b;
    }
}
