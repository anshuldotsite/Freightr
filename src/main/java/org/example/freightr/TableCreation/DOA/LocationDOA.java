package org.example.freightr.TableCreation.DOA;

import org.example.freightr.TableCreation.ObjectClasses.LocationForStats;

import java.util.ArrayList;

/**
 * @description This interface has DOA for location
 */
public interface LocationDOA {
    public ArrayList<LocationForStats> getPackagesAtLocations();
}
