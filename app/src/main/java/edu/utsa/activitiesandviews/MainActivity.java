package edu.utsa.activitiesandviews;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.ComponentActivity;
import androidx.annotation.Nullable;

public class MainActivity extends ComponentActivity {
    private Button button;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //login is what we called login.xml
        // R is a variable that android has that links to
        // resource folder
        setContentView(R.layout.login);
        setupButtons();
        // lauren was here version 2
        //aaron made another change here
        //luis made this change here
        // nate was here
    }

    private void setupButtons(){
        // we named the id of the button "login"
        button = (Button) findViewById(R.id.login);

        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                // where am i starting (Main Activity)
                // where am i going (Profile Activity)
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(intent);
            }

        });
    }
}
