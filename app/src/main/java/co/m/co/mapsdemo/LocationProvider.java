package co.m.co.mapsdemo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.location.Location;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

/**
 * @author Oscar Gallon on 1/22/19.
 */
public class LocationProvider extends BaseLocationProvider {

    private FusedLocationProviderClient mFusedLocationProviderClient;

    private FusedLocationProviderClient client;

    private LocationCallback mLocationCallback;

    public final static LocationProvider instance = new LocationProvider();

    private LocationProvider() {

    }

    @SuppressLint("MissingPermission")
    public void getLocationUpdates(Activity activity, LocationCallback locationCallback) {
        mLocationCallback = locationCallback;

        client = LocationServices.getFusedLocationProviderClient(activity);

        client.requestLocationUpdates(getLocationRequest(), locationCallback, null);
    }

    public void stopLocationUpdates() {
        if (client == null) return;

        if (mLocationCallback == null) return;

        client.removeLocationUpdates(mLocationCallback);
        mLocationCallback = null;
    }
}
