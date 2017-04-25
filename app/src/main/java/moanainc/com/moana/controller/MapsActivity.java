package moanainc.com.moana.controller;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;
import moanainc.com.moana.R;
import moanainc.com.moana.firebase.FirebaseInterface;
import moanainc.com.moana.model.Report;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ChildEventListener listener = new ChildEventListener() {
        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String s) {}

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(MapsActivity.this);
            Toast.makeText(getApplicationContext(), "Map updated!", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {}

        @Override
        public void onChildMoved(DataSnapshot dataSnapshot, String s) {}

        @Override
        public void onCancelled(DatabaseError databaseError) {}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        FirebaseInterface.addListenerOnReports(listener);

        if (FirebaseInterface.getReportError()) {
            Toast.makeText(getApplicationContext(), "Some reports could not be processed. Please contact an Admin", Toast.LENGTH_LONG).show();
        }
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        ArrayList<Report> availReports = FirebaseInterface.getAvailabilityReports();
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        for (Report report : availReports) {
            googleMap.addMarker(new MarkerOptions().position(new LatLng(report.getLat(), report.getLng()))
                    .title(report.getName())
                    .snippet(report.toString())
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)));
            builder.include(new LatLng(report.getLat(), report.getLng()));
        }

        ArrayList<Report> purityReports = FirebaseInterface.getPurityReports();
        for (Report report : purityReports) {
            googleMap.addMarker(new MarkerOptions().position(new LatLng(report.getLat(), report.getLng()))
                    .title(report.getName())
                    .snippet(report.toString())
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));
            builder.include(new LatLng(report.getLat(), report.getLng()));
        }

        ArrayList<Report> historyReports = FirebaseInterface.getHistoryReports();
        for (Report report : historyReports) {
            googleMap.addMarker(new MarkerOptions().position(new LatLng(report.getLat(), report.getLng()))
                    .title(report.getName())
                    .snippet(report.toString())
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));
            builder.include(new LatLng(report.getLat(), report.getLng()));
        }
        ArrayList<Report> sourceReports = FirebaseInterface.getSourceReports();
        for (Report report : sourceReports) {
            googleMap.addMarker(new MarkerOptions().position(new LatLng(report.getLat(), report.getLng()))
                    .title(report.getName())
                    .snippet(report.toString())
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
            builder.include(new LatLng(report.getLat(), report.getLng()));
        }

        if(!FirebaseInterface.getAllReports().isEmpty()) {
            LatLngBounds bounds = builder.build();
            int width = getResources().getDisplayMetrics().widthPixels;
            int height = getResources().getDisplayMetrics().heightPixels;
            int padding = (int) (width * 0.12);
            googleMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds,  width, height, padding));
        }
    }

    public void onBackButton(View view) {
        FirebaseInterface.removeListenerOnReports(listener);
        Intent goToWelcome = new Intent(getBaseContext(), WelcomeActivity.class);
        getBaseContext().startActivity(goToWelcome);
    }

    @Override
    public void onBackPressed() {
        FirebaseInterface.removeListenerOnReports(listener);
        Intent goToWelcome = new Intent(getBaseContext(), WelcomeActivity.class);
        getBaseContext().startActivity(goToWelcome);
    }
}
