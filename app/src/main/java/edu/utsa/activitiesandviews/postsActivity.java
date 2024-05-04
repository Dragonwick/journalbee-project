package edu.utsa.activitiesandviews;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.posts);

        // initialize EditText fields
        titleEditText = findViewById(R.id.Title);
        descriptionEditText = findViewById(R.id.Description);

        // set onClickListener for the postsCreateButton
        findViewById(R.id.postsCreateButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get the text entered by the user
                String title = titleEditText.getText().toString();
                String description = descriptionEditText.getText().toString();

                // save the inputs to a text file
                saveInputsToFile(title, description);

                // navigate back to mapview.xml
                Intent intent = new Intent(postsActivity.this, MainViewActivity.class);
                startActivity(intent);
            }
        });
        // copy file from assets to internal storage
        copyFileFromAssets("user_inputs.txt");
    }

    private void copyFileFromAssets(String filename) {
        try {
            InputStream inputStream = getAssets().open(filename);
            File outFile = new File(getFilesDir(), filename);
            OutputStream outputStream = new FileOutputStream(outFile);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
            outputStream.flush();
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void saveInputsToFile(String title, String description) {
        // define the file name and location
        String fileName = "user_inputs.txt";

        // get the directory where the file is copied
        File file = new File(getFilesDir(), fileName);

        try {
            // create FileWriter with append mode to add new content to the file
            FileWriter fileWriter = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            // write the inputs to the file
            bufferedWriter.write("Title: " + title);
            bufferedWriter.newLine(); // Add a new line
            bufferedWriter.write("Description: " + description);
            bufferedWriter.newLine(); // Add a new line

            // close the BufferedWriter
            bufferedWriter.close();

            Toast.makeText(postsActivity.this, "Details saved successfully", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(postsActivity.this, "Error: Unable to save details", Toast.LENGTH_SHORT).show();
        }
    }
}
