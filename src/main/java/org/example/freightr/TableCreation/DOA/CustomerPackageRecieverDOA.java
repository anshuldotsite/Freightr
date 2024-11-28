
package org.example.freightr.TableCreation.DOA;

import org.example.freightr.TableCreation.ObjectClasses.CustomerPackageReceiver;

import java.util.ArrayList;

/**
 * @author Kohinoor jeet singh
 * @description CustomerPackage Table DAO
 */

public interface CustomerPackageRecieverDOA {

    void addCustomerPackageReceiver(CustomerPackageReceiver customerPackageReceiver);

    void deleteCustomerPackageReceiver(int packageId);
    ArrayList<Integer> getPackageIdsByCustomerId(int customerId);
}
