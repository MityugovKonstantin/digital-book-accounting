package ru.mityugov.digitalbookaccounting.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.mityugov.digitalbookaccounting.models.Book;
import ru.mityugov.digitalbookaccounting.models.Person;

import java.util.List;

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {

    List<Book> findByOwner(Person owner);

    List<Book> findByNameStartingWith(String startingWith);
}
