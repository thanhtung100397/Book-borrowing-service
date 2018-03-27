package com.kthdv.book_borrowing.controller;

import com.kthdv.book_borrowing.dao.BorrowedBookRepository;
import com.kthdv.book_borrowing.dao.BookRepository;
import com.kthdv.book_borrowing.model.data.Book;
import com.kthdv.book_borrowing.model.data.BorrowedBook;
import com.kthdv.book_borrowing.model.view_model.BorrowedBookViewModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@Api(value = "borrower-api", description = "APIs of Book borrower")
@RequestMapping("api/borrower")
@CrossOrigin(origins = "*")
public class BorrowerController {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BorrowedBookRepository borrowedBookRepository;

    @ApiOperation(value = "Get list borrowed books", response = Iterable.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Get list borrowed books success", responseContainer = "List", response = BorrowedBookViewModel.class)
    })
    @GetMapping("/borrowedBooks")
    public ResponseEntity<List<BorrowedBookViewModel>> getListBorrowedBooks() {
        try {
            return new ResponseEntity<>(borrowedBookRepository.getBorrowedBookPreviews(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Borrow books", response = Iterable.class)
    @ApiResponses({
            @ApiResponse(code = 201, message = "Borrow books success"),
            @ApiResponse(code = 400, message = "Borrow quantity must be greater than 0"),
            @ApiResponse(code = 403, message = "Borrow quantity is greater than available book quantity, cannot borrow"),
            @ApiResponse(code = 404, message = "Book with given id not found")
    })
    @PostMapping("/books/{id}/borrow/{quantity}")
    public ResponseEntity borrowBook(@PathVariable("id") String bookID,
                                     @PathVariable("quantity") int quantity) {
        try {
            if(quantity <= 0) {
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            }
            Book bookFound = bookRepository.findFirstById(bookID);
            if (bookFound == null) {
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
            long totalQuantity = bookFound.getQuantity();
            Long borrowedQuantity = borrowedBookRepository.getTotalBookBorrowedQuantity(bookID);
            if(borrowedQuantity == null) {
                borrowedQuantity = 0L;
            }
            long bookLeft = totalQuantity - borrowedQuantity;

            if (bookLeft - quantity <= 0) {
                return new ResponseEntity(HttpStatus.FORBIDDEN);
            }

            BorrowedBook borrowedBook = new BorrowedBook(bookFound, quantity);
            borrowedBookRepository.save(borrowedBook);

            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Return borrowed books", response = Iterable.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Return borrowed books success"),
            @ApiResponse(code = 404, message = "Borrowed book with given id not found")
    })
    @PostMapping("/borrowedBooks/{id}/return")
    public ResponseEntity returnBorrowedBook(@PathVariable("id") String borrowedBookID) {
        try {
            BorrowedBook borrowedBook = borrowedBookRepository.findFirstById(borrowedBookID);
            if (borrowedBook == null) {
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
            borrowedBook.setReturnedDate(new Date());
            borrowedBookRepository.save(borrowedBook);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
