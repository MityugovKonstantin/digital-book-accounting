package ru.mityugov.digitalbookaccounting.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.mityugov.digitalbookaccounting.models.Person;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {

    Person findByFullName(String fullName);
}
