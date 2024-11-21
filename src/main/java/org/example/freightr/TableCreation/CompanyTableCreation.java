package org.example.freightr.TableCreation;

import org.example.freightr.Database;
import org.example.freightr.TableCreation.ObjectClasses.Company;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import static org.example.freightr.TableCreation.Dbconst.*;

public class CompanyTableCreation {

    private static CompanyTableCreation instance;

    private final Database db = Database.getInstance();

    private CompanyTableCreation() {
    }

    public static synchronized CompanyTableCreation getInstance() {
        if (instance == null) {
            instance = new CompanyTableCreation();
        }
        return instance;
    }

    // Method to get all companies
    public ArrayList<Company> getAllCompanies() {
        String query = "SELECT * FROM " + TABLE_COMPANY_DETAILS;
        ArrayList<Company> companies = new ArrayList<>();

        try (Statement statement = db.getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                companies.add(new Company(
                        resultSet.getInt(COMPANY_COLUMN_ID),
                        resultSet.getString(COMPANY_COLUMN_NAME),
                        resultSet.getString(COMPANY_COLUMN_EMAIL),
                        resultSet.getInt(COMPANY_COLUMN_NUMBER)
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return companies;
    }

    // Method to get a specific company by ID
    public Company getCompany(int companyId) {
        String query = "SELECT * FROM " + TABLE_COMPANY_DETAILS + " WHERE " + COMPANY_COLUMN_ID + " = ?";
        try (PreparedStatement statement = db.getConnection().prepareStatement(query)) {
            statement.setInt(1, companyId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new Company(
                        resultSet.getInt(COMPANY_COLUMN_ID),
                        resultSet.getString(COMPANY_COLUMN_NAME),
                        resultSet.getString(COMPANY_COLUMN_EMAIL),
                        resultSet.getInt(COMPANY_COLUMN_NUMBER)
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public Company deleteCompany(int companyId) {
        Company company = getCompany(companyId);
        if (company == null) {
            return null;
        }

        String query = "DELETE FROM " + TABLE_COMPANY_DETAILS + " WHERE " + COMPANY_COLUMN_ID + " = "+companyId;
        try (PreparedStatement statement = db.getConnection().prepareStatement(query)) {

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                return company;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Company updateCompany(Company company) {
        String query = "UPDATE " + TABLE_COMPANY_DETAILS +
                " SET " +
                COMPANY_COLUMN_NAME + " = ?, " +
                COMPANY_COLUMN_EMAIL + " = ?, " +
                COMPANY_COLUMN_NUMBER + " = ? " +
                "WHERE " + COMPANY_COLUMN_ID + " = ?";
        try (PreparedStatement statement = db.getConnection().prepareStatement(query)) {
            statement.setString(1, company.getCompanyName());
            statement.setString(2, company.getCompanyEmail());
            statement.setInt(3, company.getCompanyNumber());
            statement.setInt(4, company.getCompanyId());

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                return getCompany(company.getCompanyId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
