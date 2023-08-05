package ru.mityugov.digitalbookaccounting.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import ru.mityugov.digitalbookaccounting.dao.BookDao;
import ru.mityugov.digitalbookaccounting.dao.PersonDao;
import ru.mityugov.digitalbookaccounting.models.Book;
import ru.mityugov.digitalbookaccounting.models.Person;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookDao bookDao;
    private final PersonDao personDao;

    @Autowired
    public BookController(BookDao bookDao, PersonDao personDao) {
        this.bookDao = bookDao;
        this.personDao = personDao;
    }

    @GetMapping()
    public String showBooks(Model model) {
        model.addAttribute("books", bookDao.getBooks());
        return "book/books";
    }

    @GetMapping("/{id}")
    public String showBook(@PathVariable("id") int id, Model model) {
        Book book = bookDao.getBook(id);

        model.addAttribute("book", book);
        model.addAttribute("people", personDao.getPeople());

        if (book.getPersonId() != null) {
            model.addAttribute("person", personDao.getPersonById(book.getPersonId()));
        } else {
            model.addAttribute("person", new Person());
        }

        return "book/book";
    }

    @PatchMapping("/{id}/assign")
    public String assign(@PathVariable("id") int id, @ModelAttribute("person") Person person) {
        bookDao.assign(id, person.getPersonId());
        return ("redirect:/books/" + id);
    }

    @PatchMapping("/{id}/detach")
    public String detach(@PathVariable("id") int id) {
        bookDao.detach(id);
        return ("redirect:/books/" + id);
    }

    @GetMapping("/add")
    public String showBookAdditional(@ModelAttribute("book") Book book) {
        return "book/additional";
    }

    @PostMapping("/add")
    public String addBook(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "book/additional";
        bookDao.add(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String showBookUpdating(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookDao.getBook(id));
        return "book/updating";
    }

    @PatchMapping("/{id}/edit")
    public String updateBook(
            @ModelAttribute("book") @Valid Book book,
            BindingResult bindingResult,
            @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "book/updating";
        bookDao.update(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") int id) {
        bookDao.delete(id);
        return "redirect:/books";
    }
}
