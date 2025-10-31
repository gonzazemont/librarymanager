package com.sbproject.librarymanager.repository;

import com.sbproject.librarymanager.entity.Book;
import com.sbproject.librarymanager.entity.BookGenre;
import com.sbproject.librarymanager.entity.BookStatus;
import org.springframework.data.domain.Page;//lo que devuelve
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;//lo que pido
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository <Book, Long> {

    //find by ISBN
    Optional<Book> findByIsbn(String isbn);

    //verify if a book exists by ISBN
    boolean existsByIsbn(String isbn);

    //find books by author (ingoring case)
    List<Book> findByAuthorIgnoreCase(String author);

    // find books by genre
    List<Book> findByGenre(String genre);

    //find books by status
    List<Book> findByStatus(String status);

    //find books by genre with pagination
   Page<Book> findByGenre(BookGenre genre, Pageable pageable);

    //find books by status with pagination
    Page<Book> findByStatus(BookStatus status, Pageable pageable);
}
