package ru.mityugov.digitalbookaccounting.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Person {

    private int id;

    @NotEmpty(message = "Full name cannot be empty!")
    @Size(min = 1, max = 100, message = "Full name cannot be less than 1 and more than 100 characters!")
    private String fullName;

    @NotEmpty(message = "Born year cannot be empty!")
    private int bornYear;

    public Person(int id, String fullName, int bornYear) {
        this.id = id;
        this.fullName = fullName;
        this.bornYear = bornYear;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
