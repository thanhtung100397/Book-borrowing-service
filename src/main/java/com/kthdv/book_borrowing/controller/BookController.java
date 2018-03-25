package com.kthdv.book_borrowing.controller;

import com.kthdv.book_borrowing.dao.BookRepository;
import com.kthdv.book_borrowing.model.view_model.BookDetail;
import com.kthdv.book_borrowing.model.view_model.BookPreview;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(value = "book-api", description = "APIs for fetching book data")
@RequestMapping("api")
@CrossOrigin(origins = "*")
public class BookController {
    @Autowired
    private BookRepository bookRepository;

    @ApiOperation(value = "Get list books (preview)", response = Iterable.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Get list books success", responseContainer = "List", response = BookPreview.class)
    })
    @GetMapping("/books")
    public ResponseEntity<List<BookPreview>> getAllBookPreviews() {
        try {
            return new ResponseEntity<>(bookRepository.getAllBookPreviews(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Get book's detail", response = Iterable.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Get book's detail success", response = BookDetail.class),
            @ApiResponse(code = 404, message = "Book with given id not found")
    })
    @GetMapping("/books/{id}")
    public ResponseEntity<BookDetail> getBookDetail(@PathVariable("id") String bookID) {
        try {
            BookDetail bookDetailFound = bookRepository.getBookDetail(bookID);
            if (bookDetailFound == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(bookDetailFound, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
