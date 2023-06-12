package com.example.spring_boot_rest_api.controller;

import com.example.spring_boot_rest_api.model.*;
import com.example.spring_boot_rest_api.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@Controller
@RequestMapping("/books")
public class BookController {

	private final BookRepository bookRepository;

	public BookController(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	@GetMapping()
	public String getBooks(Model model) {
		model.addAttribute("books", bookRepository.findAll());
		return "books";
	}

	@GetMapping("/{id}")
	public String getBookById(Model model, @PathVariable("id") Long id) {
		Book book = bookRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
		model.addAttribute("book", book);
		return "book";
	}

	@ExceptionHandler({EntityNotFoundException.class})
	public String bookNotFound() {
		return "bookNotFound";
	}

	@PostMapping()
	public String submit(@ModelAttribute("book") Book book) {
		book.setPublisher(new Publisher("Default Publisher"));
		Set<Author> authors = new HashSet<>();
		authors.add(new Author("default", "name", LocalDate.of(2000, 1, 1)));
		book.setAuthors(authors);
		bookRepository.save(book);
		return "bookAddedConfirmation";
	}

	@ModelAttribute
	public void addAttributes(Model model) {
		model.addAttribute("msg", "Welcome !");
	}

}

