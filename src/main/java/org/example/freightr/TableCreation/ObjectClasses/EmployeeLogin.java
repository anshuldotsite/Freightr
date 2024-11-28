package org.example.freightr.TableCreation.ObjectClasses;

/**
 * POJO for employee login table
 * @author Kautuk Prasad
 */
public class EmployeeLogin {
    private int id;
    private String fullName;
    private String email;
    private String designation;
    private String userName;
    private String password;

    public EmployeeLogin(String fullName, String email, String designation, String userName, String password) {
        this.fullName = fullName;
        this.email = email;
        this.designation = designation;
        this.userName = userName;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
