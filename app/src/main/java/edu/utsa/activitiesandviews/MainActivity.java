package edu.utsa.activitiesandviews;

import static java.sql.DriverManager.println;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.ComponentActivity;
import androidx.annotation.Nullable;

import java.io.IOException;
import java.util.Scanner;

public class MainActivity extends ComponentActivity {
    private Button button;
    private AssetManager assets;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        assets = getAssets();
        setupButtons();
    }

    private void setupButtons(){
        button = (Button) findViewById(R.id.login);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText uText = (EditText)findViewById(R.id.inputName);
                EditText pText = (EditText)findViewById(R.id.inputPassword);
                int id = authenticate(uText.getText().toString(), pText.getText().toString());
                if(id > 0) {
                    Intent intent = new Intent(MainActivity.this, MainViewActivity.class);
                    intent.putExtra("id",id);
                    startActivity(intent);
                }
                else{
                    uText.setText("");
                    pText.setText("");
                    uText.setError("Incorrect username or password");
                    pText.setError("Incorrect username or password");
                }
            }
        });
    }

    private int authenticate(String username, String password) {
        Scanner scan;
        String str = "";
        String[] arr = null;
        boolean auth = false;
        int id = -1;
        try {
            scan = new Scanner(this.assets.open("login.txt"));
            while (scan.hasNext()) {
                str = scan.nextLine();
                arr = str.split(",");
                if (username.equalsIgnoreCase(arr[1]) && password.equals(arr[2])) {
                    auth = true;
                    id = Integer.parseInt(arr[0]);
                    break;
                }
            }
            scan.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }


        return id;

    }
}

