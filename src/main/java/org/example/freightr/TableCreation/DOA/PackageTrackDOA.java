package org.example.freightr.TableCreation.DOA;

import org.example.freightr.TableCreation.ObjectClasses.PackageTracking;

import java.util.ArrayList;

/**
 * @author Kautuk Prasad
 * @description Package Track Table DOA
 */
public interface PackageTrackDOA {
    public void addPackageTracking(PackageTracking packageTracking);
    PackageTracking getPackageTracking(int trackingID);
    public ArrayList<PackageTracking> getAllPackageTrackingWithStatus(int StatusID);
    public ArrayList<PackageTracking> getAllPackageTracking();
    public void updatePackageTracking(PackageTracking packageTracking);
    public void deletePackageTrack(int packageID);
}
