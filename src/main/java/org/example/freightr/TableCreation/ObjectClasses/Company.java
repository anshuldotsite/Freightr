package org.example.freightr.TableCreation.ObjectClasses;

public class Company {
    private int companyId;
    private String companyName;
    private int companyNumber;
    private String companyEmail;



    public Company(int CompanyId,String companyName,int companyNumber,String companyEmail){
       this.companyId = CompanyId;
       this.companyName=companyName;
       this.companyNumber=companyNumber;
       this.companyEmail=companyEmail;
    }

    public Company(String companyName, int companyNumber, String companyEmail) {
        this.companyName = companyName;
        this.companyNumber = companyNumber;
        this.companyEmail = companyEmail;
    }

    public Company(){

    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }

    public int getCompanyNumber() {
        return companyNumber;
    }

    public void setCompanyNumber(int companyNumber) {
        this.companyNumber = companyNumber;
    }

    @Override
    public String toString() {
        return companyName;
    }
}
