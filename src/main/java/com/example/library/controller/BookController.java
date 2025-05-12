package com.example.library.controller;

import com.example.library.model.Book;
import com.example.library.model.Author;
import com.example.library.repository.BookRepository;
import com.example.library.repository.AuthorRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping
    public String listBooks(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "books";
    }

    @GetMapping("/add")
    public String showAddBookForm(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("authors", authorRepository.findAll());
        return "add_book";
    }

    @PostMapping("/add")
    public String addBook(@Valid @ModelAttribute Book book, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("authors", authorRepository.findAll());
            return "add_book";
        }
        Author author = authorRepository.findById(book.getAuthor().getId()).orElse(null);
        book.setAuthor(author);
        bookRepository.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}")
    public String bookDetails(@PathVariable Long id, Model model) {
        Optional<Book> bookOpt = bookRepository.findById(id);
        if (bookOpt.isPresent()) {
            Book book = bookOpt.get();
            model.addAttribute("book", book);
            return "book_details";
        }
        return "redirect:/books";
    }

    @GetMapping("/edit/{id}")
    public String showEditBookForm(@PathVariable Long id, Model model) {
        Optional<Book> bookOpt = bookRepository.findById(id);
        if (bookOpt.isPresent()) {
            model.addAttribute("book", bookOpt.get());
            model.addAttribute("authors", authorRepository.findAll());
            return "add_book";
        }
        return "redirect:/books";
    }

    @PostMapping("/edit/{id}")
    public String editBook(@PathVariable Long id, @Valid @ModelAttribute Book book, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("authors", authorRepository.findAll());
            return "add_book";
        }
        Author author = authorRepository.findById(book.getAuthor().getId()).orElse(null);
        book.setAuthor(author);
        book.setId(id);
        bookRepository.save(book);
        return "redirect:/books";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookRepository.deleteById(id);
        return "redirect:/books";
    }
} 