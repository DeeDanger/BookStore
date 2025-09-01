package com.example.bookstore.controller;

import com.example.bookstore.domain.Book;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    @GetMapping("/index")
    public Book getBook() {
        // Returning a sample book
        return new Book("Effective Java", "Joshua Bloch", 2018, "978-0134685991", 45.99);
    }
}