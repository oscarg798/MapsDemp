package co.m.co.mapsdemo;

import com.google.android.gms.location.LocationRequest;

/**
 * @author Oscar Gallon on 1/22/19.
 */
abstract class BaseLocationProvider {

    LocationRequest getLocationRequest() {
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(5000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        return locationRequest;
    }
}
