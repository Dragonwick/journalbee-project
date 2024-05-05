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



public class postsActivity extends AppCompatActivity {
    private EditText titleEditText, descEditText;
    //private ListView noteListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.posts);
        setupButtons();
        initWidgets();
    }

    private void initWidgets() {
        titleEditText = findViewById(R.id.titleEditText);
        descEditText = findViewById(R.id.descriptionEditText);
    }

    public void saveJournal(View view){
        String title = String.valueOf(titleEditText.getText());
        String desc = String.valueOf(descEditText.getText());

        int postId = Journal.journalArrayList.size();
        Journal newJournal = new Journal(postId, title,desc);
        Journal.journalArrayList.add(newJournal);
        finish();

    }

    public void setupButtons() {
        findViewById(R.id.backButton).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // button going back to previous page
                finish();
            }
        });


    }
}
