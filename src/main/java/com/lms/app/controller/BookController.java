package com.lms.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lms.app.entity.Author;
import com.lms.app.entity.Book;
import com.lms.app.service.AuthorService;
import com.lms.app.service.BookService;

@RestController
@RequestMapping("/books")
public class BookController {

	@Autowired
	private BookService bookService;

	@Autowired
	private AuthorService authorService;

	// url - http://localhost:8070/books
	@GetMapping
	public List<Book> getAllBooks() {
		return bookService.findAll();
	}

	// url - http://localhost:8070/books/author/{id}
	@GetMapping("/author/{authorId}")
	public List<Book> getBooksByAuthorId(@PathVariable Long authorId) {
		return bookService.findByAuthorId(authorId);
	}

	// url - http://localhost:8070/books
	// Json body-
	/*
	 * { "title": "SpringBoot Java", "isbn": "978-0134685991", "author": { "id": 3 }
	 * }
	 * 
	 */
	@PostMapping
	public ResponseEntity<Book> createBook(@RequestBody Book book) {
		if (book.getAuthor() == null || book.getAuthor().getId() == 0) {
			return ResponseEntity.badRequest().build();
		}

		Optional<Author> author = authorService.findById(book.getAuthor().getId());
		if (author.isPresent()) {
			book.setAuthor(author.get());
			Book savedBook = bookService.save(book);
			return ResponseEntity.ok(savedBook);
		} else {
			return ResponseEntity.badRequest().build();
		}
	}

	// url - localhost:8070/books/{id}
	// Json Body-
	/*
	 * { "title": "SpringBoot Java", "isbn": "978-0134685991", "author": { "id": 3 }
	 * }
	 * 
	 */
	@PutMapping("/{id}")
	public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book bookDetails) {
		Optional<Book> optionalBook = bookService.findById(id);
		if (optionalBook.isPresent()) {
			Book book = optionalBook.get();
			book.setTitle(bookDetails.getTitle());
			book.setIsbn(bookDetails.getIsbn());
			book.setAuthor(bookDetails.getAuthor());
			Book updatedBook = bookService.save(book);
			return ResponseEntity.ok(updatedBook);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	// url - http://localhost:8070/books/{id}
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
		bookService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	// URL - http://localhost:8070/books/search?title={title}
    @GetMapping("/search")
    public ResponseEntity<List<Book>> searchBooksByTitle(@RequestParam("title") String title) {
        List<Book> books = bookService.findByTitleContaining(title);
        return ResponseEntity.ok(books);
    }
}
