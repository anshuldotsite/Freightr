package org.example.freightr.TableCreation;

public class Dbconst {
    public static final String TABLE_CUSTOMER = "customer";
    public static final String CUSTOMER_COLUMN_ID = "customer_id";
    public static final String CUSTOMER_COLUMN_COMPANY_ID = "company_id";
    public static final String CUSTOMER_COLUMN_FIRST_NAME = "first_name";
    public static final String CUSTOMER_COLUMN_LAST_NAME = "last_name";
    public static final String CUSTOMER_COLUMN_CONTACT_NUMBER = "contact_number";
    public static final String CUSTOMER_COLUMN_EMAIL = "email";
    public static final String CUSTOMER_COLUMN_ADDRESS = "address";
    public static final String CUSTOMER_COLUMN_ZIPCODE = "zipcode";
    public static final String CUSTOMER_COLUMN_CITY = "city";
    public static final String CUSTOMER_COLUMN_PROVINCE = "province";
    public static final String CUSTOMER_COLUMN_COUNTRY = "country";
    public static final String CUSTOMER_COLUMN_TYPE = "customer_type";
    // const for package
    public static final String TABLE_PACKAGE = "package";
    public static final String PACKAGE_COLUMN_ID = "package_id";
    public static final String PACKAGE_COLUMN_DESCRIPTION = "package_description";
    public static final String PACKAGE_COLUMN_SENT_DATE = "sent_date";
    public static final String PACKAGE_COLUMN_WEIGHT = "weight";
    public static final String PACKAGE_COLUMN_HEIGHT = "height";
    public static final String PACKAGE_COLUMN_LENGTH = "length";
    public static final String PACKAGE_COLUMN_BREADTH = "breadth";
    public static final String PACKAGE_COLUMN_PRICE = "price";



    //  constants for Customer Packages table
    public static final String TABLE_CUSTOMER_PACKAGES = "customer_packages";

    public static final String CUSTOMER_PACKAGES_COLUMN_CUSTOMER_ID = "customer_id";
    public static final String CUSTOMER_PACKAGES_COLUMN_PACKAGE_ID = "package_id";

    //  constants  Company Details table
    public static final String TABLE_COMPANY_DETAILS = "company_details";
    public static final String COMPANY_COLUMN_ID = "company_id";
    public static final String COMPANY_COLUMN_NAME = "company_name";
    public static final String COMPANY_COLUMN_NUMBER = "company_number";
    public static final String COMPANY_COLUMN_EMAIL = "company_email";

    //  constants for Package Tracking table
    public static final String TABLE_PACKAGE_TRACKING = "package_tracking";
    public static final String TRACKING_COLUMN_ID = "tracking_id";
    public static final String TRACKING_COLUMN_PACKAGE_ID = "package_id";
    public static final String TRACKING_COLUMN_LOCATION = "location";
    public static final String TRACKING_COLUMN_STATUS = "status";

    /**
     * Employee Login Table
     * @author Kautuk Prasad
     */
    public static final String TABLE_EMPLOYEE_LOGIN = "employee_login";
    public static final String EMPLOYEE_LOGIN_COLUMN_ID = "id";
    public static final String EMPLOYEE_FULL_NAME = "full_name";
    public static final String EMPLOYEE_EMAIL="employee_email";
    public static final String EMPLOYEE_DESIGNATION = "designation";

    public static final String EMPLOYEE_USER_NAME = "user_name";
    public static final String EMPLOYEE_PASSWORD = "password";

    public static final String CREATE_TABLE_CUSTOMER =
            "CREATE TABLE " + TABLE_CUSTOMER + " (" +
                    CUSTOMER_COLUMN_ID + " int NOT NULL AUTO_INCREMENT, " +
                    CUSTOMER_COLUMN_COMPANY_ID + " int, " +
                    CUSTOMER_COLUMN_FIRST_NAME + " VARCHAR(52), " +
                    CUSTOMER_COLUMN_LAST_NAME + " VARCHAR(50), " +
                    CUSTOMER_COLUMN_CONTACT_NUMBER + " VARCHAR(13), " +
                    CUSTOMER_COLUMN_EMAIL + " VARCHAR(150), " +
                    CUSTOMER_COLUMN_ADDRESS + " VARCHAR(255), " +
                    CUSTOMER_COLUMN_ZIPCODE + " VARCHAR(12), " +
                    CUSTOMER_COLUMN_CITY + " VARCHAR(50), " +
                    CUSTOMER_COLUMN_PROVINCE + " VARCHAR(50), " +
                    CUSTOMER_COLUMN_COUNTRY + " VARCHAR(60), " +
                    CUSTOMER_COLUMN_TYPE + " VARCHAR(50), " +
                    "PRIMARY KEY(" + CUSTOMER_COLUMN_ID + "));";

    public static final String CREATE_TABLE_PACKAGE =
            "CREATE TABLE " + TABLE_PACKAGE + " (" +
                    PACKAGE_COLUMN_ID + " int NOT NULL AUTO_INCREMENT, " +
                    PACKAGE_COLUMN_DESCRIPTION + " VARCHAR(255), " +
                    PACKAGE_COLUMN_SENT_DATE + " DATE, " +
                    PACKAGE_COLUMN_WEIGHT + " DECIMAL(10, 2), " +
                    PACKAGE_COLUMN_HEIGHT + " DECIMAL(10, 2), " +
                    PACKAGE_COLUMN_LENGTH + " DECIMAL(10, 2), " +
                    PACKAGE_COLUMN_BREADTH + " DECIMAL(10, 2), " +
                    PACKAGE_COLUMN_PRICE + " DECIMAL(10, 2), " +
                    "PRIMARY KEY(" + PACKAGE_COLUMN_ID + "));";


    public static final String CREATE_TABLE_CUSTOMER_PACKAGES =
            "CREATE TABLE " + TABLE_CUSTOMER_PACKAGES + " (" +
                    CUSTOMER_PACKAGES_COLUMN_CUSTOMER_ID + " int NOT NULL, " +
                    CUSTOMER_PACKAGES_COLUMN_PACKAGE_ID + " int NOT NULL, " +
                    "FOREIGN KEY(" + CUSTOMER_PACKAGES_COLUMN_CUSTOMER_ID + ") REFERENCES " + TABLE_CUSTOMER + "(" + CUSTOMER_COLUMN_ID + "), " +
                    "FOREIGN KEY(" + CUSTOMER_PACKAGES_COLUMN_PACKAGE_ID + ") REFERENCES " + TABLE_PACKAGE + "(" + PACKAGE_COLUMN_ID + "));";

    // SQL to create CompanyDetails table
    public static final String CREATE_TABLE_COMPANY_DETAILS =
            "CREATE TABLE " + TABLE_COMPANY_DETAILS + " (" +
                    COMPANY_COLUMN_ID + " int NOT NULL AUTO_INCREMENT, " +
                    COMPANY_COLUMN_NAME + " VARCHAR(255) NOT NULL, " +
                    COMPANY_COLUMN_NUMBER + " VARCHAR(50) NOT NULL, " +
                    COMPANY_COLUMN_EMAIL + " VARCHAR(150) NOT NULL, " +
                    "PRIMARY KEY(" + COMPANY_COLUMN_ID + "));";

    // SQL to create PackageTracking table
    public static final String CREATE_TABLE_PACKAGE_TRACKING =
            "CREATE TABLE " + TABLE_PACKAGE_TRACKING + " (" +
                    TRACKING_COLUMN_ID + " int NOT NULL AUTO_INCREMENT, " +
                    TRACKING_COLUMN_PACKAGE_ID + " int NOT NULL, " +
                    TRACKING_COLUMN_LOCATION + " VARCHAR(255) NOT NULL, " +
                    TRACKING_COLUMN_STATUS + " VARCHAR(100) NOT NULL, " +
                    "PRIMARY KEY(" + TRACKING_COLUMN_ID + "), " +
                    "FOREIGN KEY(" + TRACKING_COLUMN_PACKAGE_ID + ") REFERENCES " + TABLE_PACKAGE + "(" + PACKAGE_COLUMN_ID + "));";

    /**
     *creating employee login table
     * @author Kautuk Prasad
     */

    public static final String CREATE_TABLE_EMPLOYEE_LOGIN =
            "CREATE TABLE " + TABLE_EMPLOYEE_LOGIN + " (" +
                    EMPLOYEE_LOGIN_COLUMN_ID + " int NOT NULL AUTO_INCREMENT, " +
                    EMPLOYEE_FULL_NAME + " VARCHAR(100) NOT NULL, " +
                    EMPLOYEE_EMAIL + " VARCHAR(50) NOT NULL, " +
                    EMPLOYEE_DESIGNATION + " VARCHAR(50) NOT NULL, " +
                    EMPLOYEE_USER_NAME + " VARCHAR(50) NOT NULL, " +
                    EMPLOYEE_PASSWORD + " VARCHAR(100) NOT NULL, " +
                    "PRIMARY KEY(" + EMPLOYEE_LOGIN_COLUMN_ID + "));";
}