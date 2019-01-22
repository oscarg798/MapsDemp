package co.m.co.mapsdemo;

import android.app.Activity;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

interface ILocationSettingCallback {

    void onLocationSettingsSuccessResponse();

    void onLocationSettingsFailure(boolean resolvable, Exception e);
}

/**
 * @author Oscar Gallon on 1/22/19.
 */
public class LocationSettingsProvider extends BaseLocationProvider {


    public final static LocationSettingsProvider instance = new LocationSettingsProvider();


    private LocationSettingsProvider(){

    }

    public void checkLocationUpdates(Activity activity,
            final ILocationSettingCallback locationSettingCallback) {


        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(getLocationRequest());

        SettingsClient client = LocationServices.getSettingsClient(activity);
        Task<LocationSettingsResponse> task = client.checkLocationSettings(builder.build());

        task.addOnSuccessListener(activity, new OnSuccessListener<LocationSettingsResponse>() {
            @Override
            public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
                locationSettingCallback.onLocationSettingsSuccessResponse();
            }
        });

        task.addOnFailureListener(activity, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                locationSettingCallback.onLocationSettingsFailure(
                        e instanceof ResolvableApiException, e);
            }
        });
    }
}
