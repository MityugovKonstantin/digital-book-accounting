package ru.mityugov.digitalbookaccounting.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "person")
public class Person {

    @Id
    @Column(name = "person_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int personId;

    @Column(name = "full_name")
    @NotEmpty(message = "Full name cannot be empty!")
    @Size(min = 1, max = 100, message = "Full name cannot be less than 1 and more than 100 characters!")
    private String fullName;

    @Column(name = "born_year")
    @Min(value = 1800, message = "Born year cannot be less then 1800!")
    private int bornYear;

    @OneToMany(mappedBy = "owner")
    List<Book> books;

    public Person(String fullName, int bornYear) {
        this.fullName = fullName;
        this.bornYear = bornYear;
    }

    public Person() {
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getBornYear() {
        return bornYear;
    }

    public void setBornYear(int bornYear) {
        this.bornYear = bornYear;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
