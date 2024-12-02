package org.example.freightr.TableCreation.ObjectClasses;

import java.util.ArrayList;

public interface PackageTrackAllDOA {
    public ArrayList<PackageCustomTracking> getAllPackageTrackingWithStatus(int statusId);
    public void updatePackage(PackageCustomTracking packageCustomTracking);
    public int getPackageCount(int packageTrack);
}
