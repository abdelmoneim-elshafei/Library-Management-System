package com.noob.lms.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.noob.lms.entity.Book;
import com.noob.lms.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@ExtendWith(SpringExtension.class)
//@SpringBootTest
@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetAllBooks() throws Exception {
        Book book1 = getTestBook();
        Book book2 = getTestBook();
        List<Book> books = Arrays.asList(book1, book2);

        Mockito.when(bookService.getBooks()).thenReturn(books);

        mockMvc.perform(get("/api/books")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(2)) // Verify length of JSON array
                .andExpect(jsonPath("$[0].title").value(book1.getTitle())) // Verify title of first book
                .andExpect(jsonPath("$[1].title").value(book2.getTitle())); // Verify title of second book
    }

    @Test
    public void testGetBookById() throws Exception {
        Book book = getTestBook();
        Long bookId = 1L;

        Mockito.when(bookService.getBookById(bookId)).thenReturn(Optional.of(book));

        mockMvc.perform(get("/api/books/{id}", bookId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title").value(book.getTitle()));
    }

    @Test
    public void testAddBook() throws Exception {
        Book book = getTestBook();

        Mockito.when(bookService.addBook(any(Book.class))).thenReturn(book);

        //System.out.println(objectMapper.writeValueAsString(book));

        mockMvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title").value(book.getTitle()));
    }

    @Test
    public void testUpdateBook() throws Exception {

        Book updatedBook = getTestBook();
        Long bookId = 1L;

        Mockito.when(bookService.updateBook(any(Book.class), Mockito.eq(bookId))).thenReturn(Optional.of(updatedBook));

        mockMvc.perform(put("/api/books/{id}", bookId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedBook)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title").value(updatedBook.getTitle()));
    }

    @Test
    public void testDeleteBook() throws Exception {
        Long bookId = 1L;

        mockMvc.perform(delete("/api/books/{id}", bookId))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testAddBookInvalidData() throws Exception {
        Book book = getTestBook();
        book.setAuthor("");
        Mockito.when(bookService.addBook(any(Book.class))).thenReturn(book);

        mockMvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isBadRequest());
    }

    private Book getTestBook() {
        Book b = new Book();
        int r = 1 + (int) (Math.random() * ((1000 - 1) + 1));
        b.setTitle("Title " + r);
        b.setIsbn("ISBN " + r);
        b.setAuthor("Author " + r);
        b.setPublicationYear(2015);
        b.setCopiesAvailable(3);
        return b;
    }

}