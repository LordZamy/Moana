package moanainc.com.moana.controller;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Date;

import moanainc.com.moana.R;
import moanainc.com.moana.model.report.AvailReport;
import moanainc.com.moana.model.Model;

public class AvailabilityActivity extends AppCompatActivity implements OnMapReadyCallback {

    private EditText _reportName;
    private Spinner _statusSpinner;
    private final int MY_PERMISSIONS_REQUEST_LOCATION = 1;
    private LatLng currentLocation;
    private GoogleMap mMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        _reportName = (EditText) findViewById(R.id.editText5);
        _statusSpinner = (Spinner) findViewById(R.id.spinner2);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, AvailReport.legalStatus);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        _statusSpinner.setAdapter(adapter);


        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            Log.d("PERMS", "NOT GRANTED");
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_LOCATION);
        } else {
            LocationManager locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);

            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
                Log.d("PERMS", "GRANTED");
                Location lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                if(lastKnownLocation == null){
                    currentLocation = new LatLng(0, 0);
                } else {
                    Log.d("LOCATION", lastKnownLocation.toString());
                    currentLocation = new LatLng(lastKnownLocation.getLatitude(), lastKnownLocation.getLongitude());
                }
                if (mMap != null) {
                    onMapReady(mMap);
                }
            }
        }
    }

    private void goToWelcome() {
        Intent goToWelcome = new Intent(getBaseContext(), WelcomeActivity.class);
        getBaseContext().startActivity(goToWelcome);
    }

    public void onSubmitReport(View view){
        if (_reportName.getText().toString().equals("")) {
            Toast toast = Toast.makeText(getApplicationContext(), "Please give the report a name.", Toast.LENGTH_LONG);
            toast.show();
        } else {
            String nameInput = _reportName.getText().toString();
            String statusInput = _statusSpinner.getSelectedItem().toString();
            Model.getInstance().getCurrentUser().createAvailReport(nameInput, new Date(), currentLocation.latitude, currentLocation.longitude, statusInput);

            goToWelcome();
            Toast toast = Toast.makeText(getApplicationContext(), "Report Created", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    public void onCancelPressed(View view) {
        Intent goToWelcome = new Intent(getBaseContext(), WelcomeActivity.class);
        getBaseContext().startActivity(goToWelcome);
    }

    private final LocationListener locationListener = new LocationListener() {
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

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    LocationManager locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);

                    if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
                        Log.d("PERMS", "GRANTED");
                    }

                } else {
                    Log.d("PERMS", "DENIED");
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
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
            mMap.addMarker(new MarkerOptions().position(currentLocation)
                    .title("Your current location")
                    .snippet("Press and hold to drag")
                    .draggable(true)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 10));
        }
    }

}
