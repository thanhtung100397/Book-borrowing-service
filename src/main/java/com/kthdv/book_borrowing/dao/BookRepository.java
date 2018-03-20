package com.kthdv.book_borrowing.dao;

import com.kthdv.book_borrowing.model.data.Book;
import com.kthdv.book_borrowing.model.view_model.BookDetail;
import com.kthdv.book_borrowing.model.view_model.BookPreview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, String> {
    Book findFirstById(String id);

    @Query("select new com.kthdv.book_borrowing.model.view_model.BookPreview(b.id, " +
            "b.title, " +
            "b.author, " +
            "b.quantity, " +
            "(select sum(bb.quantity) from BorrowedBook bb where bb.book.id = b.id and bb.returnedDate is null)) " +
            "from Book b " +
            "order by b.title asc")
    List<BookPreview> getAllBookPreviews();

    @Query("select new com.kthdv.book_borrowing.model.view_model.BookDetail(b.id, " +
            "b.title, " +
            "b.author, " +
            "b.description, " +
            "b.quantity, " +
            "(select sum(bb.quantity) from BorrowedBook bb where bb.book.id = ?1 and bb.returnedDate is null))" +
            "from Book b " +
            "where b.id = ?1 " +
            "order by b.title asc")
    BookDetail getBookDetail(String bookID);

    @Query("select b.quantity from Book b where b.id = ?1")
    long getBookQuantity(String bookID);
}
