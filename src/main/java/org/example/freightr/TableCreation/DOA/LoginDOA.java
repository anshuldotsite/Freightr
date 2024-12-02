package org.example.freightr.TableCreation.DOA;

import java.util.ArrayList;

/**
 * DOA for employee login table
 * @author Kautuk Prasad
 */
public interface LoginDOA {
    public ArrayList<EmployeeLogin> getAllEmployeeLogins();
    public void createAccount(EmployeeLogin employeeLogin);
    public void updatePassword(String userName, String password);
    public boolean checkUserExists(String userName);
    public boolean signIn(String userName,String password);
    public String getEmployeeName(String userName);
}
