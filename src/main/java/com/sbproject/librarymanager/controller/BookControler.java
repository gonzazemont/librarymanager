package com.sbproject.librarymanager.controller;

import com.sbproject.librarymanager.dto.BookResponseDTO;
import com.sbproject.librarymanager.dto.CreateBookDTO;
import com.sbproject.librarymanager.dto.UpdateBookDTO;
import com.sbproject.librarymanager.entity.BookGenre;
import com.sbproject.librarymanager.entity.BookStatus;
import com.sbproject.librarymanager.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookControler {
    private final BookService bookService;

    // create - create a new book
    @PostMapping
    public ResponseEntity<BookResponseDTO> createBook(@Valid @RequestBody CreateBookDTO createBookDTO){
        BookResponseDTO bookResponseDTO = bookService.createBook(createBookDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookResponseDTO);

    }

    //read - get all books with pagination
    @GetMapping
    public ResponseEntity<Page<BookResponseDTO>> getAllBooks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam (defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ){
        Sort.Direction sortDirection = direction.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sortBy));
        Page<BookResponseDTO> books = bookService.getAllBooks(pageable);

        return ResponseEntity.ok(books);
    }

    //read - get book by id
    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDTO> getBookById(@PathVariable Long id) {
        BookResponseDTO book = bookService.getBookById(id);
        return ResponseEntity.ok(book);
    }

    //update - update book by id
    @PutMapping("/{id}")
    public ResponseEntity<BookResponseDTO> updateBook (
            @PathVariable Long id,
            @Valid @RequestBody UpdateBookDTO updateBookDTO
    ){
        BookResponseDTO updateBook = bookService.updateBook(id, updateBookDTO);
        return ResponseEntity.ok(updateBook);
    }

    //patch - update book status
    @PatchMapping("/{id}/status")
    public ResponseEntity<BookResponseDTO> updateBookStatus(
            @PathVariable Long id,
            @RequestParam BookStatus status
    ) {
        BookResponseDTO updatedBook = bookService.updateBookStatus(id, status);
        return ResponseEntity.ok(updatedBook);
    }

    //delete - delete book by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    //search - get books by author
    @GetMapping("/search/author")
    public ResponseEntity<java.util.List<BookResponseDTO>> getBooksByAuthor(
            @RequestParam String author
    ) {
        List<BookResponseDTO> books = bookService.getBooksByAuthor(author);
        return ResponseEntity.ok(books);
    }

    //search - get books by genre
    @GetMapping("/search/genre")
    public ResponseEntity<List<BookResponseDTO>> getBooksByGenre(
            @RequestParam BookGenre genre
    ) {
        List<BookResponseDTO> books = bookService.getBooksByGenre(genre);
        return ResponseEntity.ok(books);
    }


    //search - get books by status
    @GetMapping("/search/status")
    public ResponseEntity<List<BookResponseDTO>> getBooksByStatus(
            @RequestParam BookStatus status
    ) {
        List<BookResponseDTO> books = bookService.getBooksByStatus(status);
        return ResponseEntity.ok(books);
    }
}









