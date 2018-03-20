package com.kthdv.book_borrowing.model.view_model;

import com.kthdv.book_borrowing.model.data.Book;
import com.kthdv.book_borrowing.model.data.BorrowedBook;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel
public class BorrowedBookViewModel {
    @ApiModelProperty(notes = "Borrowed books id")
    private String id;
    @ApiModelProperty(notes = "Book id", position = 1)
    private String bookID;
    @ApiModelProperty(notes = "Book title", position = 2)
    private String title;
    @ApiModelProperty(notes = "Book author", position = 3)
    private String author;
    @ApiModelProperty(notes = "Borrowed quantity", position = 4)
    private long quantity;
    @ApiModelProperty(notes = "Borrowed date in long", position = 5)
    private long createdDate;
    @ApiModelProperty(notes = "Return date in long, -1 mean books have't returned yet", position = 6)
    private long returnedDate;

    public BorrowedBookViewModel() {
    }

    public BorrowedBookViewModel(String id,
                                 String bookID,
                                 String title,
                                 String author,
                                 long quantity,
                                 Date createdDate,
                                 Date returnedDate) {
        setId(id);
        setBookID(bookID);
        setTitle(title);
        setAuthor(author);
        setQuantity(quantity);
        setCreatedDate(createdDate.getTime());
        if (returnedDate != null) {
            setReturnedDate(returnedDate.getTime());
        } else {
            setReturnedDate(-1);
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(long createdDate) {
        this.createdDate = createdDate;
    }

    public long getReturnedDate() {
        return returnedDate;
    }

    public void setReturnedDate(long returnedDate) {
        this.returnedDate = returnedDate;
    }
}
