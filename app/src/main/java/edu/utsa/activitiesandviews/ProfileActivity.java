package edu.utsa.activitiesandviews;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.ComponentActivity;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.Scanner;


public class ProfileActivity extends ComponentActivity {

    private Button button;
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

    private void setupButtons(){
        // we named the id of the button "profileB"
        button = findViewById(R.id.exitProfile);

        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                // where am i starting (Main Activity)
                // where am i going (Profile Activity)
                Intent intent = new Intent(ProfileActivity.this, MainViewActivity.class);
                startActivity(intent);
            }

        });
    }

    public void setupProfile() {
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", -1);

        Scanner scan;
        String str = "";
        String[] arr = null;

        try  {
            scan = new Scanner(assets.open("accounts.txt"));
            while(scan.hasNextLine()) {
                str = scan.nextLine();
                arr = str.split(",");
                if (Integer.parseInt(arr[0]) == id) {
                    profileInfo = new Account(id, arr[1], arr[2]);
                    break;
                }
            }
            scan.close();
        }
        catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        TextView name = (TextView) findViewById(R.id.name);
        TextView email = (TextView) findViewById(R.id.email);

        // producing error: "java.lang.NullPointerException: Attempt to invoke virtual method 'java.lang.String edu.utsa.activitiesandviews.Account.getEmail()' on a null object reference"
        // there should be another comment above me
        //name.setText(profileInfo.getName());
        //email.setText(profileInfo.getEmail());
        // another comment

    }

}
