package com.example.bookstore.application.web;

import org.springframework.web.bind.annotation.*;

import com.example.bookstore.application.domain.Book;
import com.example.bookstore.application.domain.BookRepository;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookRestController {

    private final BookRepository bookRepository;

    public BookRestController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // Palauttaa kaikki kirjat
    @GetMapping
    public List<Book> getAllBooks() {
        return (List<Book>) bookRepository.findAll();
    }

    // Palauttaa yhden kirjan ID:n perusteella
    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));
    }
}
