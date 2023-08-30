package ru.mityugov.digitalbookaccounting.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import ru.mityugov.digitalbookaccounting.models.Person;
import ru.mityugov.digitalbookaccounting.services.BooksService;
import ru.mityugov.digitalbookaccounting.services.PeopleService;
import ru.mityugov.digitalbookaccounting.utils.PersonValidator;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PeopleService peopleService;

    private final BooksService booksService;

    private final PersonValidator personValidator;

    @Autowired
    public PeopleController(PeopleService peopleService, BooksService booksService, PersonValidator personValidator) {
        this.peopleService = peopleService;
        this.booksService = booksService;
        this.personValidator = personValidator;
    }

    @GetMapping()
    public String showPeople(Model model) {
        model.addAttribute("people", peopleService.findAll());
        return "people/people";
    }

    @GetMapping("/{id}")
    public String showPerson(@PathVariable("id") int id, Model model) {
        Person owner = peopleService.findOne(id);
        model.addAttribute("books", booksService.findAllByOwner(owner));
        model.addAttribute("person", owner);
        return "people/person";
    }

    @GetMapping("/add")
    public String showPersonAdditional(@ModelAttribute("person") Person person) {
        return "people/additional";
    }

    @PostMapping("/add")
    public String addPerson(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors())
            return "people/additional";
        peopleService.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String showPersonUpdating(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", peopleService.findOne(id));
        return "people/updating";
    }

    @PatchMapping("/{id}/edit")
    public String updatePerson(
            @ModelAttribute("person") @Valid Person person,
            BindingResult bindingResult,
            @PathVariable("id") int id
    ) {
        personValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors())
            return "people/updating";
        peopleService.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable("id") int id) {
        peopleService.delete(id);
        return "redirect:/people";
    }
}
