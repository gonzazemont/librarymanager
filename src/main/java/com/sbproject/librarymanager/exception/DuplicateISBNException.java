package com.sbproject.librarymanager.exception;

public class DuplicateISBNException extends RuntimeException {
    public DuplicateISBNException(String isbn) {
        super("A book with ISBN " + isbn + " already exists.");
    }
}
