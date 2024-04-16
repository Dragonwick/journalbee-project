package edu.utsa.activitiesandviews;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.ComponentActivity;


public class ProfileActivity extends ComponentActivity {

    private Button button;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        setupButtons();
    }

    private void setupButtons(){
        // we named the id of the button "profileB"
        button = (Button) findViewById(R.id.exitProfile);

        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                // where am i starting (Main Activity)
                // where am i going (Profile Activity)
                Intent intent = new Intent(ProfileActivity.this, MainViewActivity.class);
                startActivity(intent);
            }

        });
    }
}
