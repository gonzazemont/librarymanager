package com.sbproject.librarymanager.dto;

import com.sbproject.librarymanager.entity.BookGenre;
import com.sbproject.librarymanager.entity.BookStatus;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateBookDTO {
    @NotBlank(message = "Title is required")
    @Size(min=1, max=200, message = "Title must be between 1 and 200 characters")
    private String title;

    @NotBlank(message = "Author is required")
    @Size(min=1, max=100, message = "Author must be between 1 and 100 characters")
    private String author;

    @Pattern(regexp = "^(?:\\\\d{10}|\\\\d{13})$", message = "ISBN must be either 10 or 13 digits")
    private String isbn;

    @Min(value = 1000, message = "Published year must be greater than 999")
    @Max(value = 2025, message = "Published year cannot be in the future")
    private Integer publicationYear;

    @Size(max=100, message = "Editorial must not exceed 100 characters")
    private String editorial;

    @Min(value = 1, message = "Number of pages must be at least 1")
    private Integer numberOfPages;

    @NotNull(message = "Genre is required")
    private BookGenre genre;

    @NotNull(message = "Status is required")
    private BookStatus status;

    private LocalDate dateAcquired;
}
