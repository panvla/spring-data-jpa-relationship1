package com.vladimirpandurov.springdatarelationship.controller;

import com.vladimirpandurov.springdatarelationship.dto.requestDto.BookRequestDto;
import com.vladimirpandurov.springdatarelationship.dto.responseDto.BookResponseDto;
import com.vladimirpandurov.springdatarelationship.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    @PostMapping
    public ResponseEntity<BookResponseDto> addBook(@RequestBody final BookRequestDto bookRequestDto){
        BookResponseDto bookResponseDto = bookService.addBook(bookRequestDto);
        return new ResponseEntity<>(bookResponseDto, HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDto> getBook(@PathVariable("id") final Long id){
        BookResponseDto bookResponseDto = bookService.getBookById(id);
        return new ResponseEntity<>(bookResponseDto, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<BookResponseDto>> getBooks(){
        List<BookResponseDto> bookResponseDtos = bookService.getBooks();
        return new ResponseEntity<>(bookResponseDtos, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<BookResponseDto> deleteBook(@PathVariable("id") final Long id){
        BookResponseDto bookResponseDto = this.bookService.deleteBook(id);
        return new ResponseEntity<>(bookResponseDto, HttpStatus.OK);
    }
    @PutMapping("{id}")
    public ResponseEntity<BookResponseDto> editBook(@RequestBody final BookRequestDto bookRequestDto,
                                                    @PathVariable("id") final Long id){
        BookResponseDto bookResponseDto = this.bookService.editBook(id, bookRequestDto);
        return new ResponseEntity<>(bookResponseDto, HttpStatus.OK);
    }
    @PostMapping("/addCategory/{categoryId}/{bookId}")
    public ResponseEntity<BookResponseDto> addCategory(@PathVariable("categoryId") final Long categoryId,
                                                       @PathVariable("bookId") final Long bookId){
        BookResponseDto bookResponseDto = this.bookService.addCategoryToBook(bookId, categoryId);
        return new ResponseEntity<>(bookResponseDto, HttpStatus.OK);
    }
    @DeleteMapping("/removeCategory/{categoryId}/{bookId}")
    public ResponseEntity<BookResponseDto> removeCategory(@PathVariable("categoryId") final Long categoryId,
                                                          @PathVariable("bookId") final Long bookId){
        BookResponseDto bookResponseDto = bookService.removeCategoryFromBook(bookId, categoryId);
        return new ResponseEntity<>(bookResponseDto, HttpStatus.OK);
    }
    @PostMapping("/addAuthor/{authorId}/{bookId}")
    public ResponseEntity<BookResponseDto> addAuthor(@PathVariable("authorId") final Long authorId,
                                                     @PathVariable("bookId") final Long bookId){
        BookResponseDto bookResponseDto = bookService.addAuthorToBook(bookId, authorId);
        return new ResponseEntity<>(bookResponseDto, HttpStatus.OK);
    }
    @DeleteMapping("/removeAuthor/{authorId}/{bookId}")
    public ResponseEntity<BookResponseDto> removeAuthor(@PathVariable("authorId") final Long authorId,
                                                        @PathVariable("bookId") final Long bookId){
        BookResponseDto bookResponseDto = this.bookService.deleteAuthorFromBook(bookId, authorId);
        return new ResponseEntity<>(bookResponseDto, HttpStatus.OK);
    }





}
