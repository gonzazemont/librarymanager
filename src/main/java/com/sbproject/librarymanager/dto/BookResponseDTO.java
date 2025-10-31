package com.sbproject.librarymanager.dto;

import com.sbproject.librarymanager.entity.BookGenre;
import com.sbproject.librarymanager.entity.BookStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookResponseDTO {
    private Long id;
    private String title;
    private String author;
    private String isbn;
    private Integer publicationYear;
    private String editorial;
    private Integer numberOfPages;
    private BookGenre genre;
    private BookStatus status;
    private LocalDate acquisitionDate;
}
