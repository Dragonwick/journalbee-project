package edu.utsa.activitiesandviews;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import androidx.appcompat.app.AppCompatActivity;

public class postsActivity extends AppCompatActivity {
    private EditText locationEditText;
    private EditText descriptionEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.posts);  // Replace 'posts' with the name of your posts.xml layout file

        // initialize EditText fields
        locationEditText = findViewById(R.id.Title);
        descriptionEditText = findViewById(R.id.Description);

        // set onClickListener for the postsCreateButton
        findViewById(R.id.postsCreateButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the text entered by the user
                String location = locationEditText.getText().toString();
                String description = descriptionEditText.getText().toString();

                // Save the inputs to a text file
                saveInputsToFile(location, description);

                // navigate back to mapview.xml
                Intent intent = new Intent(postsActivity.this, MainViewActivity.class);
                startActivity(intent);
            }
        });
    }

    private void saveInputsToFile(String location, String description) {
        // Define the file name and location
        String fileName = "user_inputs.txt";

        // Get the directory where the file will be saved
        File directory = getApplicationContext().getFilesDir();
        File file = new File(directory, fileName);

        try {
            // Create FileWriter with append mode to add new content to the file
            FileWriter fileWriter = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            // Write the inputs to the file
            bufferedWriter.write("Location: " + location);
            bufferedWriter.newLine(); // Add a new line
            bufferedWriter.write("Description: " + description);
            bufferedWriter.newLine(); // Add a new line

            // Close the BufferedWriter
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
