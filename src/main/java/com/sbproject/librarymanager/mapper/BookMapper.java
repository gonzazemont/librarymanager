package com.sbproject.librarymanager.mapper;

import com.sbproject.librarymanager.dto.BookResponseDTO;
import com.sbproject.librarymanager.dto.CreateBookDTO;
import com.sbproject.librarymanager.entity.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    // convert CreateBookDTO to entity
    public Book toEntity(CreateBookDTO dto) {
        Book book = new Book();
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setIsbn(dto.getIsbn());
        book.setPublicationYear(dto.getPublicationYear());
        book.setEditorial(dto.getEditorial());
        book.setNumberOfPages(dto.getNumberOfPages());
        book.setGenre(dto.getGenre());
        book.setStatus(dto.getStatus());
        book.setAcquisitionDate(dto.getAcquisitionDate());
        return book;
    }

    //convert UpdateBookDTO to entity (updates an existing entity)
    public void updateEntity(CreateBookDTO dto, Book book) {
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setIsbn(dto.getIsbn());
        book.setPublicationYear(dto.getPublicationYear());
        book.setEditorial(dto.getEditorial());
        book.setNumberOfPages(dto.getNumberOfPages());
        book.setGenre(dto.getGenre());
        book.setStatus(dto.getStatus());
        book.setAcquisitionDate(dto.getAcquisitionDate());
    }

    //convert entity to BookResponseDTO
    public BookResponseDTO toResponseDTO(Book book) {
        BookResponseDTO dto = new BookResponseDTO();
        dto.setId(book.getId());
        dto.setTitle(book.getTitle());
        dto.setAuthor(book.getAuthor());
        dto.setIsbn(book.getIsbn());
        dto.setPublicationYear(book.getPublicationYear());
        dto.setEditorial(book.getEditorial());
        dto.setNumberOfPages(book.getNumberOfPages());
        dto.setGenre(book.getGenre());
        dto.setStatus(book.getStatus());
        dto.setAcquisitionDate(book.getAcquisitionDate());
        return dto;
    }
}
