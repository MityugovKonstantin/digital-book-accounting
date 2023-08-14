package ru.mityugov.digitalbookaccounting.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import ru.mityugov.digitalbookaccounting.models.Book;
import ru.mityugov.digitalbookaccounting.models.Person;
import ru.mityugov.digitalbookaccounting.services.BooksService;
import ru.mityugov.digitalbookaccounting.services.PeopleService;

@Controller
@RequestMapping("/books")
public class BookController {

    private final PeopleService peopleService;

    private final BooksService booksService;

    @Autowired
    public BookController(PeopleService peopleService, BooksService booksService) {
        this.peopleService = peopleService;
        this.booksService = booksService;
    }

    @GetMapping()
    public String showBooks(@PageableDefault(size = 5) Pageable pageable, Model model) {
        var page = booksService.findAll(pageable);
        model.addAttribute("sort", getSort(pageable));
        model.addAttribute("page", page);
        return "book/books";
    }

    @GetMapping("/{id}")
    public String showBook(@PathVariable("id") int id, Model model) {
        Book book = booksService.findOne(id);

        model.addAttribute("book", book);
        model.addAttribute("people", peopleService.findAll());

        if (book.getOwner() != null) {
            model.addAttribute("person", peopleService.findOne(book.getOwner().getPersonId()));
        } else {
            model.addAttribute("person", new Person());
        }

        return "book/book";
    }

    @GetMapping("/add")
    public String showBookAdditional(@ModelAttribute("book") Book book) {
        return "book/additional";
    }

    @PostMapping("/add")
    public String addBook(
            @ModelAttribute("person") Person person,
            @ModelAttribute("book") @Valid Book book,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors())
            return "book/additional";
        book.setOwner(peopleService.findOne(person.getPersonId()));
        booksService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String showBookUpdating(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", booksService.findOne(id));
        return "book/updating";
    }

    @PatchMapping("/{id}/edit")
    public String updateBook(
            @ModelAttribute("book") @Valid Book book,
            BindingResult bindingResult,
            @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "book/updating";
        booksService.update(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") int id) {
        booksService.delete(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/assign")
    public String assign(@PathVariable("id") int id, @ModelAttribute("person") Person person) {
        booksService.assign(id, person);
        return ("redirect:/books/" + id);
    }

    @PatchMapping("/{id}/detach")
    public String detach(@PathVariable("id") int id) {
        booksService.detach(id);
        return ("redirect:/books/" + id);
    }

    // TODO
    private String getSort(Pageable pageable) {
        String sort = pageable.getSort().toString().split(":")[0];
        return sort.equals("UNSORTED") ? "" : sort;
    }
}
