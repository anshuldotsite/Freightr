package org.example.freightr.TableCreation.ObjectClasses;

import java.util.Date;

/**
 * @description Customer Package Receiver Object Class
 */
public class CustomerPackageReceiver {
    private int customerId;
    private int packageId;
    private int receiverId;

    // Constructor
    public CustomerPackageReceiver(int customerId, int packageId, int receiverId) {
        this.customerId = customerId;
        this.packageId = packageId;
        this.receiverId = receiverId;
    }

    // Getters and Setters
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getPackageId() {
        return packageId;
    }

    public void setPackageId(int packageId) {
        this.packageId = packageId;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

}
