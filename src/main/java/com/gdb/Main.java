package com.gdb;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //System.out.printf("Hello and welcome!\n");
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
        Borrowing.checkReturnDate();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Library!");

        while (true) {
            System.out.println("\nPlease choose an option:");
            System.out.println("1.  Display all books");
            System.out.println("2.  Display available books");
            System.out.println("3.  Display all copies of a book");
            System.out.println("4.  Add a new book");
            System.out.println("5.  Update book information");
            System.out.println("6.  Delete books");
            System.out.println("7.  Search for books");
            System.out.println("8.  Add a new borrower");
            System.out.println("9.  Search for a borrower");
            System.out.println("10. Borrow a book");
            System.out.println("11. Return a book");
            System.out.println("0.  Exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    Book.displayBooks();
                    break;
                case 2:
                    Book.displayAvailableBooks();
                    break;
                case 3:
                    Copy.displayCopies();
                    break;
                case 4:
                    addBook();
                    break;
                case 5:
                    updateBook();
                    break;
                case 6:
                    deleteBooks();
                    break;
                case 7:
                    searchBooks();
                    break;
                case 8:
                    addBorrower();
                    break;
                case 9:
                    searchBorrower();
                    break;
                case 10:
                    borrowBook();
                    break;
                case 11:
                    returnBook();
                    break;
                case 0:
                    System.out.println("Goodbye! Have a great day!");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

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