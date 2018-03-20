package com.kthdv.book_borrowing.model.data;

import com.kthdv.book_borrowing.model.dto.BookDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@ApiModel
@Entity
@Table(name = "book")
public class Book {
    @ApiModelProperty(notes = "Book id")
    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @GeneratedValue(generator = "uuid")
    private String id;
    @ApiModelProperty(notes = "Book title", position = 1)
    private String title;
    @ApiModelProperty(notes = "Book author", position = 2)
    private String author;
    @ApiModelProperty(notes = "Book description", position = 3)
    private String description;
    @ApiModelProperty(notes = "Book quantity", position = 4)
    private long quantity;

    public Book(BookDto bookDto) {
        this.update(bookDto);
    }

    public Book() {
    }

    public void update(BookDto bookDto) {
        setTitle(bookDto.getTitle());
        setAuthor(bookDto.getAuthor());
        setDescription(bookDto.getDescription());
        setQuantity(bookDto.getQuantity());
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
