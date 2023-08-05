package ru.mityugov.digitalbookaccounting.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Book {

    private int bookId;

    private Integer personId;

    @NotEmpty(message = "Book name cannot be empty!")
    @Size(min = 1, max = 200, message = "Book name cannot be less than 1 and more than 200 characters!")
    private String name;

    @NotEmpty(message = "Book author cannot be empty!")
    @Size(min = 1, max = 100, message = "Book author cannot be less than 1 and more than 100 characters!")
    private String author;

    private int year;

    public Book(int bookId, int personId, String name, String author, int year) {
        this.bookId = bookId;
        this.personId = personId;
        this.name = name;
        this.author = author;
        this.year = year;
    }

    public Book() {}

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
