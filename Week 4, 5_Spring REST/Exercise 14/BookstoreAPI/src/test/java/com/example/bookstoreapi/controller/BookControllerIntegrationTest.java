package com.example.bookstoreapi.controller;

import com.example.bookstoreapi.dto.BookDTO;
import com.example.bookstoreapi.model.Book;
import com.example.bookstoreapi.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@SpringJUnitConfig
public class BookControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private BookService bookService;

    @BeforeEach
    public void setup() {
        // Optionally, you can set up your test data here
    }

    @Test
    public void testCreateBook() throws Exception {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setTitle("New Book");
        bookDTO.setAuthor("New Author");
        bookDTO.setPrice(29.99);

        mockMvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bookDTO)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title").value("New Book"))
                .andExpect(jsonPath("$.author").value("New Author"));
    }

    @Test
    public void testGetBookById() throws Exception {
        // Create a book to test
        BookDTO bookDTO = new BookDTO();
        bookDTO.setTitle("Test Book");
        bookDTO.setAuthor("Test Author");
        bookDTO.setPrice(19.99);
        Book createdBook = (Book) bookService.createBook(bookDTO);

        mockMvc.perform(get("/api/books/" + createdBook.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title").value("Test Book"))
                .andExpect(jsonPath("$.author").value("Test Author"));
    }

    @Test
    public void testUpdateBook() throws Exception {
        // Create a book to test
        BookDTO bookDTO = new BookDTO();
        bookDTO.setTitle("Initial Title");
        bookDTO.setAuthor("Initial Author");
        bookDTO.setPrice(19.99);
        Book createdBook = (Book) bookService.createBook(bookDTO);

        BookDTO updatedBookDTO = new BookDTO();
        updatedBookDTO.setTitle("Updated Title");
        updatedBookDTO.setAuthor("Updated Author");
        updatedBookDTO.setPrice(29.99);

        mockMvc.perform(put("/api/books/" + createdBook.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedBookDTO)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title").value("Updated Title"))
                .andExpect(jsonPath("$.author").value("Updated Author"));
    }

    @Test
    public void testDeleteBook() throws Exception {
        // Create a book to test
        BookDTO bookDTO = new BookDTO();
        bookDTO.setTitle("Book to Delete");
        bookDTO.setAuthor("Author");
        bookDTO.setPrice(19.99);
        Book createdBook = (Book) bookService.createBook(bookDTO);

        mockMvc.perform(delete("/api/books/" + createdBook.getId()))
                .andExpect(status().isNoContent());
    }
}