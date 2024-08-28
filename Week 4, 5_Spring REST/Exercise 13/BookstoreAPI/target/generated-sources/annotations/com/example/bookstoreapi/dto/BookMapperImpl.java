package com.example.bookstoreapi.dto;

import com.example.bookstoreapi.model.Book;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-27T18:15:50+0530",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
public class BookMapperImpl implements BookMapper {

    @Override
    public BookDTO bookToBookDTO(Book book) {
        if ( book == null ) {
            return null;
        }

        BookDTO bookDTO = new BookDTO();

        bookDTO.setId( book.getId() );
        bookDTO.setTitle( book.getTitle() );
        bookDTO.setAuthor( book.getAuthor() );
        if ( book.getPrice() != null ) {
            bookDTO.setPrice( book.getPrice() );
        }

        return bookDTO;
    }

    @Override
    public Book bookDTOToBook(BookDTO bookDTO) {
        if ( bookDTO == null ) {
            return null;
        }

        String author = null;

        author = bookDTO.getAuthor();

        long l = 0L;
        String bookTitle = null;
        double v = 0.0d;

        Book book = new Book( l, bookTitle, author, v );

        book.setId( bookDTO.getId() );
        book.setTitle( bookDTO.getTitle() );
        book.setPrice( bookDTO.getPrice() );

        return book;
    }
}
