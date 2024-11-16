package org.example.freightr.TableCreation.DOA;

import org.example.freightr.TableCreation.Package;

import java.util.ArrayList;

public interface PackageDoa {
    public ArrayList<Package> getAllPackages();

    public Package getPackage(int packageId);

    public Package deletePackage(int packageId);
}
