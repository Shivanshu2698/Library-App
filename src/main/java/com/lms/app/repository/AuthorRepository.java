package com.lms.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.app.entity.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
	List<Author> findByNameContaining(String name);
}
