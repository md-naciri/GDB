package com.gdb;

import com.sun.jdi.PrimitiveValue;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Borrower {
    private static int memberId;
    private String name;
    private String phone;

    public Borrower( String name, String phone) {
        memberId++;
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getMemberId() {
        return memberId;
    }

    public void addBorrower(){
        String querySetBorrower = "INSERT INTO borrower(name, phone) VALUES (?,?)";
        PreparedStatement pSet;
        String queryGetBorrower = "SELECT * FROM borrower WHERE name = ? AND phone = ? ;";
        PreparedStatement pGet;
        ResultSet r;
        if(!checkBorrower()){
            try {
                pSet = DB_connection.Cnx().prepareStatement(querySetBorrower);
                pSet.setString(1, name);
                pSet.setString(2, phone);
                if (pSet.executeUpdate()!=0){
                    System.out.println("Borrower added successfully");
                    pGet = DB_connection.Cnx().prepareStatement(queryGetBorrower);
                    pGet.setString(1, name);
                    pGet.setString(2, phone);
                    r = pGet.executeQuery();
                    if (r.next()){
                        System.out.println("memberId: "+r.getString(1)+"    Name: "+r.getString(2)+"    Phone: "+r.getString(3));
                    }
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    public void searchBorrower(){
        String query = "SELECT * FROM borrower WHERE name = ? AND phone = ? ;";
        PreparedStatement p;
        ResultSet r;
        try {
            boolean check = true;
            p = DB_connection.Cnx().prepareStatement(query);
            p.setString(1,name);
            p.setString(2,phone);
            r = p.executeQuery();
            if (r.next()){
                check = false;
                System.out.println("memberId: "+r.getString(1)+"    Name: "+r.getString(2)+"    Phone: "+r.getString(3));
            }
            if (check){
                System.out.println("Borrower not found");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public boolean checkBorrower(){
        String query = "SELECT * FROM borrower WHERE name = ? AND phone = ? ;";
        PreparedStatement p;
        ResultSet r;
        try {
            p = DB_connection.Cnx().prepareStatement(query);
            p.setString(1,name);
            p.setString(2,phone);
            r = p.executeQuery();
            if (r.next()){
                System.out.println("Borrower already exists");
                System.out.println("memberId: "+r.getString(1)+"    Name: "+r.getString(2)+"    Phone: "+r.getString(3));
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    public void returnBook(){

    }
}
