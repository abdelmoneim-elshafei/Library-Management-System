package com.noob.lms.controller;

import com.noob.lms.entity.Book;
import com.noob.lms.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;
    @GetMapping
    @Operation(summary = "Get All Books", description = "Retrieve a list of all books.")
    public ResponseEntity<?> getBooks() {
        return new ResponseEntity<>(bookService.getBooks(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get book by Id", description = "Retrieve details of a specific book by ID.")
    public ResponseEntity<?> getBookById(@Parameter(description = "Book Id", example = "12") @PathVariable Long id) {
        return new ResponseEntity<>(bookService.getBookById(id),HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Add a new Book", description = "Add a new book to the library.")
    public ResponseEntity<?> addBook(@Parameter(description = "Book object to add", schema = @Schema(implementation = Book.class)) @Valid @RequestBody Book book) {
        return new ResponseEntity<>(bookService.addBook(book),HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Book", description = "Update details of specific book by ID.")
    public ResponseEntity<?> updateBook(@Parameter(description = "Book object to update", schema = @Schema(implementation = Book.class)) @Valid @RequestBody Book book,
                                        @Parameter(description = "Book Id", example = "12") @PathVariable Long id) {
        return new ResponseEntity<>(bookService.updateBook(book,id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Book", description = "Delete book by ID.")
    public ResponseEntity<?> deleteBook(@Parameter(description = "Book Id", example = "12") @PathVariable Long id){
        return new ResponseEntity<>(bookService.deleteBook(id),HttpStatus.NO_CONTENT);
    }

}
