package com.example.bookstoreapi.controller;

import com.example.bookstoreapi.dto.BookDTO;
import com.example.bookstoreapi.model.Book;
import com.example.bookstoreapi.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    public ResponseEntity<EntityModel<Book>> createBook(@RequestBody BookDTO bookDTO) {
        Book book = bookService.createBook(bookDTO);
        EntityModel<Book> resource = EntityModel.of(book);

        Link selfLink = WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(BookController.class).getBookById(book.getId())
        ).withSelfRel();

        resource.add(selfLink);
        return new ResponseEntity<>(resource, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Book>> getBookById(@PathVariable Long id) {
        Book book = bookService.getBookById(id);
        EntityModel<Book> resource = EntityModel.of(book);

        Link selfLink = WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(BookController.class).getBookById(id)
        ).withSelfRel();

        resource.add(selfLink);
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Book>> updateBook(
            @PathVariable Long id,
            @RequestBody BookDTO bookDTO
    ) {
        Book book = bookService.updateBook(id, bookDTO);
        EntityModel<Book> resource = EntityModel.of(book);

        Link selfLink = WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(BookController.class).getBookById(id)
        ).withSelfRel();

        resource.add(selfLink);
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<EntityModel<Book>>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        List<EntityModel<Book>> resources = books.stream()
                .map(book -> {
                    EntityModel<Book> entityModel = EntityModel.of(book);
                    Link selfLink = WebMvcLinkBuilder.linkTo(
                            WebMvcLinkBuilder.methodOn(BookController.class).getBookById(book.getId())
                    ).withSelfRel();
                    entityModel.add(selfLink);
                    return entityModel;
                })
                .collect(Collectors.toList());

        return new ResponseEntity<>(resources, HttpStatus.OK);
    }
}