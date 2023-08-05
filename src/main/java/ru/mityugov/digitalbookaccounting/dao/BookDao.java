package ru.mityugov.digitalbookaccounting.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import ru.mityugov.digitalbookaccounting.models.Book;

import java.util.List;

@Component
public class BookDao {

    private final JdbcTemplate jdbcTemplate;
    private static final BeanPropertyRowMapper<Book> BEAN_PROPERTY_ROW_MAPPER
            = new BeanPropertyRowMapper<>(Book.class);

    @Autowired
    public BookDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        BEAN_PROPERTY_ROW_MAPPER.setPrimitivesDefaultedForNullValue(true);
    }

    public List<Book> getBooks() {
        return jdbcTemplate.query("SELECT * FROM BOOK", BEAN_PROPERTY_ROW_MAPPER);
    }

    public List<Book> getBooks(int personId) {
        return jdbcTemplate.query(
                "SELECT * FROM BOOK WHERE PERSON_ID = ?",
                new Object[]{personId},
                BEAN_PROPERTY_ROW_MAPPER
        );
    }

    public void add(Book book) {
        jdbcTemplate.update(
                "INSERT INTO BOOK (NAME, AUTHOR, YEAR) VALUES (?, ?, ?)",
                book.getName(),
                book.getAuthor(),
                book.getYear()
        );
    }

    public Book getBook(int id) {
        return jdbcTemplate.query(
                        "SELECT * FROM BOOK WHERE BOOK_ID = ?",
                        new Object[]{id},
                        BEAN_PROPERTY_ROW_MAPPER)
                .stream()
                .findAny()
                .orElse(null);
    }

    public void update(int id, Book book) {
        jdbcTemplate.update(
                "UPDATE BOOK SET NAME = ?, AUTHOR = ?, YEAR = ? WHERE BOOK_ID = ?",
                book.getName(),
                book.getAuthor(),
                book.getYear(),
                id
        );
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM BOOK WHERE BOOK_ID = ?", id);
    }

    public void assign(int bookId, int personId) {
        jdbcTemplate.update("UPDATE BOOK SET PERSON_ID = ? WHERE BOOK_ID = ?", personId, bookId);
    }

    public void detach(int id) {
        jdbcTemplate.update("UPDATE BOOK SET PERSON_ID = NULL WHERE BOOK_ID = ?", id);
    }
}
