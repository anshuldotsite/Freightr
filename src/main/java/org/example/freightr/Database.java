package org.example.freightr;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;
import java.util.StringTokenizer;


public class Database {
    private static Database instance;
    private Connection connection = null;
    private static String DB_NAME;
    private static String DB_PASSWORD;
    private static String DB_USER;
    private static String HOST;
    private static boolean isConnected;

    private Database() {
        if (connection == null) {
            //reading the credentials from txt file and connecting to server
            try {
                BufferedReader reader = new BufferedReader(new FileReader("credentials.txt"));
                String credentialsStr = reader.readLine();
                StringTokenizer stringTokenizer = new StringTokenizer(credentialsStr);
                String credentials[] = new String[4];
                int i = 0;
                while (stringTokenizer.hasMoreTokens()) {
                    credentials[i++] = stringTokenizer.nextToken();
                }
                DB_USER = credentials[0];
                DB_NAME = credentials[1];
                HOST = credentials[2];
                DB_PASSWORD = credentials[3];


                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql://" +HOST+ "/" + DB_NAME +
                                "?serverTimezone=UTC",
                        DB_USER,
                        DB_PASSWORD);
                System.out.println("Connection successful");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //getting the only object of the class
    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    //checking the connection status
    public boolean getStatus() {
        try {
            if (connection.isValid(300) == false) {
                isConnected = false;
            } else {
                isConnected = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isConnected;
    }
}

