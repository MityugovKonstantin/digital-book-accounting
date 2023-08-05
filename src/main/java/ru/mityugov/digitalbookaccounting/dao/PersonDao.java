package ru.mityugov.digitalbookaccounting.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.mityugov.digitalbookaccounting.models.Person;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> getPeople() {
        return jdbcTemplate.query("SELECT * FROM PERSON", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person getPersonById(int id) {
        return jdbcTemplate.query(
                "SELECT * FROM PERSON WHERE PERSON_ID = ?",
                new Object[]{id},
                new BeanPropertyRowMapper<>(Person.class))
                .stream()
                .findAny()
                .orElse(null);
    }

    public Optional<Person> getPersonByFullName(String fullName) {
        return jdbcTemplate.query(
                        "SELECT * FROM PERSON WHERE FULL_NAME = ?",
                        new Object[]{fullName},
                        new BeanPropertyRowMapper<>(Person.class))
                .stream()
                .findAny();
    }

    public void add(Person person) {
        jdbcTemplate.update(
                "INSERT INTO PERSON (FULL_NAME, BORN_YEAR) VALUES (?, ?)",
                person.getFullName(),
                person.getBornYear()
        );
    }

    public void update(int id, Person person) {
        jdbcTemplate.update(
                "UPDATE PERSON SET FULL_NAME = ?, BORN_YEAR = ? WHERE PERSON_ID = ?",
                person.getFullName(),
                person.getBornYear(),
                id
        );
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM PERSON WHERE PERSON_ID = ?", id);
    }
}
