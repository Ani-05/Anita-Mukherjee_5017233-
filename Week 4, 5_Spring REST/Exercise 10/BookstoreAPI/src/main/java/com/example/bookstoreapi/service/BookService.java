package com.example.bookstoreapi.service;

import com.example.bookstoreapi.dto.BookDTO;
import com.example.bookstoreapi.model.Book;

import java.util.List;

public interface BookService {
    Book createBook(BookDTO bookDTO);
    Book getBookById(Long id);
    Book updateBook(Long id, BookDTO bookDTO);
    void deleteBook(Long id);
    List<Book> getAllBooks();
}