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

    public static void searchBooks() {

    }

    public static void displayBook() {

    }

    public static void displayBooks() {

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
