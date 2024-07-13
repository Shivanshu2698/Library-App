package com.lms.app.service;

import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.lms.app.entity.Book;
import com.lms.app.repository.BookRepository;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @Test
    void testFindAll() {
        Book book = new Book();
        book.setId(1L);
        book.setTitle("Adv Java");
        book.setIsbn("978-0134685991");

        when(bookRepository.findAll()).thenReturn(Collections.singletonList(book));

        List<Book> result = bookService.findAll();
        assertEquals(1, result.size());
        assertEquals("Adv Java", result.get(0).getTitle());
    }

    @Test
    void testFindById() {
        Book book = new Book();
        book.setId(1L);
        book.setTitle("Adv Java");
        book.setIsbn("978-0134685991");

        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        Optional<Book> result = bookService.findById(1L);
        assertTrue(result.isPresent());
        assertEquals("Adv Java", result.get().getTitle());
    }
}
