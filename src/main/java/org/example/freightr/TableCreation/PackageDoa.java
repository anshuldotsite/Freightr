package org.example.freightr.TableCreation;

import java.util.ArrayList;

public interface PackageDoa {
    public ArrayList<Package> getAllPackages();

    public Package getPackage(int packageId);

    public Package deletePackage(int packageId);
}
