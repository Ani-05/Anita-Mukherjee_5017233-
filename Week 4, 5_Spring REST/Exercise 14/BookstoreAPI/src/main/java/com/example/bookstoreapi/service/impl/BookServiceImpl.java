package com.example.bookstoreapi.service.impl;

import com.example.bookstoreapi.dto.BookDTO;
import com.example.bookstoreapi.mapper.BookMapper;
import com.example.bookstoreapi.model.Book;
import com.example.bookstoreapi.repository.BookRepository;
import com.example.bookstoreapi.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    private final BookMapper bookMapper = BookMapper.INSTANCE;

    @Override
    public Book createBook(BookDTO bookDTO) {
        Book book = bookMapper.toEntity(bookDTO);
        return bookRepository.save(book);
    }

    @Override
    public Book getBookById(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.orElse(null); // Return null or handle as needed
    }

    @Override
    public Book updateBook(Long id, BookDTO bookDTO) {
        if (!bookRepository.existsById(id)) {
            throw new RuntimeException("Book not found");
        }
        Book book = bookMapper.toEntity(bookDTO);
        book.setId(id); // Set the ID for update operation
        return bookRepository.save(book);
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
}