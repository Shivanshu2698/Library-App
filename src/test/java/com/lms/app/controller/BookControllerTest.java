package com.lms.app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lms.app.entity.Author;
import com.lms.app.entity.Book;
import com.lms.app.service.AuthorService;
import com.lms.app.service.BookService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@ExtendWith(SpringExtension.class)
@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @MockBean
    private AuthorService authorService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllBooks() throws Exception {
        Book book = new Book();
        book.setId(1L);
        book.setTitle("Spring");
        book.setIsbn("978-0134685991");

        Mockito.when(bookService.findAll()).thenReturn(Collections.singletonList(book));

        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Spring"));
    }

    @Test
    void testCreateBook() throws Exception {
        Author author = new Author();
        author.setId(1L);
        author.setName("Joshua Bloch");

        Book book = new Book();
        book.setId(1L);
        book.setTitle("Spring");
        book.setIsbn("978-0134685991");
        book.setAuthor(author);

        Mockito.when(authorService.findById(1L)).thenReturn(Optional.of(author));
        Mockito.when(bookService.save(any(Book.class))).thenReturn(book);

        mockMvc.perform(post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Spring"));
    }

    @Test
    void testUpdateBook() throws Exception {
        Author author = new Author();
        author.setId(1L);
        author.setName("Prateek Gautam");

        Book book = new Book();
        book.setId(1L);
        book.setTitle("Spring");
        book.setIsbn("978-0134685991");
        book.setAuthor(author);

        Mockito.when(bookService.findById(1L)).thenReturn(Optional.of(book));
        Mockito.when(bookService.save(any(Book.class))).thenReturn(book);

        mockMvc.perform(put("/books/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Spring"));
    }

    @Test
    void testDeleteBook() throws Exception {
        Mockito.doNothing().when(bookService).deleteById(1L);

        mockMvc.perform(delete("/books/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testSearchBooksByTitle() throws Exception {
        Book book = new Book();
        book.setId(1L);
        book.setTitle("Spring");
        book.setIsbn("978-0134685991");

        Mockito.when(bookService.findByTitleContaining("Java")).thenReturn(Collections.singletonList(book));

        mockMvc.perform(get("/books/search?title=Java"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Spring"));
    }
}
