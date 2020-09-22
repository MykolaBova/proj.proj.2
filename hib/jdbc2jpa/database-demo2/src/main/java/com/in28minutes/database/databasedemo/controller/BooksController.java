package com.in28minutes.database.databasedemo.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BooksController {
	@GetMapping("/books")
	public List<com.in28minutes.springboot.basics.springbootin10steps.Book> getAllBooks() {
		return Arrays.asList(
				new com.in28minutes.springboot.basics.springbootin10steps.Book(1l, "Mastering Spring 5.2", "Ranga Karanam"));
	}
}