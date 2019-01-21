package com.task.library.libraryws.repository;

import com.task.library.libraryws.domain_author.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository  extends JpaRepository<Author, Long> {
    Author findAuthorById(long id);
    Author findAuthorIdByName(String name);
}
