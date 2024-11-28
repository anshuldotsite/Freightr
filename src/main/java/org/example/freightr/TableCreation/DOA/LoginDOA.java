package org.example.freightr.TableCreation.DOA;

import org.example.freightr.TableCreation.ObjectClasses.EmployeeLogin;

import java.util.ArrayList;

/**
 * DOA for employee login table
 * @author Kautuk Prasad
 */
public interface LoginDOA {
    public ArrayList<EmployeeLogin> getAllEmployeeLogins();
    public void createAccount(EmployeeLogin employeeLogin);
    public void updatePassword(String userName, String password);
    public void checkUserExists(String userName);
    public boolean signIn(String userName,String password);
}
