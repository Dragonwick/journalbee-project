package edu.utsa.activitiesandviews;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;

public class MainViewActivity extends AppCompatActivity implements OnMapReadyCallback {

    private ImageButton profileA;
    private ImageButton logoutB;
    private GoogleMap gMap;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mapview);
        setupButtons();

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.id_map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        GoogleMap map = googleMap;
        map.setMapStyle(MapStyleOptions.loadRawResourceStyle(MainViewActivity.this, R.raw.map_style));

        LatLng location = new LatLng(29.5831, -98.6199);
    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15));
    }

    private void setupButtons(){
        // we named the id of the button "profileB"
        profileA = (ImageButton) findViewById(R.id.profileA);
        logoutB = (ImageButton) findViewById(R.id.logoutB);
        profileA.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                // where am i starting (Main Activity)
                // where am i going (Profile Activity)
                Intent intent = new Intent(MainViewActivity.this, ProfileActivity.class);
                startActivity(intent);
            }

        });
        logoutB.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                // where am i starting (Main Activity)
                // where am i going (Profile Activity)
                Intent intent = new Intent(MainViewActivity.this, MainActivity.class);
                startActivity(intent);
            }

        });

    }
}
//this is a test to ensure that my damn git is working :D