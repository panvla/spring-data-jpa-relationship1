package com.vladimirpandurov.springdatarelationship.service;

import com.vladimirpandurov.springdatarelationship.dto.Mapper;
import com.vladimirpandurov.springdatarelationship.dto.requestDto.BookRequestDto;
import com.vladimirpandurov.springdatarelationship.dto.responseDto.BookResponseDto;
import com.vladimirpandurov.springdatarelationship.model.Author;
import com.vladimirpandurov.springdatarelationship.model.Book;
import com.vladimirpandurov.springdatarelationship.model.Category;
import com.vladimirpandurov.springdatarelationship.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final CategoryService categoryService;

    @Transactional
    @Override
    public BookResponseDto addBook(BookRequestDto bookRequestDto) {
        Book book = new Book();
        book.setName(bookRequestDto.getName());
        if(bookRequestDto.getAuthorsIds().isEmpty()){
            throw new IllegalArgumentException("you need atleast one author");
        }else{
            List<Author> authors = new ArrayList<>();
            for(Long authorId: bookRequestDto.getAuthorsIds()){
                Author author = authorService.getAuthor(authorId);
                authors.add(author);
            }
            book.setAuthors(authors);
        }
        if(bookRequestDto.getCategoryId() == null){
            throw new IllegalArgumentException("book need atleast one category");
        }
        Category category = categoryService.getCategory(bookRequestDto.getCategoryId());
        book.setCategory(category);

        Book savedBook = this.bookRepository.save(book);
        return Mapper.bookToBookResponseDto(savedBook);
    }

    @Override
    public BookResponseDto getBookById(Long bookId) {
        Book book = getBook(bookId);
        return Mapper.bookToBookResponseDto(book);
    }

    @Override
    public Book getBook(Long bookId) {
        Book book = this.bookRepository.findById(bookId).orElseThrow(()-> new IllegalArgumentException("cannot find book with id: " + bookId));
        return book;
    }

    @Override
    public List<BookResponseDto> getBooks() {
        List<Book> books = StreamSupport.stream(this.bookRepository.findAll().spliterator(),false)
                .collect(Collectors.toList());
        return Mapper.booksToBookResponseDtos(books);
    }

    @Override
    public BookResponseDto deleteBook(Long bookId) {
        Book book = getBook(bookId);
        this.bookRepository.delete(book);
        return Mapper.bookToBookResponseDto(book);
    }
    @Transactional
    @Override
    public BookResponseDto editBook(Long bookId, BookRequestDto bookRequestDto) {
        Book bookToEdit = getBook(bookId);
        bookToEdit.setName(bookRequestDto.getName());
        if(!bookRequestDto.getAuthorsIds().isEmpty()){
            List<Author> authors = new ArrayList<>();
            for(Long authorId: bookRequestDto.getAuthorsIds()){
                Author author = this.authorService.getAuthor(authorId);
                authors.add(author);
            }
            bookToEdit.setAuthors(authors);
        }
        if(bookRequestDto.getCategoryId() != null){
            Category category = this.categoryService.getCategory(bookRequestDto.getCategoryId());
            bookToEdit.setCategory(category);
        }
        return Mapper.bookToBookResponseDto(bookToEdit);
    }

    @Override
    public BookResponseDto addAuthorToBook(Long bookId, Long authorId) {
        Book book = getBook(bookId);
        Author author = this.authorService.getAuthor(authorId);
        if(author.getBooks().contains(author)){
            throw new IllegalArgumentException("this author is already assigned to this book");
        }
        book.addAuthor(author);
        author.addBook(book);
        return Mapper.bookToBookResponseDto(book);
    }

    @Override
    public BookResponseDto deleteAuthorFromBook(Long bookId, Long authorId) {
        Book book = getBook(bookId);
        Author author = this.authorService.getAuthor(authorId);
        if(!(author.getBooks().contains(book))){
            throw new IllegalArgumentException("book does not have this author!");
        }
        author.removeBook(book);
        book.deleteAuthor(author);
        return Mapper.bookToBookResponseDto(book);
    }

    @Override
    public BookResponseDto addCategoryToBook(Long bookId, Long categoryId) {
        Book book = getBook(bookId);
        Category category = this.categoryService.getCategory(categoryId);
        if(Objects.nonNull(book.getCategory())){
            throw new IllegalArgumentException("book already has a category");
        }
        book.setCategory(category);
        category.addBook(book);
        return Mapper.bookToBookResponseDto(book);
    }

    @Override
    public BookResponseDto removeCategoryFromBook(Long bookId, Long categoryId) {
        Book book = getBook(bookId);
        Category category = categoryService.getCategory(categoryId);
        if(!(Objects.nonNull(book.getCategory()))){
            throw new IllegalArgumentException("book does not have a category to deletee");
        }
        book.setCategory(null);
        category.removeBook(book);
        return Mapper.bookToBookResponseDto(book);
    }
}
