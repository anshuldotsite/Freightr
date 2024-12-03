package org.example.freightr.TableCreation.ObjectClasses;

/**
 * @description This class has constructors for creating location stat objects, along with getters and setters
 */
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

