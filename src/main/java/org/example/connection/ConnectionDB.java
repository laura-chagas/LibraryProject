package org.example.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {

    public static Connection connect(){
        Connection conn = null;

        try{
            conn = DriverManager.getConnection(url,user,password);
            System.out.println("Connected to the PostgreSQL server successfully.");
            return conn;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

}
