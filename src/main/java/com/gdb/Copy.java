package com.gdb;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
import java.time.LocalDate;

public class Copy {
    private static int copyId;
    private String status;

    private Book book;

    public Copy(int copyId, String status){
        this.status = status;
        copyId++;
    }
    public Copy(){
    }

    public int getcopyId() {
        return copyId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static void addCopy() {

    }

    public static void displayCopies(){
        PreparedStatement p;
        ResultSet r;
        Scanner s = new Scanner(System.in);
        System.out.println("insert the isbn: ");
        String i_isbn = s.nextLine();
        while (!Book.checkIsbn(i_isbn)){
            System.out.println("The isbn is incorrect, enter a valid one or type E to exit: ");
            i_isbn = s.nextLine();
            if (i_isbn.equals("E")){
                return;
            }
        };
        String query = "SELECT c.*, b.* FROM copies c INNER JOIN book b ON c.isbn_book = b.isbn WHERE c.isbn_book = ?";
        try {
            p = DB_connection.Cnx().prepareStatement(query);
            p.setString(1,i_isbn);
            r = p.executeQuery();
            while (r.next()){
               System.out.println("-Title: "+r.getString("title")+"     -Author: "+r.getString("author")+"     -ISBN: "+r.getString("isbn_book")+"     -Copy_ID: "+r.getString("copyId")+"     - Status: "+r.getString("status"));
            }
        }catch (Exception e){
            e.getMessage();
        }
    }

    public void borrowBook(String isbn, int memberId){
        String queryIsAvailable = "SELECT copyId FROM `copies` WHERE isbn_book = ? AND status = 'Available' LIMIT 1";
        String queryBorrowing = "INSERT INTO `borrowing`(`copies_copyId`, `borrower_memberId`, `borrowingDate`, `returnDate`) VALUES (?,?,?,?);";
        PreparedStatement pIsAvailable;
        PreparedStatement pBorrowing;
        ResultSet rIsAvailable;
        try {
            pIsAvailable = DB_connection.Cnx().prepareStatement(queryIsAvailable);
            pIsAvailable.setString(1,isbn);
            rIsAvailable = pIsAvailable.executeQuery();
            if(rIsAvailable.next()){
                int copyId = rIsAvailable.getInt(1);
                try {
                    LocalDate borrowingDate = LocalDate.now();
                    LocalDate returnDate = borrowingDate.plusDays(15);

                    pBorrowing = DB_connection.Cnx().prepareStatement(queryBorrowing);
                    pBorrowing.setInt(1,copyId);
                    pBorrowing.setInt(2,memberId);
                    pBorrowing.setDate(3, java.sql.Date.valueOf(borrowingDate));
                    pBorrowing.setDate(4, java.sql.Date.valueOf(returnDate));
                    if (pBorrowing.executeUpdate()!=0){
                        System.out.println("Book borrowed");
                        System.out.println("your Copy ID is: "+ copyId);
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }else {
                System.out.println("There is no copy available of this book");
                return;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void returnBook(int copyId, int memberId){
        String query = "DELETE FROM `borrowing` WHERE copies_copyId = ? AND borrower_memberId = ?;";
        PreparedStatement p;
        try {
            p = DB_connection.Cnx().prepareStatement(query);
            p.setInt(1, copyId);
            p.setInt(2, memberId);
            if (p.executeUpdate() != 0){
                System.out.println("Book returned successfully");
            } else {
                System.out.println("copyId incorrect");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void statistics() {
        String query = "SELECT\n" +
                "  SUM(CASE WHEN status = 'Available' THEN 1 ELSE 0 END) AS available,\n" +
                "  SUM(CASE WHEN status = 'Borrowed' THEN 1 ELSE 0 END) AS borrowed,\n" +
                "  SUM(CASE WHEN status = 'Lost' THEN 1 ELSE 0 END) AS lost,\n" +
                "  COUNT(*) AS total\n" +
                "FROM copies;";
        PreparedStatement p;
        ResultSet r;
        try {
            p = DB_connection.Cnx().prepareStatement(query);
            r = p.executeQuery();
            while (r.next()){
                System.out.println("All books: " + r.getInt(4) +
                        "\nAvailable books: " + r.getInt(1) +
                        "\nBorrowed books: " + r.getInt(2) +
                        "\nLost books: " + r.getInt(3));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
