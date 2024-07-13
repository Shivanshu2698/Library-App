package com.lms.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lms.app.entity.Author;
import com.lms.app.service.AuthorService;

@RestController
@RequestMapping("/authors")
public class AuthorController {

	@Autowired
	private AuthorService authorService;

	// url - http://localhost:8070/authors
	@GetMapping
	public List<Author> getAllAuthors() {
		return authorService.findAll();
	}

	// url - http://localhost:8070/authors
	// Json body-
	/*
	 * { "name": "Author Name", "biography": "Author biography" }
	 */
	@PostMapping
	public Author createAuthor(@RequestBody Author author) {
		return authorService.save(author);
	}

	// URL: http://localhost:8070/authors/search?name={name}
	@GetMapping("/search")
	public ResponseEntity<List<Author>> searchAuthorsByName(@RequestParam("name") String name) {
		List<Author> authors = authorService.findByNameContaining(name);
		return ResponseEntity.ok(authors);
	}
}
