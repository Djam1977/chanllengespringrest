package com.challengespringrest.springRest1.controller;


import com.challengespringrest.springRest1.entity.Book;
import com.challengespringrest.springRest1.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class BookController {

    @Autowired
    BookRepository bookRepository;

    @GetMapping("/books")
    public List<Book> getAll(){
        return bookRepository.findAll();
    }

    @GetMapping("/blogs/{id}")
    public Book showBook(@PathVariable Long id){
        return bookRepository.findById(id).get();
    }

    @PostMapping("/books/search")
    public List<Book> searchBook(@RequestBody Map<String, String> body){
        String searchTerm = body.get("text");
        return bookRepository.findByTitleContainingOrContentContaining(searchTerm, searchTerm);
    }
    @PostMapping("/books")
    public Book createBook(@RequestBody Book blog){
        return bookRepository.save(blog);
    }


    @DeleteMapping("books/{id}")
    public boolean deleteBook(@PathVariable Long id){
        bookRepository.deleteById(id);
        return true;
    }
}

