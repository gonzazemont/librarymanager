package com.sbproject.librarymanager.service;

import com.sbproject.librarymanager.dto.BookResponseDTO;
import com.sbproject.librarymanager.dto.CreateBookDTO;
import com.sbproject.librarymanager.dto.UpdateBookDTO;
import com.sbproject.librarymanager.entity.Book;
import com.sbproject.librarymanager.entity.BookGenre;
import com.sbproject.librarymanager.entity.BookStatus;
import com.sbproject.librarymanager.exception.BookNotFoundExeption;
import com.sbproject.librarymanager.exception.DuplicateISBNException;
import com.sbproject.librarymanager.mapper.BookMapper;
import com.sbproject.librarymanager.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class BookServicceImpl implements BookService{
    private  final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public BookResponseDTO createBook(CreateBookDTO createBookDTO) {
        //verify if book with same isbn exists
        if(createBookDTO.getIsbn() != null && bookRepository.existsByIsbn(createBookDTO.getIsbn())){
            throw new DuplicateISBNException(createBookDTO.getIsbn());
        }

        //convert DTO to entity
        Book book = bookMapper.toEntity(createBookDTO);

        //save in DB
        Book savedBook = bookRepository.save(book);

        //convert entity to DTO
        return bookMapper.toResponseDTO(savedBook);
    }

    @Override
    @Transactional(readOnly = true)
    public BookResponseDTO getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundExeption(id));
        return bookMapper.toResponseDTO(book);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<BookResponseDTO> getAllBooks(Pageable pageable){
        Page<Book> books = bookRepository.findAll(pageable);
        return books.map(bookMapper::toResponseDTO);
    }

    @Override
    public BookResponseDTO updateBook(Long id, UpdateBookDTO updateBookDTO) {
        // verify book exists
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundExeption(id));

        //Verify duplicate ISBN (if changing)
        if (updateBookDTO.getIsbn() != null &&
                !updateBookDTO.getIsbn().equals(existingBook.getIsbn())) {
            if (bookRepository.existsByIsbn(updateBookDTO.getIsbn())) {
                throw new DuplicateISBNException(updateBookDTO.getIsbn());
            }
        }

        // Update the existing entity with the new data
        bookMapper.updateEntity(updateBookDTO, existingBook);

        //save changes
        Book updatedBook = bookRepository.save(existingBook);

        return bookMapper.toResponseDTO(updatedBook);
    }

    @Override
    public void deleteBook(Long id) {
        if(!bookRepository.existsById(id)){
            throw new BookNotFoundExeption(id);
        }
        bookRepository.deleteById(id);
    }

    @Override
    public BookResponseDTO updateBookStatus(Long id, BookStatus status) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundExeption(id));

        book.setStatus(status);
        Book updatedBook = bookRepository.save(book);

        return bookMapper.toResponseDTO(updatedBook);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookResponseDTO> getBooksByAuthor(String author) {
        List<Book> books = bookRepository.findByAuthorContainingIgnoreCase(author);
        return books.stream()
                .map(bookMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookResponseDTO> getBooksByGenre(BookGenre genre) {
        List<Book> books = bookRepository.findByGenre(genre.name());
        return books.stream()
                .map(bookMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookResponseDTO> getBooksByStatus(BookStatus status) {
        List<Book> books = bookRepository.findByStatus(status.name());
        return books.stream()
                .map(bookMapper::toResponseDTO)
                .collect(Collectors.toList());
    }


}
