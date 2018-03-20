package com.kthdv.book_borrowing.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@ApiModel
public class BookDto {
    @ApiModelProperty(notes = "book's title, NOT NULL, NOT EMPTY")
    @NotEmpty
    private String title;
    @ApiModelProperty(notes = "book's author, NOT NULL, NOT EMPTY", position = 1)
    @NotEmpty
    private String author;
    @ApiModelProperty(notes = "book's description, NOT NULL, NOT EMPTY", position = 2)
    @NotEmpty
    private String description;
    @ApiModelProperty(notes = "book's quantity, MUST BE GREATER THAN 1", position = 3)
    @Min(1)
    private long quantity;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }
}
