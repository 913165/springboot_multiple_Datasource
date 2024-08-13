package org.example.booksapp.service;

import org.example.booksapp.model.Book;
import org.example.booksapp.repository.BookDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

   @Autowired
   private BookDAO bookDAO;

    public List<Book> getAllBooks() {
         return bookDAO.getAllBooks();
    }

    public Book getBookById(Long id) {
         return bookDAO.getBookById(id);
    }

    public Book addBook(Book book) {
         return bookDAO.addBook(book);
    }

    public Book updateBook(Book book) {
         return bookDAO.updateBook(book);
    }

    public void deleteBook(Long id) {
         bookDAO.deleteBook(id);
    }



}
