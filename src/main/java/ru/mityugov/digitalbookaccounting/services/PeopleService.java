package ru.mityugov.digitalbookaccounting.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import ru.mityugov.digitalbookaccounting.models.Book;
import ru.mityugov.digitalbookaccounting.models.Person;
import ru.mityugov.digitalbookaccounting.repositories.PeopleRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class PeopleService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> findAll() {
        return peopleRepository.findAll();
    }

    public Person findOne(int id) {
        return peopleRepository.findById(id).orElse(null);
    }

    public Person findOne(String fullName) {
        return peopleRepository.findByFullName(fullName);
    }

    public void findOwner(Model model, Book book) {
        if (book.getOwner() != null) {
            model.addAttribute("person", findOne(book.getOwner().getPersonId()));
        } else {
            model.addAttribute("people", findAll());
            model.addAttribute("person", new Person());
        }
    }

    @Transactional
    public void save(Person person) {
        peopleRepository.save(person);
    }

    @Transactional
    public void update(int id, Person updatedPerson) {
        updatedPerson.setPersonId(id);
        peopleRepository.save(updatedPerson);
    }

    @Transactional
    public void delete(int id) {
        peopleRepository.deleteById(id);
    }
}
