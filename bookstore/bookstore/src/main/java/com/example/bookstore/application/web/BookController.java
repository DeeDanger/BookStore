package com.example.bookstore.application.web;

import com.example.bookstore.application.domain.Book;
import com.example.bookstore.application.domain.BookRepository;

import com.example.bookstore.application.domain.CategoryRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/*@Controller
public class BookController {

    @GetMapping({ "/", "/index" })
    public Book getBook() {
        return new Book("Effective Java", "Joshua Bloch", 2018, "978-0134685991",
                45.99);
    }

}*/

@Controller
public class BookController {

    // @Autowired
    private BookRepository bookRepository;
    private CategoryRepository categoryRepository;

    public BookController(BookRepository bookRepository, CategoryRepository categoryRepository) {
        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository;
    }

    // --- List books ---
    @RequestMapping(value = { "/", "/booklist" })
    public String showBookList(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "booklist";
    }

    // --- Add book form ---
    @RequestMapping("/addbook")
    public String showAddBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "addbook";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Book book) {
        bookRepository.save(book);
        return "redirect:booklist";
    }

    // --- Delete book ---
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable("id") Long id, Model model) {
        bookRepository.deleteById(id);
        return "redirect:/booklist";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String showModBook(@PathVariable("id") Long bookId, Model model) {
        model.addAttribute("book", bookRepository.findById(bookId).orElse(new Book()));
        model.addAttribute("categories", categoryRepository.findAll());
        return "modifybook"; // modifybook.html
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String updateBook(@PathVariable("id") Long bookId, Book book) {
        book.setId(bookId); // Varmista, että id pysyy samana
        bookRepository.save(book);
        return "redirect:/booklist";
    }
}