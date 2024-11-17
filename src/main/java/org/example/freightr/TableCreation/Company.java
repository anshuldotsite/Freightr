package org.example.freightr.TableCreation;

public class Company {
    private int companyId;
    private String companyName;
    private String CompanyEmail;
    private int CompanyNumber;


    public Company(int CompanyId,String companyName,String companyEmail,int companyNumber){
       this.companyId = CompanyId;
       this.companyName=companyName;
       this.CompanyEmail=companyEmail;
       this.CompanyNumber=companyNumber;

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
        return CompanyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        CompanyEmail = companyEmail;
    }

    public int getCompanyNumber() {
        return CompanyNumber;
    }

    public void setCompanyNumber(int companyNumber) {
        CompanyNumber = companyNumber;
    }



}
