package com.gdb;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Borrowing {
    private static int id;
    private Copy bookId;
    private Borrower memberId;
    private LocalDate borrowingDate;
    private LocalDate returnDate;

    public Borrowing(Copy bookId, Borrower memberId, LocalDate borrowingDate, LocalDate returnDate) {
        id++;
        this.bookId = bookId;
        this.memberId = memberId;
        this.borrowingDate = borrowingDate;
        this.returnDate = returnDate;
    }

    public Borrowing(){
    }

    public static void checkReturnDate(){
        LocalDate currentDate = LocalDate.now();
        String queryCheck = "SELECT copies_copyId FROM borrowing WHERE returnDate < ?";
        String queryUpdate = "UPDATE copies SET status = 'Lost' WHERE copyId = ?;";
        PreparedStatement pCheck;
        PreparedStatement pUpdate;
        ResultSet rCheck;
        try {
            pCheck = DB_connection.Cnx().prepareStatement(queryCheck);
            pCheck.setDate(1, java.sql.Date.valueOf(currentDate));
            rCheck = pCheck.executeQuery();
            while (rCheck.next()){
                int copyId = rCheck.getInt(1);
                pUpdate = DB_connection.Cnx().prepareStatement(queryUpdate);
                pUpdate.setInt(1,copyId);
                pUpdate.execute();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /*public void scheduleStatusUpdate(){
        Timer timer = new Timer(true);

        long delay = 0; // Start immediately
        long period = 10 * 1000; // 5min
        //long period = 24 * 60 * 60 * 1000; // 24 hours
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                checkReturnDate();
                System.out.println("message");
            }
        }, delay, period);
    }*/
}