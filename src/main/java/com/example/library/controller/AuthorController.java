package com.example.library.controller;

import com.example.library.model.Author;
import com.example.library.model.Book;
import com.example.library.repository.AuthorRepository;
import com.example.library.repository.BookRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/authors")
public class AuthorController {
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private BookRepository bookRepository;

    @GetMapping
    public String listAuthors(Model model) {
        model.addAttribute("authors", authorRepository.findAll());
        return "authors";
    }

    @GetMapping("/add")
    public String showAddAuthorForm(Model model) {
        model.addAttribute("author", new Author());
        return "add_author";
    }

    @PostMapping("/add")
    public String addAuthor(@Valid @ModelAttribute Author author, BindingResult result) {
        if (result.hasErrors()) {
            return "add_author";
        }
        authorRepository.save(author);
        return "redirect:/authors";
    }

    @GetMapping("/{id}")
    public String authorDetails(@PathVariable Long id, Model model) {
        Optional<Author> authorOpt = authorRepository.findById(id);
        if (authorOpt.isPresent()) {
            Author author = authorOpt.get();
            List<Book> books = bookRepository.findByAuthorId(id);
            model.addAttribute("author", author);
            model.addAttribute("books", books);
            return "author_details";
        }
        return "redirect:/authors";
    }

    @GetMapping("/edit/{id}")
    public String showEditAuthorForm(@PathVariable Long id, Model model) {
        Optional<Author> authorOpt = authorRepository.findById(id);
        if (authorOpt.isPresent()) {
            model.addAttribute("author", authorOpt.get());
            return "add_author";
        }
        return "redirect:/authors";
    }

    @PostMapping("/edit/{id}")
    public String editAuthor(@PathVariable Long id, @Valid @ModelAttribute Author author, BindingResult result) {
        if (result.hasErrors()) {
            return "add_author";
        }
        author.setId(id);
        authorRepository.save(author);
        return "redirect:/authors";
    }

    @GetMapping("/delete/{id}")
    public String deleteAuthor(@PathVariable Long id) {
        authorRepository.deleteById(id);
        return "redirect:/authors";
    }
} 