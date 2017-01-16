package com.example.sensorsapplication;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.woermann.android.sensors.SensorManager;

public class MainActivity extends AppCompatActivity
    implements ActivityCompat.OnRequestPermissionsResultCallback, LocationListener {

    private SensorManager sensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorManager = new SensorManager((LocationManager) this.getSystemService(LOCATION_SERVICE));
        if (sensorManager.requestLocationServicePermissions(this)) {
            sensorManager.startLocationService(this);
        }
    }


    @Override
    public void onRequestPermissionsResult(
            int requestCode,
            String[] permissions,
            int[] grantResults) {

        for (int i = 0; i < permissions.length; i++) {
            if (permissions[i].equals(Manifest.permission.ACCESS_FINE_LOCATION)) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    sensorManager.startLocationService(this);
                    continue;
                }
            }
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.i(MainActivity.class.getCanonicalName(), "Location Changed");
        Log.d(MainActivity.class.getCanonicalName(),
                "latitude: " + Double.toString(location.getLatitude()));
        Log.d(MainActivity.class.getCanonicalName(),
                "longitute: " + Double.toString(location.getLongitude()));
        Log.d(MainActivity.class.getCanonicalName(),
                "accuracy: " + Float.toString(location.getAccuracy()));
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
