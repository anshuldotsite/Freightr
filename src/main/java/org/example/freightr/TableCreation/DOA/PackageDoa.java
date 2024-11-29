package org.example.freightr.TableCreation.DOA;

import org.example.freightr.TableCreation.ObjectClasses.Package;

import java.util.ArrayList;

public interface PackageDoa {
    public ArrayList<Package> getAllPackages();

    public Package getPackage(int packageId);

    public void deletePackage(int packageId);

    int addPackage(Package Package);
}
