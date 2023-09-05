package com.gdb;

import java.util.Date;

public class Borrowing {
    private Copy bookId;
    private Borrower memberId;
    private Date borrowingDate;
    private Date returnDate;

    public Borrowing(Copy bookId, Borrower memberId, Date borrowingDate, Date returnDate) {
        this.bookId = bookId;
        this.memberId = memberId;
        this.borrowingDate = borrowingDate;
        this.returnDate = returnDate;
    }
}
