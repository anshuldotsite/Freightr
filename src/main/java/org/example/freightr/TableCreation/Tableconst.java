package org.example.freightr.TableCreation;

public class Tableconst { public static final String TABLE_CUSTOMER = "customer";
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
}