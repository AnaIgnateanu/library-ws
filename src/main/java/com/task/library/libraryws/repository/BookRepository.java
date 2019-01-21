package com.task.library.libraryws.repository;

import com.task.library.libraryws.domain_book.Book;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface BookRepository extends JpaRepository<Book, Long> {
    Book findBookById(long id);
    
    //List<Book> findAllBooks();

    @Override
    <S extends Book> S save(S s);
}