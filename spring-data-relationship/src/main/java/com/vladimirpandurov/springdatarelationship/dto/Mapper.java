package com.vladimirpandurov.springdatarelationship.dto;

import com.vladimirpandurov.springdatarelationship.dto.responseDto.BookResponseDto;
import com.vladimirpandurov.springdatarelationship.model.Author;
import com.vladimirpandurov.springdatarelationship.model.Book;

import java.util.ArrayList;
import java.util.List;

public class Mapper {

    public static BookResponseDto bookToBookResponseDto(Book book){
        BookResponseDto bookResponseDto = new BookResponseDto();
        bookResponseDto.setId(book.getId());
        bookResponseDto.setCategoryName(book.getCategory().getName());
        List<String> names = new ArrayList<>();
        List<Author> authors = book.getAuthors();
        for(Author author : authors){
            names.add(author.getName());
        }
        bookResponseDto.setAuthorNames(names);
        return bookResponseDto;
    }




}
