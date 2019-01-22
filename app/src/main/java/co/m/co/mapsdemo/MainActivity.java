package co.m.co.mapsdemo;

import android.content.Intent;
import android.content.IntentSender;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override protected void onResume() {
        super.onResume();
        LocationSettingsProvider.instance.checkLocationUpdates(this,
                new ILocationSettingCallback() {
                    @Override public void onLocationSettingsSuccessResponse() {
                        getLocation();
                    }

                    @Override
                    public void onLocationSettingsFailure(boolean resolvable, Exception e) {
                        try {
                            // Show the dialog by calling startResolutionForResult(),
                            // and check the result in onActivityResult().
                            ResolvableApiException res = (ResolvableApiException) e;
                            res.startResolutionForResult(MainActivity.this,
                                    101);
                        } catch (IntentSender.SendIntentException sendEx) {
                            // Ignore the error.
                        }
                    }
                });
    }

    private void getLocation() {
        LocationProvider.instance.getLocationUpdates(this, new LocationCallback() {
            @Override public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);
                if (locationResult == null) return;

                for (Location location : locationResult.getLocations()) {
                    Log.i("Location", location.getLatitude() + " " + location.getLongitude());
                }
            }
        });
    }

    @Override protected void onStop() {
        super.onStop();
        LocationProvider.instance.stopLocationUpdates();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 101){
            if(resultCode == RESULT_OK){
                getLocation();
            }else{
                //ERROR
            }
        }
    }
}
