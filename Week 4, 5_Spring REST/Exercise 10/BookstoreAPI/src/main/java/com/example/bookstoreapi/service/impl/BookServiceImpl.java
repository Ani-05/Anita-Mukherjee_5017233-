package com.example.bookstoreapi.service.impl;

import com.example.bookstoreapi.dto.BookDTO;
import com.example.bookstoreapi.exception.BookNotFoundException;
import com.example.bookstoreapi.model.Book;
import com.example.bookstoreapi.repository.BookRepository;
import com.example.bookstoreapi.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Book createBook(BookDTO bookDTO) {
        Book book = modelMapper.map(bookDTO, Book.class);
        return bookRepository.save(book);
    }

    @Override
    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found with id: " + id));
    }

    @Override
    public Book updateBook(Long id, BookDTO bookDTO) {
        Book existingBook = getBookById(id);
        modelMapper.map(bookDTO, existingBook);
        return bookRepository.save(existingBook);
    }

    @Override
    public void deleteBook(Long id) {
        Book existingBook = getBookById(id);
        bookRepository.delete(existingBook);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
}