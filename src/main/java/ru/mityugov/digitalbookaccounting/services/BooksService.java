package ru.mityugov.digitalbookaccounting.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.mityugov.digitalbookaccounting.models.Book;
import ru.mityugov.digitalbookaccounting.models.Person;
import ru.mityugov.digitalbookaccounting.repositories.BooksRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class BooksService {

    private final BooksRepository booksRepository;

    @Autowired
    public BooksService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public Page<Book> findAll(Pageable pageable) {
        return booksRepository.findAll(pageable);
    }

    public List<Book> findAllByOwner(Person owner) {
        return booksRepository.findByOwner(owner);
    }

    public Book findOne(int id) {
        return booksRepository.findById(id).orElse(null);
    }

    public List<Book> findByNameStartingWith(String startingWith) {
        if (startingWith == null || startingWith.isEmpty()) return null;
        return booksRepository.findByNameStartingWith(startingWith);
    }

    @Transactional
    public void save(Book book) {
        booksRepository.save(book);
    }

    @Transactional
    public void update(int id, Book updatedBook) {
        updatedBook.setBookId(id);
        booksRepository.save(updatedBook);
    }

    @Transactional
    public void delete(int id) {
        booksRepository.deleteById(id);
    }

    @Transactional
    public void assign(int id, Person person) {
        Book book = booksRepository.findById(id).orElse(null);
        if (book != null) {
            book.setOwner(person);
            booksRepository.save(book);
        }
    }

    @Transactional
    public void detach(int id) {
        Book book = booksRepository.findById(id).orElse(null);
        if (book != null) {
            book.setOwner(null);
            booksRepository.save(book);
        }
    }
}
