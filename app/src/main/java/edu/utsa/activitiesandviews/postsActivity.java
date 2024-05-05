package edu.utsa.activitiesandviews;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class postsActivity extends AppCompatActivity {
    private EditText titleEditText;
    private EditText descriptionEditText;
    private ListView noteListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.posts);


        // initialize EditText fields
        titleEditText = findViewById(R.id.Title);
        descriptionEditText = findViewById(R.id.Description);


        Intent intentHome = getIntent();
        int id = intentHome.getIntExtra("id", -1);
        Log.d("postsActivity", "ID received: " + id);


        findViewById(R.id.backButton).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intentBack = new Intent(postsActivity.this, MainViewActivity.class);
                intentBack.putExtra("id", id);
                startActivity(intentBack);
            }
        });

        // set onClickListener for the postsCreateButton
        findViewById(R.id.postsCreateButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get the text entered by the user
                String title = titleEditText.getText().toString();
                String description = descriptionEditText.getText().toString();

                // save the inputs to a text file
                //saveInputsToFile(title, description);

                // navigate back to mapview.xml

                Intent intentBackToMap = new Intent(postsActivity.this, MainViewActivity.class);
                intentBackToMap.putExtra("id", -1);
                startActivity(intentBackToMap);
            }
        });
        // copy file from assets to internal storage
        //copyFileFromAssets("user_inputs.txt");
    }



}
