package com.kthdv.book_borrowing.controller;

import com.kthdv.book_borrowing.dao.BookRepository;
import com.kthdv.book_borrowing.model.data.Book;
import com.kthdv.book_borrowing.model.dto.BookDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Api(value = "librarian-api", description = "APIs of Librarian")
@RequestMapping("api/librarian")
@CrossOrigin(origins = "*")
public class LibrarianController {
    @Autowired
    private BookRepository bookRepository;

    @ApiOperation(value = "Add book", response = Iterable.class)
    @ApiResponses({
            @ApiResponse(code = 201, message = "New book added")
    })
    @PostMapping("/books")
    public ResponseEntity addBook(@Valid @RequestBody BookDto bookDto) {
        try {
            Book book = new Book(bookDto);
            bookRepository.save(book);
            return new ResponseEntity(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Edit book information", response = Iterable.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Edit book information success"),
            @ApiResponse(code = 404, message = "Book with given id not found")
    })
    @PutMapping("/books/{id}")
    public ResponseEntity updateBook(@PathVariable("id") String bookID,
                                     @Valid @RequestBody BookDto bookDto) {
        try {
            Book bookFound = bookRepository.findFirstById(bookID);
            if(bookFound == null) {
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
            bookFound.update(bookDto);
            bookRepository.save(bookFound);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Delete book", response = Iterable.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Delete book success"),
            @ApiResponse(code = 404, message = "Book with given id not found")
    })
    @DeleteMapping("/books/{id}")
    public ResponseEntity deleteBook(@PathVariable("id") String bookID) {
        try {
            if(!bookRepository.existsById(bookID)) {
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
            bookRepository.deleteById(bookID);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
