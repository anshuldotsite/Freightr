package org.example.freightr.TableCreation.ObjectClasses;

public class LocationForStats {
    private String location;
    private int packageCount;

    public LocationForStats (String location, int packageCount) {
        this.location = location;
        this.packageCount = packageCount;
    }

    public String getLocation() {
        return location;
    }

    public int getPackageCount() {
        return packageCount;
    }
}

