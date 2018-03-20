package com.kthdv.book_borrowing.model.view_model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class BookPreview {
    @ApiModelProperty("Book id")
    private String id;
    @ApiModelProperty(value = "Book title", position = 1)
    private String title;
    @ApiModelProperty(value = "Book author", position = 2)
    private String author;
    @ApiModelProperty(value = "Book quantity", position = 3)
    private long quantity;
    @ApiModelProperty(value = "Book borrowed quantity", position = 4)
    private long borrowedQuantity;

    public BookPreview(String id,
                       String title,
                       String author,
                       long quantity,
                       Long borrowedQuantity) {
        setId(id);
        setTitle(title);
        setAuthor(author);
        setQuantity(quantity);
        setBorrowedQuantity(borrowedQuantity == null? 0 : borrowedQuantity);
    }

    public BookPreview() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public long getBorrowedQuantity() {
        return borrowedQuantity;
    }

    public void setBorrowedQuantity(long borrowedQuantity) {
        this.borrowedQuantity = borrowedQuantity;
    }
}
