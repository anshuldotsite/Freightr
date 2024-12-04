package org.example.freightr.TableCreation.DOA;

import org.example.freightr.TableCreation.ObjectClasses.PackageCustomTracking;

import java.util.ArrayList;

/**
 * @description This class has constructors for tracking all package objects, along with getters and setters
 */

public interface PackageTrackAllDOA {
    public ArrayList<PackageCustomTracking> getAllPackageTrackingWithStatus(int statusId);
    public void updatePackage(PackageCustomTracking packageCustomTracking);
    public int getPackageCount(int packageTrack);
}
