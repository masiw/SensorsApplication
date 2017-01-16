package com.woermann.android.sensors;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

/**
 * Created by masi on 16/01/17.
 */

public class SensorManager {

    private static final String locationProvider = LocationManager.GPS_PROVIDER;
    private static final int MY_PERMISSION_ACCESS_FINE_LOCATION = 1;

    private LocationManager locationManager;


    public SensorManager(LocationManager locationManager) {
        this.locationManager = locationManager;
    }

    public boolean requestLocationServicePermissions(Activity requester) {

        if (ActivityCompat.checkSelfPermission(requester, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.i("SensorManager", "Access not yet granted");
            ActivityCompat.requestPermissions(requester,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSION_ACCESS_FINE_LOCATION);

            return false;
        }
        return true;
    }


    public void startLocationService(LocationListener locationListener) {

        try {
            locationManager.requestLocationUpdates(locationProvider,0, 0, locationListener);
        } catch (SecurityException exception) {
            System.out.println("Security Exception: Prevented from requesting current location. "
                    + exception.getMessage());
        }
    }
}
