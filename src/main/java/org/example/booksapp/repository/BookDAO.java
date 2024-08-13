package org.example.booksapp.repository;

import org.example.booksapp.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Component
public class BookDAO {

    private final JdbcTemplate jdbcTemplate;

    public BookDAO(@Qualifier("mysqldatasource") DataSource mysqldatasource) {
        this.jdbcTemplate = new JdbcTemplate(mysqldatasource);
    }


    // getAllBooks
    public List<Book> getAllBooks() {
        //JdbcTemplate jdbcTemplate = new JdbcTemplate(mysqldatasource);
        return jdbcTemplate.query("select * from books", (rs, rowNum) ->
                new Book(
                        rs.getLong("id"),
                        rs.getString("title"),
                        rs.getString("ISBN"),
                        rs.getDate("publicationdate"),
                        rs.getString("authorname")));

    }

    // get book by id
    public Book getBookById(Long id) {
        return jdbcTemplate.queryForObject("select * from books where id = ?", new Object[]{id}, (rs, rowNum) ->
                new Book(
                        rs.getString("title"),
                        rs.getString("ISBN"),
                        rs.getDate("publicationdate"),
                        rs.getString("authorname")));
    }

    // add book
    public Book addBook(Book book) {
        jdbcTemplate.update("insert into books (title, ISBN, publicationdate, authorname) values (?, ?, ?, ?)",
                book.getTitle(), book.getISBN(), book.getPublicationDate(), book.getAuthorName());
        return book;
    }

    // update book
    public Book updateBook(Book book) {
        jdbcTemplate.update("update books set title = ?, ISBN = ?, publicationdate = ?, authorname = ? where id = ?",
                book.getTitle(), book.getISBN(), book.getPublicationDate(), book.getAuthorName());
        return book;
    }

    // delete book by id
    public void deleteBook(Long id) {
        jdbcTemplate.update("delete from books where id = ?", id);
    }
}
