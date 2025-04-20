package com.example.fooddeliveryapp;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private static final int REQUEST_LOCATION_PERMISSION = 1;
    private GoogleMap mMap;
    private LocationCallback mLocationCallback;
    private TextView addressTextView;
    private TextView latitudeTextView;
    private TextView longitudeTextView;
    private Marker mMarker;
    private Location mCurrentLocation;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        addressTextView = findViewById(R.id.addressTextView);
        latitudeTextView = findViewById(R.id.latitudeTextView);
        longitudeTextView = findViewById(R.id.longitudeTextView);
        Button confirmButton = findViewById(R.id.confirmButton);
        ImageView backButton = findViewById(R.id.backButton);
        TextView titleTextView = findViewById(R.id.titleTextView);

        titleTextView.setText("Select Location");

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapFragmentContainer);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);

        backButton.setOnClickListener(v -> finish());

        confirmButton.setOnClickListener(v -> {
            if (mCurrentLocation != null) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("latitude", mCurrentLocation.getLatitude());
                resultIntent.putExtra("longitude", mCurrentLocation.getLongitude());
                resultIntent.putExtra("address", addressTextView.getText().toString());
                setResult(RESULT_OK, resultIntent);
                finish();
            } else {
                Toast.makeText(MapsActivity.this, "Please select a location", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;

        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setMapToolbarEnabled(true);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            getLocation();
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_LOCATION_PERMISSION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_LOCATION_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLocation();
            } else {
                Toast.makeText(this, "Location permission is required to use this feature", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    private void getLocation() {
        LocationRequest mLocationRequest = LocationRequest.create();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(10000); // Update location every 10 seconds
        LocationRequest locationRequest = mLocationRequest.setFastestInterval(5000);

        mLocationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(@NonNull LocationResult locationResult) {
                for (Location location : locationResult.getLocations()) {
                    mCurrentLocation = location;

                    LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());

                    updateMap(latLng);

                    getAddress(location);
                }
            }
        };

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        LocationServices.getFusedLocationProviderClient(this).requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.getMainLooper());

        mMap.setOnMapClickListener(latLng -> {
            mCurrentLocation = new Location("Selected Point");
            mCurrentLocation.setLatitude(latLng.latitude);
            mCurrentLocation.setLongitude(latLng.longitude);

            updateMap(Objects.requireNonNull(latLng));

            getAddress(mCurrentLocation);
        });
    }

    private void updateMap(LatLng latLng) {
        if (mMarker == null) {
            mMarker = mMap.addMarker(new MarkerOptions().position(latLng).title("Selected Location"));
        } else {
            mMarker.setPosition(latLng);
        }
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
    }

    @SuppressLint("SetTextI18n")
    private void getAddress(Location location) {
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(
                    location.getLatitude(), location.getLongitude(), 1);
            if (addresses != null && !addresses.isEmpty()) {
                Address address = addresses.get(0);
                String fullAddress = address.getAddressLine(0);
                addressTextView.setText("Address: " + fullAddress);
                latitudeTextView.setText("Latitude: " + location.getLatitude());
                longitudeTextView.setText("Longitude: " + location.getLongitude());

                findViewById(R.id.locationInfoContainer).setVisibility(View.VISIBLE);
            }
        } catch (IOException e) {
            Log.e("MapsActivity", "Error getting address: " + e.getMessage());
            addressTextView.setText("Address: Not found");
            latitudeTextView.setText("Latitude: " + location.getLatitude());
            longitudeTextView.setText("Longitude: " + location.getLongitude());
            findViewById(R.id.locationInfoContainer).setVisibility(View.VISIBLE);

        }
        LocationServices.getFusedLocationProviderClient(this).removeLocationUpdates(mLocationCallback);
    }
}