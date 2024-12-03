package org.example.freightr.TableCreation.ObjectClasses;

/**
 * @description This class has constructors for package tracking objects, along with getters and setters
 */

public class PackageTracking {
    private int trackingId;
    private int packageId;
    private String location;
    private int statusId;

    public PackageTracking(int trackingId, int packageId, String location, int statusId) {
        this.trackingId = trackingId;
        this.packageId = packageId;
        this.location = location;
        this.statusId = statusId;
    }

    public PackageTracking() {
    }

    public int getTrackingId() {
        return trackingId;
    }

    public void setTrackingId(int trackingId) {
        this.trackingId = trackingId;
    }

    public int getPackageId() {
        return packageId;
    }

    public void setPackageId(int packageId) {
        this.packageId = packageId;
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
