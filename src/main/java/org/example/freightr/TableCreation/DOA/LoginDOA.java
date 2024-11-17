package org.example.freightr.TableCreation.DOA;

import java.util.ArrayList;

/**
 * DOA for employee login table
 * @author Kautuk Prasad
 */
public interface LoginDOA {
    public ArrayList<EmployeeLogin> getAllEmployeeLogins();
    public void createAccount(EmployeeLogin employeeLogin);
    public void updatePassword(EmployeeLogin employeeLogin);
    public void checkUserExists(String userName);
    public boolean signIn(String userName,String password);
}
