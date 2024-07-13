package com.lms.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.app.entity.Book;
import com.lms.app.repository.BookRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;

	public List<Book> findAll() {
		return bookRepository.findAll();
	}

	public Optional<Book> findById(Long id) {
		return bookRepository.findById(id);
	}

	public Book save(Book book) {
		return bookRepository.save(book);
	}

	public void deleteById(Long id) {
		bookRepository.deleteById(id);
	}

	public List<Book> findByAuthorId(Long authorId) {
		return bookRepository.findByAuthorId(authorId);
	}
	
	public List<Book> findByTitleContaining(String title) {
        return bookRepository.findByTitleContaining(title);
    }
}
