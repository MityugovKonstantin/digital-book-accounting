package ru.mityugov.digitalbookaccounting.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Person {

    private int personId;

    @NotEmpty(message = "Full name cannot be empty!")
    @Size(min = 1, max = 100, message = "Full name cannot be less than 1 and more than 100 characters!")
    private String fullName;

    @Min(value = 1800, message = "Born year cannot be less then 1800!")
    private int bornYear;

    public Person(int personId, String fullName, int bornYear) {
        this.personId = personId;
        this.fullName = fullName;
        this.bornYear = bornYear;
    }

    public Person() {}

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
}
