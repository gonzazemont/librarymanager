package com.sbproject.librarymanager.exception;

public class BookNotFoundExeption extends RuntimeException{
    public BookNotFoundExeption(Long id) {
        super("Book with ID " + id + " not found.");
    }

    public BookNotFoundExeption (String message){
        super(message);
    }
}
