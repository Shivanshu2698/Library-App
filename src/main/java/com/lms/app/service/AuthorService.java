package com.lms.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.app.entity.Author;
import com.lms.app.repository.AuthorRepository;

@Service
public class AuthorService {

	@Autowired
	private AuthorRepository authorRepository;

	public List<Author> findAll() {
		return authorRepository.findAll();
	}

	public Optional<Author> findById(Long id) {
		return authorRepository.findById(id);
	}

	public Author save(Author author) {
		return authorRepository.save(author);
	}

	public void deleteById(Long id) {
		authorRepository.deleteById(id);
	}

	public List<Author> findByNameContaining(String name) {
		return authorRepository.findByNameContaining(name);
	}

}
