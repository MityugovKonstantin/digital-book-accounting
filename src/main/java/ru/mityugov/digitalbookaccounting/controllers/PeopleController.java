package ru.mityugov.digitalbookaccounting.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.mityugov.digitalbookaccounting.dao.PersonDao;
import ru.mityugov.digitalbookaccounting.models.Person;
import ru.mityugov.digitalbookaccounting.utils.PersonValidator;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonDao personDao;
    private final PersonValidator personValidator;

    @Autowired
    public PeopleController(PersonDao personDao, PersonValidator personValidator) {
        this.personDao = personDao;
        this.personValidator = personValidator;
    }

    @GetMapping()
    public String showPeople(Model model) {
        model.addAttribute("people", personDao.getPeople());
        return "people/people";
    }

    @GetMapping("/{id}")
    public String showPerson(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDao.getPersonById(id));
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

        personDao.add(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String showPersonUpdating(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDao.getPersonById(id));
        return "people/updating";
    }

    @PatchMapping("/{id}/edit")
    public String updatePerson(
            @ModelAttribute("person") @Valid Person person,
            BindingResult bindingResult,
            @PathVariable("id") int id) {
        personValidator.validate(person, bindingResult);

        if (bindingResult.hasErrors())
            return "people/updating";

        personDao.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable("id") int id) {
        personDao.delete(id);
        return "redirect:/people";
    }

}
