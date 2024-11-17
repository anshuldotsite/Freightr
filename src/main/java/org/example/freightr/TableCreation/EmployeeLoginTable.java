package org.example.freightr.TableCreation;

import org.example.freightr.Database;
import org.example.freightr.TableCreation.DOA.EmployeeLogin;
import org.example.freightr.TableCreation.DOA.LoginDOA;

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

    @Override
    public void updatePassword(EmployeeLogin employeeLogin) {

    }

    @Override
    public void checkUserExists(String userName) {

    }

    public static EmployeeLoginTable getInstance(){
        if(instance == null){
            instance = new EmployeeLoginTable();
        }
        return instance;

    }
}
