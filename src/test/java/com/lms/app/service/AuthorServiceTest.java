package com.lms.app.service;

import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.lms.app.entity.Author;
import com.lms.app.repository.AuthorRepository;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class AuthorServiceTest {

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorService authorService;

    @Test
    void testFindAll() {
        Author author = new Author();
        author.setId(1L);
        author.setName("Prateek Gautam");

        when(authorRepository.findAll()).thenReturn(Collections.singletonList(author));

        List<Author> result = authorService.findAll();
        assertEquals(1, result.size());
        assertEquals("Prateek Gautam", result.get(0).getName());
    }

    @Test
    void testFindById() {
        Author author = new Author();
        author.setId(1L);
        author.setName("Prateek Gautam");

        when(authorRepository.findById(1L)).thenReturn(Optional.of(author));

        Optional<Author> result = authorService.findById(1L);
        assertTrue(result.isPresent());
        assertEquals("Prateek Gautam", result.get().getName());
    }
}
