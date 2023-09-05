package com.gdb;

import java.sql.Connection;
import java.sql.DriverManager;

public class DB_connection {
    public static Connection Cnx(){
        String url = "jdbc:mysql://localhost:3306/gdb";
        String username = "root";
        String password = "";
        Connection ret = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            ret = DriverManager.getConnection(url, username, password);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ret;
    }
}