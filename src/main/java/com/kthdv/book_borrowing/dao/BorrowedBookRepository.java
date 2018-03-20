package com.kthdv.book_borrowing.dao;

import com.kthdv.book_borrowing.model.data.BorrowedBook;
import com.kthdv.book_borrowing.model.view_model.BorrowedBookViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BorrowedBookRepository extends JpaRepository<BorrowedBook, String> {
    @Query("select sum(bb.quantity) from BorrowedBook bb where bb.book.id = ?1 and bb.returnedDate is null")
    Long getTotalBookBorrowedQuantity(String bookID);

    BorrowedBook findFirstById(String id);

    @Query("select new com.kthdv.book_borrowing.model.view_model.BorrowedBookViewModel(bb.id, " +
            "bb.book.id, " +
            "bb.book.title, " +
            "bb.book.author, " +
            "bb.quantity, " +
            "bb.createdDate, " +
            "bb.returnedDate)" +
            "from BorrowedBook bb " +
            "order by bb.createdDate desc")
    List<BorrowedBookViewModel> getBorrowedBookPreviews();
}
