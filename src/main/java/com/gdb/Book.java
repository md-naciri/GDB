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



    /*public static void addBook() {
        PreparedStatement p;
        //ResultSet r;
        String query = "INSERT INTO `book`(`isbn`, `title`, `author`, `quantity`) VALUES (?,?,?,?)";
        Scanner s = new Scanner(System.in);
        System.out.println("insert isbn, title, author, quantity");
        String i_isbn = s.nextLine();
        String i_title = s.nextLine();
        String i_author = s.nextLine();
        int i_quantity = s.nextInt();

        try {
            p = DB_connection.Cnx().prepareStatement(query);
            p.setString(1,i_isbn);
            p.setString(2,i_title);
            p.setString(3,i_author);
            p.setInt(4, i_quantity);
            if(p.executeUpdate()!=0){
                PreparedStatement p2;
                String query2 = "INSERT INTO `copies`(`status`, `isbn_book`) VALUES (?,?)";
                for(int i=0; i<i_quantity;i++) {
                    p2 = DB_connection.Cnx().prepareStatement(query2);
                    p2.setString(1, "Available");
                    p2.setString(2, i_isbn);
                    p2.execute();
                }

                    System.out.println("Book inserted");
            } else {
                System.out.println("Book not inserted");
            }
        }catch (Exception e){
            e.getMessage();
        }
    }*/

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
