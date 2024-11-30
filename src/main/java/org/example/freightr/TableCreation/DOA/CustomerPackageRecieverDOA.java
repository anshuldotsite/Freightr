package org.example.freightr.TableCreation.DOA;

import org.example.freightr.TableCreation.ObjectClasses.CustomerPackageReceiver;

import java.util.ArrayList;

/**
 * @description Data Access Object interface for CustomerPackageReceiver table operations
 */
public interface CustomerPackageRecieverDOA {


    void addCustomerPackageReceiver(CustomerPackageReceiver customerPackageReceiver);


    void deleteCustomerPackageReceiver(int packageId);


    ArrayList<Integer> getPackageIdsByCustomerId(int customerId);


    ArrayList<Integer> getReceiverIdsByCustomerId(int customerId);

    // Method to get a customerId from a packageId
    int getCustomerIdByPackageId(int packageId);
    int getReciverIdByPackageId(int packageId);

    // Method to get all CustomerPackageReceiver records
    ArrayList<CustomerPackageReceiver> getAllCustomerPackageReceivers();
}
