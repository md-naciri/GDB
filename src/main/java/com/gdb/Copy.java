package com.gdb;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

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

    public void borrowBook(String isbn){
        String queryIsAvailable = "SELECT copyId FROM `copies` WHERE isbn_book = ? AND status = 'Available' LIMIT 1";
        String queryBorrow = "UPDATE copies SET status='Borrowed' WHERE copyId = ?";
        PreparedStatement pIsAvailable;
        PreparedStatement pBorrow;
        ResultSet rIsAvailable;
        try {
            pIsAvailable = DB_connection.Cnx().prepareStatement(queryIsAvailable);
            pIsAvailable.setString(1,isbn);
            rIsAvailable = pIsAvailable.executeQuery();
            if(rIsAvailable.next()){
                int copyId = rIsAvailable.getInt(1);




                pBorrow = DB_connection.Cnx().prepareStatement(queryBorrow);
                //pBorrow.setInt(1,copyId);
                //pBorrow.executeUpdate();


            }else {
                System.out.println("There is no copy available of this book");
                return;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
