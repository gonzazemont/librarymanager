package com.sbproject.librarymanager.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(nullable = false, length = 100)
    private String author;

    @Column(unique = true, length = 13)
    private String isbn;

    @Column(name = "published_year")
    private Integer publishedYear;

    @Column(length = 100)
    private String editorial;

    @Column(name = "number_of_pages")
    private Integer numberOfPages;

    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    private BookGenre gender;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private BookStatus status;

    @Column(name = "date_acquired")
    private LocalDate dateAcquired;
}
