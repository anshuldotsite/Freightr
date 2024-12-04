package org.example.freightr.TableCreation.ObjectClasses;

import java.util.Date;

/**
 * @description This class has constructors for creating package tracking objects, along with getters and setters
 */

public class PackageCustomTracking {
    private int packageId;
    private String description;
    private Date sentDate;
    private int trackingId;
    private String location;
    private int statusId;

    public PackageCustomTracking(int packageId, String description, Date sentDate, int trackingId, String location, int statusId) {
        this.packageId = packageId;
        this.description = description;
        this.sentDate = sentDate;
        this.trackingId = trackingId;
        this.location = location;
        this.statusId = statusId;
    }

    // Getters and Setters
    public int getPackageId() {
        return packageId;
    }

    public void setPackageId(int packageId) {
        this.packageId = packageId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getSentDate() {
        return sentDate;
    }

    public void setSentDate(Date sentDate) {
        this.sentDate = sentDate;
    }

    public int getTrackingId() {
        return trackingId;
    }

    public void setTrackingId(int trackingId) {
        this.trackingId = trackingId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }
}
