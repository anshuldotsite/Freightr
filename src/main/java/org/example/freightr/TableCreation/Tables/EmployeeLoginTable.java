package org.example.freightr.TableCreation.Tables;

import org.example.freightr.Database;
import org.example.freightr.TableCreation.DOA.EmployeeLogin;
import org.example.freightr.TableCreation.DOA.LoginDOA;
import org.example.freightr.TableCreation.Dbconst;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static org.example.freightr.TableCreation.Dbconst.*;



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
                employeeLogins.add(new EmployeeLogin(data.getInt(EMPLOYEE_LOGIN_COLUMN_ID),
                        data.getString(EMPLOYEE_FULL_NAME),
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
                "(" + EMPLOYEE_LOGIN_COLUMN_ID + ", " +
                EMPLOYEE_FULL_NAME + "," +
                EMPLOYEE_USER_NAME + "," +
                EMPLOYEE_PASSWORD + ") VALUES ('" +
                employeeLogin.getId() + "','" + employeeLogin.getFullName() + "','" +
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
}
