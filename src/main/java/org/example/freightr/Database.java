package org.example.freightr;

import org.example.freightr.TableCreation.Dbconst;

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
    private String COMPANY_KEY;
    private String COMPANY_NAME;
    private static boolean isConnected;

    /**
     * @author Kautuk Prasad, Kohinoor Jeet Singh
     * @description This constructor automatically connects to the database using credentials given in txt file
     * and creates the specified table in the database upon connecting.
     */
    private Database() {
        if (connection == null) {
            //reading the credentials from txt file and connecting to server
            try {
                BufferedReader reader = new BufferedReader(new FileReader("credentials.txt"));
                String credentialsStr = reader.readLine();
                StringTokenizer stringTokenizer = new StringTokenizer(credentialsStr);
                String credentials[] = new String[6];
                int i = 0;
                while (stringTokenizer.hasMoreTokens()) {
                    credentials[i++] = stringTokenizer.nextToken();
                }
                DB_USER = credentials[0];
                DB_NAME = credentials[1];
                HOST = credentials[2];
                DB_PASSWORD = credentials[3];
                COMPANY_NAME = credentials[4];
                COMPANY_KEY = credentials[5];


                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql://" +HOST+ "/" + DB_NAME +
                                "?serverTimezone=UTC",
                        DB_USER,
                        DB_PASSWORD);
                System.out.println("Connection successful");
                createTable(Dbconst.TABLE_CUSTOMER, Dbconst.CREATE_TABLE_CUSTOMER, connection);
                createTable(Dbconst.TABLE_PACKAGE, Dbconst.CREATE_TABLE_PACKAGE, connection);
                createTable(Dbconst.TABLE_CUSTOMER_PACKAGES, Dbconst.CREATE_TABLE_CUSTOMER_PACKAGES, connection);
                createTable(Dbconst.TABLE_COMPANY_DETAILS, Dbconst.CREATE_TABLE_COMPANY_DETAILS, connection);
                createTable(Dbconst.TABLE_STATUS,Dbconst.CREATE_TABLE_STATUS,connection);
                createTable(Dbconst.TABLE_PACKAGE_TRACKING, Dbconst.CREATE_TABLE_PACKAGE_TRACKING, connection);
                createTable(Dbconst.TABLE_EMPLOYEE_LOGIN,Dbconst.CREATE_TABLE_EMPLOYEE_LOGIN,connection);


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void createTable(String tableName, String tableQuery, Connection connection) throws SQLException {
        Statement createTable;
        DatabaseMetaData md = connection.getMetaData();
        ResultSet resultSet = md.getTables(DB_NAME, null, tableName, null);
        if(resultSet.next()){
            System.out.println(tableName + " table already exists");
        }
        else{
            createTable = connection.createStatement();
            createTable.execute(tableQuery);
            System.out.println("The " + tableName + " table has been created");
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

    /**
     * @author Kautuk Prasad
     * @description It checks to see if the database is connected or not and returns a boolean depending on the result.
     * @return boolean
     */
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
    public Connection getConnection() {
        if (connection == null) {
            System.out.println("no connection");
        }
        return connection;
    }

    public String getCompanyKey(){
        return COMPANY_KEY;
    }

    public String getCompanyName(){
        return COMPANY_NAME;
    }
}

