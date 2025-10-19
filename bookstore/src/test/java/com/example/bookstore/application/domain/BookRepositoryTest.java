package com.example.bookstore.application.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    @DisplayName("Should create and save a new Book")
    void testCreateBook() {
        Book book = new Book();
        book.setTitle("Effective Java");
        book.setAuthor("Joshua Bloch");
        book.setIsbn("978-0134685991");

        Book saved = bookRepository.save(book);

        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getTitle()).isEqualTo("Effective Java");
    }

    @Test
    @DisplayName("Should search Book by title")
    void testSearchBook() {
        Book book = new Book();
        book.setTitle("Clean Architecture");
        book.setAuthor("Robert C. Martin");
        book.setIsbn("978-0134494166");
        bookRepository.save(book);

        List<Book> found = bookRepository.findByTitleContainingIgnoreCase("clean");
        assertThat(found).isNotEmpty();
        assertThat(found.get(0).getAuthor()).isEqualTo("Robert C. Martin");
    }

    @Test
    @DisplayName("Should delete Book")
    void testDeleteBook() {
        Book book = new Book();
        book.setTitle("Refactoring");
        book.setAuthor("Martin Fowler");
        book.setIsbn("978-0201485677");
        Book saved = bookRepository.save(book);

        bookRepository.delete(saved);
        Optional<Book> deleted = bookRepository.findById(saved.getId());

        assertThat(deleted).isEmpty();
    }
}