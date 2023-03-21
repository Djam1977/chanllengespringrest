package com.challengespringrest.springRest1.controller;

import org.springframework.web.bind.annotation.RestController;
import com.challengespringrest.springRest1.entity.Book;
import com.challengespringrest.springRest1.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController

public class BookController {
    @Autowired
    private BookRepository bookRepository;
    @GetMapping("/allBooks")
    public List<Book> index() {
        return bookRepository.findAll();
    }
    @GetMapping("/books/{id}")
    public Book show(@PathVariable long id){
        return bookRepository.findById(id).get();
    }
    @PostMapping("/books/search")
    public List<Book> search(@RequestBody Map<String, String> body){
        String searchTerm = body.get("text");
        return bookRepository.findByTitleContainingOrDescriptionContaining(searchTerm, searchTerm);
    }
    @PostMapping("/books")
    public Book create(@RequestBody Book book){
        return bookRepository.save(book);
    }
    @PutMapping("/books/{id}")
    public Book update(@PathVariable long id, @RequestBody Book book){
        // getting blog
        Book bookToUpdate = bookRepository.findById(id).get();
        bookToUpdate.setTitle(book.getTitle());
        bookToUpdate.setAuthor(book.getAuthor());
        bookToUpdate.setDescription(book.getDescription());
        return bookRepository.save(bookToUpdate);
    }
    @DeleteMapping("books/{id}")
    public boolean delete(@PathVariable long id){
        bookRepository.deleteById(id);
        return true;
    }
}