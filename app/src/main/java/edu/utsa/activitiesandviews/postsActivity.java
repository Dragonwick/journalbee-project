package edu.utsa.activitiesandviews;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Date;
import android.util.Log;
import android.widget.Toast;





public class postsActivity extends AppCompatActivity {
    private EditText titleEditText, descEditText;
    private Button deleteButton;
    private Journal selectedJournal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.posts);
        setupButtons();
        initWidgets();
        checkForEditJournal();
    }



    private void initWidgets() {
        titleEditText = findViewById(R.id.titleEditText);
        descEditText = findViewById(R.id.descriptionEditText);
        deleteButton = findViewById(R.id.deleteJournalButton);
    }
    private void checkForEditJournal() {
        Intent previousIntent = getIntent();

        int passedJournalID = previousIntent.getIntExtra(Journal.JOURNAL_EDIT_EXTRA,-1);
        selectedJournal = Journal.getJournalForID(passedJournalID);

        if(selectedJournal != null){
            titleEditText.setText(selectedJournal.getTitle());
            descEditText.setText(selectedJournal.getDescription());
        }
        else{
            deleteButton.setVisibility(View.INVISIBLE);
        }


    }

    public void saveJournal(View view){
        SQLiteManager sqLiteManager = SQLiteManager.instanceOfDatabase(this);
        String title = String.valueOf(titleEditText.getText());
        String desc = String.valueOf(descEditText.getText());

        if(selectedJournal == null) {
            int postId = Journal.journalArrayList.size();
            Journal newJournal = new Journal(postId, title, desc);
            Journal.journalArrayList.add(newJournal);
            sqLiteManager.addJournalToDatabase(newJournal);
        }
        else{
            selectedJournal.setTitle(title);
            selectedJournal.setDescription(desc);
            sqLiteManager.updateJournalInDB(selectedJournal);
        }

        finish();

    }
    public void deleteJournal(View view){
        selectedJournal.setDeleted(new Date());
        SQLiteManager sqLiteManager = SQLiteManager.instanceOfDatabase(this);
        sqLiteManager.updateJournalInDB(selectedJournal);
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
