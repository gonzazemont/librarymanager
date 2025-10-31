package com.sbproject.librarymanager.service;

import com.sbproject.librarymanager.dto.BookResponseDTO;
import com.sbproject.librarymanager.dto.CreateBookDTO;
import com.sbproject.librarymanager.dto.UpdateBookDTO;
import com.sbproject.librarymanager.entity.BookGenre;
import com.sbproject.librarymanager.entity.BookStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService {

    //CRUD Operations
    BookResponseDTO createBook(CreateBookDTO createBookDTO);
    BookResponseDTO getBookById(Long id);
    Page<BookResponseDTO> getAllBooks(Pageable pageable);
    BookResponseDTO updateBook(Long id, UpdateBookDTO updateBookDTO);
    void deleteBook(Long id);

    //partial update
    BookResponseDTO updateBookStatus(Long id, BookStatus status);

    //searches
    List<BookResponseDTO> getBooksByAuthor(String author);
    List<BookResponseDTO> getBooksByGenre(BookGenre genre);
    List<BookResponseDTO> getBooksByStatus(BookStatus status);
}
