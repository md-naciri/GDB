package com.gdb;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Copy extends Book {
    private static int copyId;
    private String status;

    private Book book;

    public Copy(String isbn, String title, String author, int quantity, int copyId, String status, Book book){
        super(isbn, title, author, quantity);
        this.status = status;
        this.book = book;
        copyId++;
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
}
