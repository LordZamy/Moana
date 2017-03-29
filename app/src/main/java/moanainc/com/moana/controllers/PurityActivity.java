package moanainc.com.moana.controllers;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Date;

import moanainc.com.moana.R;
import moanainc.com.moana.models.AccountType;
import moanainc.com.moana.models.Model;
import moanainc.com.moana.models.PurityCondition;
import moanainc.com.moana.models.ReportManager;


/**
 * Created by USER on 3/15/2017.
 */

public class PurityActivity extends AppCompatActivity implements OnMapReadyCallback {


    private EditText _purityReportName;
    private ReportManager _reportManager;
    private final int MY_PERMISSIONS_REQUEST_LOCATION = 1;
    private LatLng currentLocation;
    private GoogleMap mMap;
    private EditText _virusPPM;
    private EditText _contaminationPPM;
    private Spinner _conditionSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purityreport);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map1);
        mapFragment.getMapAsync(this);

        _purityReportName = (EditText) findViewById(R.id.editText6);
        _virusPPM = (EditText) findViewById(R.id.editText7);
        _contaminationPPM = (EditText) findViewById(R.id.editText8);
        _conditionSpinner = (Spinner) findViewById(R.id.spinner5);

        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, PurityCondition.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        _conditionSpinner.setAdapter(adapter);

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            Log.d("PERMS", "NOT GRANTED");
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_LOCATION);
        } else {
            LocationManager locationManager = (LocationManager) this.getSystemService(this.LOCATION_SERVICE);

            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
                Log.d("PERMS", "GRANTED");
                Location lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                Log.d("LOCATION", lastKnownLocation.toString());
                if(lastKnownLocation == null){
                    currentLocation = new LatLng(0, 0);
                } else {
                    currentLocation = new LatLng(lastKnownLocation.getLatitude(), lastKnownLocation.getLongitude());
                }

                if(mMap != null) {
                    onMapReady(mMap);
                }
            }
        }

    }

    public void goToWelcome(View view) {
        Intent goToWelcome = new Intent(getBaseContext(), WelcomeActivity.class);
        getBaseContext().startActivity(goToWelcome);
    }

    public void onSubmitReport(View view){
        String nameInput = _purityReportName.getText().toString();
        int virusInput = Integer.parseInt(_virusPPM.getText().toString());
        int contaminationInput = Integer.parseInt(_contaminationPPM.getText().toString());
        String conditionInput = _conditionSpinner.getSelectedItem().toString().toUpperCase();
        PurityCondition pc = PurityCondition.valueOf(conditionInput);
        Model.getInstance().getCurrentUser().createPurityReport(nameInput, (new Date()).toString(), currentLocation.latitude, currentLocation.longitude,
                PurityCondition.valueOf(conditionInput), virusInput, contaminationInput);


        goToWelcome(null);
        Toast toast = Toast.makeText(getApplicationContext(), "Report Created", Toast.LENGTH_LONG);
        toast.show();
    }


    public void onCancelPressed(View view) {
        Intent goToWelcome = new Intent(getBaseContext(), WelcomeActivity.class);
        getBaseContext().startActivity(goToWelcome);
    }

    private LocationListener locationListener = new LocationListener() {
        public void onLocationChanged(Location location) {
            // Called when a new location is found by the network location provider.
            Log.d("LOCATION", location.toString());
            currentLocation = new LatLng(location.getLatitude(), location.getLongitude());
            onMapReady(mMap);
        }

        public void onStatusChanged(String provider, int status, Bundle extras) {}

        public void onProviderEnabled(String provider) {}

        public void onProviderDisabled(String provider) {}
    };

    private void updateMapLocation(Location location) {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    LocationManager locationManager = (LocationManager) this.getSystemService(this.LOCATION_SERVICE);

                    if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
                        Log.d("PERMS", "GRANTED");
                    }

                } else {
                    Log.d("PERMS", "DENIED");
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        mMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDragStart(Marker marker) {

            }

            @Override
            public void onMarkerDrag(Marker marker) {

            }

            @Override
            public void onMarkerDragEnd(Marker marker) {
                currentLocation = marker.getPosition();
            }
        });

        if(currentLocation != null) {
            mMap.clear();
            mMap.addMarker(new MarkerOptions().position(currentLocation).title("Your current location").snippet("Press and hold to drag").draggable(true));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 10));
        }
    }

}
