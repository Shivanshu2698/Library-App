package com.lms.app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lms.app.entity.Author;
import com.lms.app.service.AuthorService;
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
@WebMvcTest(AuthorController.class)
public class AuthorControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AuthorService authorService;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void testGetAllAuthors() throws Exception {
		Author author = new Author();
		author.setId(1L);
		author.setName("Prateek Gautam");

		Mockito.when(authorService.findAll()).thenReturn(Collections.singletonList(author));

		mockMvc.perform(get("/authors")).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].name").value("Prateek Gautam"));
	}

	@Test
	void testCreateAuthor() throws Exception {
		Author author = new Author();
		author.setId(1L);
		author.setName("Prateek Gautam");

		Mockito.when(authorService.save(any(Author.class))).thenReturn(author);

		mockMvc.perform(post("/authors").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(author))).andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value("Prateek Gautam"));
	}

	@Test
	void testUpdateAuthor() throws Exception {
		Author author = new Author();
		author.setId(1L);
		author.setName("Prateek Gautam");

		Mockito.when(authorService.findById(1L)).thenReturn(Optional.of(author));
		Mockito.when(authorService.save(any(Author.class))).thenReturn(author);

		mockMvc.perform(put("/authors/1").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(author))).andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value("Prateek Gautam"));
	}

	@Test
	void testDeleteAuthor() throws Exception {
		Mockito.doNothing().when(authorService).deleteById(1L);

		mockMvc.perform(delete("/authors/1")).andExpect(status().isNoContent());
	}

	@Test
	void testSearchAuthorsByName() throws Exception {
		Author author = new Author();
		author.setId(1L);
		author.setName("Prateek Gautam");

		Mockito.when(authorService.findByNameContaining("Joshua")).thenReturn(Collections.singletonList(author));

		mockMvc.perform(get("/authors/search?name=Joshua")).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].name").value("Prateek Gautam"));
	}
}
