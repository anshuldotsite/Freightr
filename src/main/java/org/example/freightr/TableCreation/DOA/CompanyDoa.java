package org.example.freightr.TableCreation.DOA;

import org.example.freightr.TableCreation.Company;

import java.util.ArrayList;

public interface CompanyDoa {
    public ArrayList<Company> getAllCompanies();

    public Company getCompany(int companyId);

    public Company deleteCompany(int companyId);

    public Company updateCompany(Company company);
}

