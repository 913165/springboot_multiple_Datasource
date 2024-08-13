package org.example.booksapp.controller;

import org.example.booksapp.model.Book;
import org.example.booksapp.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    public List<Book> getBooks() {
        return bookService.getAllBooks();
    }

    // get book by id
    @GetMapping("/books/{id}")
    public Book getBookById(Long id) {
        return bookService.getBookById(id);
    }

    // add book using post method and request body
    @PostMapping("/books")
    public Book addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    // update book using post method and request body
    @PutMapping("/books")
    public Book updateBook(@RequestBody Book book) {
        return bookService.updateBook(book);
    }

    // delete book by id
    @DeleteMapping("/books/{id}")
    public void deleteBook(Long id) {
        bookService.deleteBook(id);
    }
}
