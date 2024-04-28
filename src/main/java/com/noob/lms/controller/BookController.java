package com.noob.lms.controller;

import com.noob.lms.entity.Book;
import com.noob.lms.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;
    @GetMapping
    public ResponseEntity<?> getBooks() {
        return new ResponseEntity<>(bookService.getBooks(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBookById(@PathVariable Long id) {
        return new ResponseEntity<>(bookService.getBookById(id),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addBook(@RequestBody Book book) {
        return new ResponseEntity<>(bookService.addBook(book),HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBook(@RequestBody Book book, @PathVariable Long id) {
        return new ResponseEntity<>(bookService.updateBook(book,id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id){
        return new ResponseEntity<>(bookService.deleteBook(id),HttpStatus.NO_CONTENT);
    }

}
