package edu.utsa.activitiesandviews;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.ComponentActivity;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.Scanner;


public class ProfileActivity extends ComponentActivity {
    private Account profileInfo;
    private AssetManager assets;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        assets = getAssets();
        setupProfile();
        setupButtons();
    }

    private void setupButtons() {
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", -1);

        Button backButton = findViewById(R.id.exitProfile);
        Button logoutButton = findViewById(R.id.logoutButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // button going back to main menu
                finish();
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLogout = new Intent(ProfileActivity.this, MainActivity.class);
                startActivity(intentLogout);
            }
        });
    }

    public void setupProfile() {
        Scanner scan;
        String str = "";
        String[] arr = null;
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", -1);

        try {
            scan = new Scanner(assets.open("accounts.txt"));
            while (scan.hasNextLine()) {
                str = scan.nextLine();
                arr = str.split(",");
                if (Integer.parseInt(arr[0]) == id) {
                    profileInfo = new Account(id, arr[1], arr[2], arr[3], arr[4], arr[5]);
                    break;
                }
            }
            scan.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        TextView name = (TextView) findViewById(R.id.name);
        TextView email = (TextView) findViewById(R.id.email);
        TextView age = (TextView) findViewById(R.id.age);
        TextView displayedUname = (TextView) findViewById(R.id.profileUsername);
        ImageView profilePic = (ImageView) findViewById(R.id.profilepic);

        name.setText(profileInfo.getName());
        email.setText(profileInfo.getEmail());
        age.setText(profileInfo.getAge());
        displayedUname.setText(profileInfo.getDisplayedUname());

        // extracting resource ID from profilePic string
        String profilePicResourceName = profileInfo.getProfilePic();
        int resourceId = getResources().getIdentifier(profilePicResourceName, "drawable", getPackageName());

        // Set profile picture
        if (resourceId != 0) { // Check if resource ID is valid
            profilePic.setImageResource(resourceId);
        } else {
            // Handle case where resource ID is not found
            // You can set a default image or display an error message
        }
    }
}
