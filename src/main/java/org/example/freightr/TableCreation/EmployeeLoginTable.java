package org.example.freightr.TableCreation;

import org.example.freightr.Database;
import org.example.freightr.TableCreation.DOA.EmployeeLogin;
import org.example.freightr.TableCreation.DOA.LoginDOA;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static org.example.freightr.TableCreation.Dbconst.*;


/**
 * CRUD functionality for employee login table
 * @author Kautuk Prasad
 */
public class EmployeeLoginTable implements LoginDOA {
    private static EmployeeLoginTable instance;
    private EmployeeLoginTable(){
        db= Database.getInstance();
    }
    Database db = Database.getInstance();
    ArrayList<EmployeeLogin> employeeLogins;


    /**
     * @author Kautuk Prasad
     * @description It gets all the employee login data from the table and stores in an arraylist.
     * @return All employee login data from the database.
     */
    @Override
    public ArrayList<EmployeeLogin> getAllEmployeeLogins() {
        String query="SELECT * FROM " + Dbconst.TABLE_EMPLOYEE_LOGIN;
        employeeLogins=new ArrayList<EmployeeLogin>();
        try {
            Statement getLogins=db.getConnection().createStatement();
            ResultSet data = getLogins.executeQuery(query);

            while (data.next()){
                employeeLogins.add(new EmployeeLogin(
                        data.getString(EMPLOYEE_FULL_NAME),
                        data.getString(EMPLOYEE_EMAIL),
                        data.getString(EMPLOYEE_DESIGNATION),
                        data.getString(EMPLOYEE_USER_NAME),
                        data.getString(EMPLOYEE_PASSWORD)));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return employeeLogins;
    }

    /**
     * @author Kautuk Prasad
     * @description It takes in an employee object and adds a new row in the table based on the object properties.
     * @param employeeLogin
     */
    @Override
    public void createAccount(EmployeeLogin employeeLogin) {
        String query = "INSERT INTO " + TABLE_EMPLOYEE_LOGIN +
                "(" + EMPLOYEE_FULL_NAME + "," +
                EMPLOYEE_EMAIL + "," +
                EMPLOYEE_DESIGNATION + "," +
                EMPLOYEE_USER_NAME + "," +
                EMPLOYEE_PASSWORD + ") VALUES ('" + employeeLogin.getFullName() + "','" +
                employeeLogin.getEmail() + "','" + employeeLogin.getDesignation() + "','" +
                employeeLogin.getUserName() + "','" + employeeLogin.getPassword() +
                "')";
        try {
            db.getConnection().createStatement().execute(query);
            System.out.println("Created Account");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @author Kautuk Prasad
     * @description It takes in username and the password from the employee and updates the password in matching column for the username in the databse.
     * @param userName
     * @param password
     */
    @Override
    public void updatePassword(String userName, String password) {
        String query = "UPDATE  " + TABLE_EMPLOYEE_LOGIN +
                " SET " + EMPLOYEE_PASSWORD + " = " + "'" + password +
                "' WHERE " +  EMPLOYEE_USER_NAME + " = '" + userName +"'";
        try{
            Statement updatePass = db.getConnection().createStatement();
            updatePass.executeUpdate(query);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * @author Kautuk Prasad
     * @description This method takes in the username entered by the user and returns a boolean values after checking
     * the database for the same username.
     * @param userName the username entered by the user.
     * @return a boolean value after checking the databse if the username exists.
     */

    @Override
    public boolean checkUserExists(String userName) {

        boolean userExists=false;
        String query = "SELECT " + EMPLOYEE_USER_NAME + " FROM " + TABLE_EMPLOYEE_LOGIN +
                " WHERE " + EMPLOYEE_USER_NAME + " = '" + userName + "'";
        try {
            Statement checkUser=db.getConnection().createStatement();
            ResultSet data = checkUser.executeQuery(query);

            if (data.next()){
                userExists=true;
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return userExists;
    }

    /**
     * @author Kautuk Prasad
     * @description It takes in the username and password and runs a query to see if the password matches the password present in the database for the particular username and returns a boolean based on the result.
     * @param userName
     * @param password
     * @return boolean
     */
    @Override
    public boolean signIn(String userName,String password) {
        boolean successSignIn=false;
        try{
            PreparedStatement checkSIgnIn =db.getConnection().prepareStatement("SELECT " + EMPLOYEE_PASSWORD + " FROM " + TABLE_EMPLOYEE_LOGIN +
                    " WHERE " + EMPLOYEE_USER_NAME + " = '" + userName+"'");
            ResultSet data = checkSIgnIn.executeQuery();
            data.next();
            String dataPassword = data.getString(EMPLOYEE_PASSWORD);
            if (dataPassword.equals(password)){
                successSignIn=true;
            }else {
                successSignIn=false;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return successSignIn;
    }

    /**
     * @author Kautuk Prasad
     * @description This takes in the username of the employee and returns the name of the employee.
     * @return Employee Name
     */
    @Override
    public String getEmployeeName(String userName) {
        String employeeName="";
        String query="SELECT "+  EMPLOYEE_FULL_NAME + " FROM " + TABLE_EMPLOYEE_LOGIN +
                " WHERE " + EMPLOYEE_USER_NAME + " = '" + userName +"'";
        try {
            Statement getName=db.getConnection().createStatement();
            ResultSet data = getName.executeQuery(query);

            if (data.next()){
                employeeName=data.getString(EMPLOYEE_FULL_NAME);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return employeeName;
    }



    public static EmployeeLoginTable getInstance(){
        if(instance == null){
            instance = new EmployeeLoginTable();
        }
        return instance;

    }
}
