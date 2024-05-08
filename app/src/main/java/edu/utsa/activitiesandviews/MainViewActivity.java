package edu.utsa.activitiesandviews;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class MainViewActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {
    private final int FINE_PERMISSION_CODE = 1;
    private Marker marker;
    Location currentLocation;
    FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mapview);
        setupButtons();
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        getCurrentLocation();

    }

    private void getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, FINE_PERMISSION_CODE);
            return;
        }
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>(){
            @Override
            public void onSuccess(Location location) {
                if (location != null){
                    currentLocation = location;
                    SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.id_map);
                    mapFragment.getMapAsync(MainViewActivity.this);
                }
            }
        });
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        GoogleMap myMap = googleMap;
        myMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(MainViewActivity.this, R.raw.map_style));

 //We're gonna be using a set location for the demo as the emulators location is always set to California.
        LatLng myLocation = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
        LatLng location = new LatLng(29.5831, -98.6199);
        LatLng demoPost = new LatLng(29.58, -98.62);
        LatLng demoPost2 = new LatLng(29.59, -98.6211);
        LatLng demoPost3 = new LatLng(29.5835, -98.6189);
       // myMap.addMarker(new MarkerOptions().position(myLocation).title("My Location"));
        myMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 16));
       // myMap.addMarker(new MarkerOptions().position(location).icon(BitmapDescriptorFactory.fromResource(R.drawable.)));

       marker = myMap.addMarker(new MarkerOptions()
                .position(demoPost).title("Demo Post 1")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));
        myMap.addMarker(new MarkerOptions()
                .position(location).title("Demo Post 2")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));
        myMap.addMarker(new MarkerOptions()
                .position(demoPost2).title("Demo Post 3")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));
        myMap.addMarker(new MarkerOptions()
                .position(demoPost3).title("Demo Post 4")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));

        myMap.setOnMarkerClickListener(this);
        myMap.getUiSettings().setZoomGesturesEnabled(false);
        myMap.getUiSettings().setScrollGesturesEnabled(false);

    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == FINE_PERMISSION_CODE){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                getCurrentLocation();
            }else {
                Toast.makeText(this, "Location Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void setupButtons(){
        ImageButton profileA = (ImageButton) findViewById(R.id.profileA);
        ImageButton logoutB = (ImageButton) findViewById(R.id.logoutB);
        ImageButton postButton = (ImageButton) findViewById(R.id.postButton);

        Intent intentMain = getIntent();
        int id = intentMain.getIntExtra("id", -1);

        profileA.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                // right button going to profile page
                Intent intentProfile = new Intent(MainViewActivity.this, ProfileActivity.class);
                intentProfile.putExtra("id", id);
                startActivity(intentProfile);
            }

        });

        logoutB.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                // left button to post list page
                Intent intentPostList = new Intent(MainViewActivity.this, PostListActivity.class);
                intentPostList.putExtra("id", id);
                startActivity(intentPostList);
            }

        });

        postButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // middle button going to new post page
                Intent intentNewPost = new Intent(MainViewActivity.this, postsActivity.class);
                intentNewPost.putExtra("id", id);
                startActivity(intentNewPost);
            }
        });
    }

    @Override
    public boolean onMarkerClick(@NonNull Marker marker) {
        Toast.makeText(this, "DEMO", Toast.LENGTH_SHORT).show();

        return false;
    }
}