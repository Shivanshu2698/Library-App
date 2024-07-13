package com.lms.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.app.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long>{
	 List<Book> findByAuthorId(Long authorId);
	 List<Book> findByTitleContaining(String title);
}
