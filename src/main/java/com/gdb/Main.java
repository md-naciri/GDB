package com.gdb;

import java.util.Scanner;
import java.util.regex.*;

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
        System.out.println("                                                                                                                                                       \n" +
                "                                                                                                                                                       \n" +
                "      ██    ██  █████  █████     ██    █████░ ███  ███        ███   ██▓   ███   ███   ██    ██     ██████  █████ ███   ███ █████ ▒██   ██ ██████       \n" +
                "      ██    ▓█  █  ▓█  █   ██   ████   ██  ██   ████▒         ███  ███▒   ███▒  ░███  ██   ████   ██       ██    ███▒ ████ ██     ███  ██   ██         \n" +
                "      ██    ▓█  █████  █████   ██  ██  █████     ██           █ ██░█ ██  ██ ░█  ░█ ██ ██  ██  ██ ░█   ████ █████ ██ █ █ ██ █████  █  █░██   ██         \n" +
                "      ██    ▓█  █   ██ ██ ██   ███████ ██ ██     ██           █  ██░ ██ ███████  █   ███ ███████  ██▓  ▒██ ██    ██ ███ ██ ██     █   ███   ██         \n" +
                "      █████ ██  █████  ██  █████    ██ ██  ███   ██          ██  ░█  ████     ██▒█    ██ ██    ██   ████   █████ ██  █  ██ █████  █▓   ██   ██         \n" +
                "                                                                                                                                                       \n" +
                "                                                                                                                                                       \n");
        System.out.println("Welcome to the Library!");

        while (true) {
            System.out.println("\n╔══════════════════════════════════════════════╗");
            System.out.println("║          Library Management System           ║");
            System.out.println("╠══════════════════════════════════════════════╣");
            System.out.println("║  Please choose an option:                    ║");
            System.out.println("║                                              ║");
            System.out.println("║  1.  Display all books                       ║");
            System.out.println("║  2.  Display available books                 ║");
            System.out.println("║  3.  Display all copies of a book            ║");
            System.out.println("║  4.  Add a new book                          ║");
            System.out.println("║  5.  Update book information                 ║");
            System.out.println("║  6.  Delete books                            ║");
            System.out.println("║  7.  Search for books                        ║");
            System.out.println("║  8.  Add a new borrower                      ║");
            System.out.println("║  9.  Borrow a book                           ║");
            System.out.println("║ 10.  Return a book                           ║");
            System.out.println("║ 11.  Statistics                              ║");
            System.out.println("║  0.  Exit                                    ║");
            System.out.println("╚══════════════════════════════════════════════╝");
            System.out.print("\nYour choice: ");
            String choice = scanner.nextLine();
            System.out.println("");
            switch (choice) {
                case "1":
                    Book.displayBooks();
                    break;
                case "2":
                    Book.displayAvailableBooks();
                    break;
                case "3":
                    Copy.displayCopies();
                    break;
                case "4":
                    addBook();
                    break;
                case "5":
                    updateBook();
                    break;
                case "6":
                    deleteBooks();
                    break;
                case "7":
                    searchBooks();
                    break;
                case "8":
                    addBorrower();
                    break;
                case "9":
                    borrowBook();
                    break;
                case "10":
                    returnBook();
                    break;
                case "11":
                    Copy.statistics();
                    break;
                case "0":
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
        String isbn = scanner.nextLine().trim();
        while (isbn.equals("")){
            System.out.println("ISBN cannot be empty, please enter a valid ISBN: ");
            isbn = scanner.nextLine().trim();
        }
        while (Book.checkIsbn(isbn)){
            System.out.println("Isbn already exists, enter a new one or type E to exit:  ");
            isbn = scanner.nextLine().trim();
            if (isbn.equals("E")){
                return;
            }
        }
        String title = scanner.nextLine().trim();
        while (title.equals("")){
            System.out.println("Title cannot be empty, please enter a valid title: ");
            title = scanner.nextLine().trim();
        }
        String author = scanner.nextLine().trim();
        String quantity = scanner.nextLine().trim();
        while (!quantity.matches("^\\d+$")){
            System.out.println("The Quantity must be a positive integer: ");
            quantity = scanner.nextLine().trim();
        }
        // Create a Book object with the provided details
        Book book = new Book(isbn, title, author, Integer.valueOf(quantity));
        book.addBook(book);
    }

    public static void updateBook() {
        String quantity = "0";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the ISBN of the book to edit: ");
        String isbn = scanner.nextLine();
        while (!Book.checkIsbn(isbn)){
            System.out.println("Isbn doesn't exist, enter a valid isbn or type E to exit:  ");
            isbn = scanner.nextLine().trim();
            if (isbn.equals("E")){
                return;
            }
        }
        System.out.println("Enter the new title or just hit Enter if you want to keep the previous title: ");
        String title = scanner.nextLine();
        System.out.println("Enter the new author or just hit Enter if you want to keep the previous author: ");
        String author = scanner.nextLine();
        //System.out.println("Enter the new quantity or just just type 0 if you want to keep the previous quantity:  ");
        //int quantity = scanner.nextInt();
  /*      int quantity = 0;
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
        } */
        System.out.println("You want to keep the previous quantity? Please answer by YES or NO: ");
        String choice = scanner.nextLine().trim();
        while (!choice.equals("YES") && !choice.equals("NO")){
            System.out.println("Please answer by YES or NO: ");
            choice = scanner.nextLine();
        }
        if (choice.equals("NO")){
            System.out.println("Enter the new quantity or type E to exit: ");
            quantity = scanner.nextLine();

            while (!quantity.matches("^\\d+$") || quantity.equals("")){
                if (quantity.equals("E")){
                    return;
                }
                System.out.println("Enter the new quantity or type E to exit: ");
                quantity = scanner.nextLine();
            }
        }


        Book book = new Book(isbn, title, author, Integer.valueOf(quantity));
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
        System.out.println("Enter the isbn of the book you want to delete or type E to exist: ");
        String isbn = scanner.nextLine().trim();
        if (isbn.equals("E")){
            return;
        }
        while (!Book.checkIsbn(isbn)){
            System.out.println("Book not found, enter a valid isbn or type E to exit:  ");
            isbn = scanner.nextLine().trim();
            if (isbn.equals("E")){
                return;
            }
        }
        Book book = new Book();
        book.removeBook(isbn);
    }

    public static void borrowBook(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Borrow a book");
        int x = addBorrower();
        System.out.println("\nList of available books: ");
        Book.displayAvailableBooks();
        boolean checkIsbn = false;
        String isbn ="";
        do{
            System.out.println("\nEnter the isbn of the book you want to borrow or type E to exit ");
            isbn = scanner.nextLine();
            if(isbn.equals("E")){
                return;
            }
            checkIsbn = Book.checkIsbn(isbn);
        }while(!checkIsbn);
        Copy copy = new Copy();
        copy.borrowBook(isbn, x);
    }

    public static void returnBook(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Return a book");
        System.out.println("\nEnter the Membership code of the borrower: ");
        String memberId = scanner.nextLine();
        while (!memberId.matches("^\\d+$") || memberId.equals("")){
            System.out.println("Membership code must be an integer, enter a valid Membership code or type E to exit");
            memberId = scanner.nextLine();
            if (memberId.equals("E")){
                return;
            }
        }
        while (!Borrowing.checkMembership(Integer.valueOf(memberId)) || !memberId.matches("^\\d+$") || memberId.equals("")){
            System.out.println("You didn't enter a valid Membership code, reenter a valid Membership code or type E to exit");
            memberId = scanner.nextLine();
            if (memberId.equals("E")){
                return;
            }
        }
        System.out.println("Enter the copyId of the book that you borrowed: ");
        String copyId = scanner.nextLine().trim();
        while (!copyId.matches("^\\d+$") || copyId.equals("") ){
            System.out.println("You didn't enter a valid CopyId, please enter a valid one:");
            copyId = scanner.nextLine().trim();
        }

        Copy copy = new Copy();
        copy.returnBook(Integer.valueOf(copyId), Integer.valueOf(memberId));
    }

    public static int addBorrower(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the borrower name: ");
        String name = scanner.nextLine().trim();
        while (name.equals("")){
            System.out.println("Name cannot be empty, please enter a valid name: ");
            name = scanner.nextLine().trim();
        }
        System.out.println("Enter the borrower phone number: ");
        String phone = scanner.nextLine().trim();
        while (phone.equals("")){
            System.out.println("Phone cannot be empty: ");
            phone = scanner.nextLine().trim();
        }
        Borrower borrower = new Borrower(name, phone);
        return  borrower.addBorrower();
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