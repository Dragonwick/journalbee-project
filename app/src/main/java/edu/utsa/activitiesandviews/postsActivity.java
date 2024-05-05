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
        titleEditText = findViewById(R.id.titleEditText);
        descriptionEditText = findViewById(R.id.descriptionEditText);
        setupButtons();
    }

    public void setupButtons() {
        findViewById(R.id.backButton).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // button going back to previous page
                finish();
            }
        });

        findViewById(R.id.postsCreateButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // button to create a post

                // get the text entered by the user
                String title = titleEditText.getText().toString();
                String description = descriptionEditText.getText().toString();

                // save the inputs to a text file
                //saveInputsToFile(title, description);

                finish();
            }
        });
        // copy file from assets to internal storage
        //copyFileFromAssets("user_inputs.txt");
    }
}
