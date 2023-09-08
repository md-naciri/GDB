package com.gdb;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Press Alt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!\n");
        //addBook();
        //Book.displayBooks();
        //updateBook();
        //Book.displayAvailableBooks();
        //searchBooks();
        //deleteBooks();
        //Copy.displayCopies();
        //addBorrower();
        //borrowBook();
        //searchBorrower();
        //returnBook();

        Borrowing borrowing = new Borrowing();
        borrowing.scheduleStatusUpdate();

    }

    public static void addBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insert ISBN, title, author, quantity: ");
        String isbn = scanner.nextLine();
        String title = scanner.nextLine();
        String author = scanner.nextLine();
        int quantity = scanner.nextInt();
        // Create a Book object with the provided details
        Book book = new Book(isbn, title, author, quantity);
        book.addBook(book);
    }

    public static void updateBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the ISBN of the book to edit: ");
        String isbn = scanner.nextLine();
        System.out.println("Enter the new title or just hit Enter if you want to keep the previous title: ");
        String title = scanner.nextLine();
        System.out.println("Enter the new author or just hit Enter if you want to keep the previous author: ");
        String author = scanner.nextLine();
        //System.out.println("Enter the new quantity or just just type 0 if you want to keep the previous quantity:  ");
        //int quantity = scanner.nextInt();
        int quantity = 0;
        while (true) {
            System.out.println("Enter the new quantity or just type 0 if you want to keep the previous quantity: ");
            String quantityInput = scanner.nextLine();
            if (quantityInput.isEmpty()) {
                break; // No new quantity provided, break the loop
            }
            try {
                quantity = Integer.parseInt(quantityInput);
                break; // Valid integer input, break the loop
            } catch (NumberFormatException e) {
                System.out.println("Invalid quantity. Please enter a valid number.");
            }
        }

        // Create a Book object with the provided details
        Book book = new Book(isbn, title, author, quantity);
        book.updateBook(book);
    }

    public static void searchBooks () {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Search for the book by the title or the author: ");
        String search = scanner.nextLine();
        Book book = new Book();
        book.searchBooks(search);
    }

    public static void deleteBooks () {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the isbn of the book you want to delete: ");
        String isbn = scanner.nextLine();
        Book book = new Book();
        book.removeBook(isbn);
    }

    public static void borrowBook(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Borrow a book");
        addBorrower();
        searchBooks();
        System.out.println("Enter the isbn of the book you want to borrow: ");
        String isbn = scanner.nextLine();
        System.out.println("Enter the MemberId of the borrower: ");
        int memberId = scanner.nextInt();
        Copy copy = new Copy();
        copy.borrowBook(isbn, memberId);
    }

    public static void returnBook(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Return a book");
        System.out.println("Enter the copyId of the book you want to return: ");
        int copyId = scanner.nextInt();
        System.out.println("Enter the MemberId of the borrower: ");
        int memberId = scanner.nextInt();
        Copy copy = new Copy();
        copy.returnBook(copyId, memberId);
    }

    public static void addBorrower(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the borrower name: ");
        String name = scanner.nextLine();
        System.out.println("Enter the borrower phone number: ");
        String phone = scanner.nextLine();
        Borrower borrower = new Borrower(name, phone);
        borrower.addBorrower();
    }

    public static void searchBorrower() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the name: ");
        String name = scanner.nextLine();
        System.out.println("Enter the phone number: ");
        String phone = scanner.nextLine();
        Borrower borrower = new Borrower(name, phone);
        borrower.searchBorrower();
    }



}