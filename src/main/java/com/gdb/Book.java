package com.gdb;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Book {

    protected String title;
    protected String isbn;
    protected String author;
    protected int quantity;

    public Book(String isbn, String title, String author, int quantity) {
        this.title = title;
        this.isbn = isbn;
        this.author = author;
        this.quantity = quantity;
    }

    public Book() {
    }

    public void addBook(Book book) {
        String bookQuery = "INSERT INTO `book`(`isbn`, `title`, `author`, `quantity`) VALUES (?,?,?,?)";
        String copiesQuery = "INSERT INTO `copies`(`status`, `isbn_book`) VALUES (?,?)";
        PreparedStatement bookStatement;
        PreparedStatement copiesStatement;
        try {
            bookStatement = DB_connection.Cnx().prepareStatement(bookQuery);
            copiesStatement = DB_connection.Cnx().prepareStatement(copiesQuery);


            bookStatement.setString(1, book.isbn);
            bookStatement.setString(2, book.title);
            bookStatement.setString(3, book.author);
            bookStatement.setInt(4, book.quantity);

            if (bookStatement.executeUpdate() != 0) {
                copiesStatement.setString(1, "Available");
                copiesStatement.setString(2, book.isbn);

                for (int i = 0; i < book.quantity; i++) {
                    copiesStatement.execute();
                }

                System.out.println("Book inserted");
            } else {
                System.out.println("Book not inserted");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateBook() {

    }

    public static void removeBook() {

    }

    public void searchBooks(String search) {
        String query = "SELECT * FROM `book` WHERE title = ? OR author = ? ;";
        PreparedStatement p;
        ResultSet r;
        try {
            p = DB_connection.Cnx().prepareStatement(query);
            p.setString(1,search);
            p.setString(2,search);
            r = p.executeQuery();
            while (r.next()){
                System.out.println("ISBN: "+r.getString(1)+"    TITLE: "+r.getString(2)+"    AUTHOR: "+r.getString(3)+"    QUANTITY: "+r.getInt(4));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void displayBook() {

    }

    public static void displayBooks() {
        String query = "SELECT\n" +
                "    b.isbn,\n" +
                "    b.title,\n" +
                "    b.author,\n" +
                "    b.quantity,\n" +
                "    SUM(CASE WHEN c.status = 'Available' THEN 1 ELSE 0 END) AS available,\n" +
                "    SUM(CASE WHEN c.status = 'Borrowed' THEN 1 ELSE 0 END) AS borrowed,\n" +
                "    SUM(CASE WHEN c.status = 'Lost' THEN 1 ELSE 0 END) AS lost\n" +
                "FROM book b JOIN copies c ON b.isbn = c.isbn_book\n" +
                "GROUP BY b.isbn;";
        PreparedStatement p;
        ResultSet r;
        try {
            p = DB_connection.Cnx().prepareStatement(query);
            r = p.executeQuery();
            while (r.next()){
                System.out.println("ISBN: "+r.getString(1)+"    TITLE: "+r.getString(2)+"    AUTHOR: "+r.getString(3)+"    QUANTITY: "+r.getInt(4)+"    Av Qu: "+r.getInt(5)+"    Br Qu: "+r.getInt(6)+"    Ls Qu: "+r.getInt(7));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void displayAvailableBooks() {
        String query = "SELECT\n" +
                "    b.isbn,\n" +
                "    b.title,\n" +
                "    b.author,\n" +
                "    b.quantity,\n" +
                "    SUM(CASE WHEN c.status = 'Available' THEN 1 ELSE 0 END) AS available\n" +
                "FROM book b JOIN copies c ON b.isbn = c.isbn_book\n" +
                "GROUP BY b.isbn\n" +
                "HAVING available > 0;";
        PreparedStatement p;
        ResultSet r;
        try {
            p = DB_connection.Cnx().prepareStatement(query);
            r = p.executeQuery();
            while (r.next()){
                System.out.println("ISBN: "+r.getString(1)+"    TITLE: "+r.getString(2)+"    AUTHOR: "+r.getString(3)+"    QUANTITY: "+r.getInt(4)+"    Av Qu: "+r.getInt(5));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }







    /*public static void displayBooks(){
        PreparedStatement p;
        ResultSet s;
        String query = "SELECT * FROM `borrower`";
        try {
            p = DB_connection.Cnx().prepareStatement(query);
            s = p.executeQuery();
            while (s.next()){
                int memberId = s.getInt("memberId");
                String name = s.getString("name");
                System.out.println(name+" "+memberId);
            }
        }catch (Exception e){

        }
    }*/
}
