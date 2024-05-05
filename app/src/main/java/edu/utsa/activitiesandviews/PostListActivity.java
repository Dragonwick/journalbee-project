package edu.utsa.activitiesandviews;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class PostListActivity extends AppCompatActivity {
    private ListView journalListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // set view to postlist
        super.onCreate(savedInstanceState);
        setContentView(R.layout.postlist);
        initWidgets();
        loadFromDBToMemory();
        setJournalAdapter();
        setOnClickListener();

        findViewById(R.id.backButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // button back to the main menu
                finish();
            }
        });

       //Intent intentPostList = getIntent();
       //int id = intentPostList.getIntExtra("id", -1);


        /*findViewById(R.id.addPostToJournal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentPost = new Intent(PostListActivity.this, postsActivity.class);
                intentPost.putExtra("id", id);
                startActivity(intentPost);
                Log.d("PostListActivity", "ID passed: " + id);

            }
        });
        */

    }



    private void initWidgets() {
        journalListView=findViewById(R.id.journalListView);
    }

    private void loadFromDBToMemory() {
        SQLiteManager sqLiteManager =SQLiteManager.instanceOfDatabase(this);
        sqLiteManager.populateJournalListArray();
    }
    private void setJournalAdapter() {
        JournalAdapter journalAdapter = new JournalAdapter(getApplicationContext(), Journal.nonDeletedJournals());
        journalListView.setAdapter(journalAdapter);
    }
    private void setOnClickListener() {
        journalListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                Journal selectedJournal = (Journal) journalListView.getItemAtPosition(position);
                Intent editJournalIntent = new Intent(getApplicationContext(), postsActivity.class);
                editJournalIntent.putExtra(Journal.JOURNAL_EDIT_EXTRA, selectedJournal.getId());
                startActivity(editJournalIntent);
            }
        });

    }
    public void newJournal(View view){
        Intent newJournalIntent = new  Intent(this, postsActivity.class);
        startActivity(newJournalIntent);
    }
    @Override
    protected void onResume(){
        super.onResume();
        setJournalAdapter();
    }



}
