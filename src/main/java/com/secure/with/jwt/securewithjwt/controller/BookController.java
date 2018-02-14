package com.secure.with.jwt.securewithjwt.controller;

import com.secure.with.jwt.securewithjwt.domain.Book;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/rest/book")
public class BookController {

    @GetMapping
    public List<Book> getBook() {
        Book book = new Book("Spring", "G.R." ,"1456600");
        Book book2 = new Book("Rest", "R.R." ,"14566cc00");

        return Arrays.asList(book,book2);
    }
}
