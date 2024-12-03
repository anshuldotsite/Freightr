package org.example.freightr.TableCreation;

import org.example.freightr.Database;
import org.example.freightr.TableCreation.DOA.CompanyDoa;
import org.example.freightr.TableCreation.ObjectClasses.Company;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static org.example.freightr.TableCreation.Dbconst.*;

/**
 * @description  This class creates the company table
 */

public class CompanyTableCreation implements CompanyDoa {

    private static CompanyTableCreation instance;

    private final Database db = Database.getInstance();

    private CompanyTableCreation() {
    }

    public static CompanyTableCreation getInstance() {
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
                        resultSet.getInt(COMPANY_COLUMN_NUMBER),
                        resultSet.getString(COMPANY_COLUMN_EMAIL)
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return companies;
    }

    // Method to get a specific company by ID
    public Company getCompany(int companyId) {
        String query = "SELECT * FROM " + TABLE_COMPANY_DETAILS + " WHERE " + COMPANY_COLUMN_ID + " = "+ companyId;
        Company company = new Company();
        try (PreparedStatement statement = db.getConnection().prepareStatement(query)) {

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                company= new Company(
                        resultSet.getInt(COMPANY_COLUMN_ID),
                        resultSet.getString(COMPANY_COLUMN_NAME),
                        resultSet.getInt(COMPANY_COLUMN_NUMBER),
                        resultSet.getString(COMPANY_COLUMN_EMAIL)

                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return company;
    }


    public void deleteCompany(int companyId) {


        String query = "DELETE FROM " + TABLE_COMPANY_DETAILS + " WHERE " + COMPANY_COLUMN_ID + " = "+companyId;
        try (PreparedStatement statement = db.getConnection().prepareStatement(query)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateCompany(Company company) {
        String query = "UPDATE " + TABLE_COMPANY_DETAILS +
                " SET " +
                COMPANY_COLUMN_NAME + " = '" + company.getCompanyName() + "', " +
                COMPANY_COLUMN_EMAIL + " = '" + company.getCompanyEmail() + "', " +
                COMPANY_COLUMN_NUMBER + " = " + company.getCompanyNumber() +
                " WHERE " + COMPANY_COLUMN_ID + " = " + company.getCompanyId();
        try (PreparedStatement statement = db.getConnection().prepareStatement(query)) {
            statement.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @author Kautuk Prasad
     * @description Adds a new record to the company table.
     */
    @Override
    public void addCompany(Company company) {
        String query = "INSERT INTO " + TABLE_COMPANY_DETAILS + " (" +
                COMPANY_COLUMN_NAME + ", " +
                COMPANY_COLUMN_NUMBER + ", "  +
                COMPANY_COLUMN_EMAIL + ") VALUES (" +
                "'" + company.getCompanyName() + "', " +
                "'" + company.getCompanyNumber() + "', " +
                "'" + company.getCompanyEmail() + "'" +
                ");";

        try {
            db.getConnection().createStatement().execute(query);
            System.out.println("Added Company");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}

