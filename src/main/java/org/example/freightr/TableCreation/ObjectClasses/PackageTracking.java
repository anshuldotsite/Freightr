package org.example.freightr.TableCreation.ObjectClasses;
public class PackageTracking {
    private int trackingId;
    private int packageId;
    private String location;
    private String status;

    public PackageTracking(int trackingId, int packageId, String location, String status) {
        this.trackingId = trackingId;
        this.packageId = packageId;
        this.location = location;
        this.status = status;
    }

    public PackageTracking(){

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
