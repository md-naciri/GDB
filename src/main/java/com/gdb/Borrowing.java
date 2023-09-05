package com.gdb;

import java.util.Date;

public class Borrowing {
    private Copies bookId;
    private Borrower memberId;
    private Date borrowingDate;
    private Date returnDate;

    public Borrowing(Copies bookId, Borrower memberId, Date borrowingDate, Date returnDate) {
        this.bookId = bookId;
        this.memberId = memberId;
        this.borrowingDate = borrowingDate;
        this.returnDate = returnDate;
    }
}
