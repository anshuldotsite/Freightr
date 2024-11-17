package org.example.freightr.TableCreation.DOA;

import java.util.ArrayList;

public interface LoginDOA {
    public ArrayList<EmployeeLogin> getAllEmployeeLogins();
    public void createAccount(EmployeeLogin employeeLogin);
    public void updatePassword(EmployeeLogin employeeLogin);
    public void checkUserExists(String userName);
}
