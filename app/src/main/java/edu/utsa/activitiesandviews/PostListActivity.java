package edu.utsa.activitiesandviews;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class PostListActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // set view to postlist
        super.onCreate(savedInstanceState);
        setContentView(R.layout.postlist);

        Intent intentPostList = getIntent();
        int id = intentPostList.getIntExtra("id", -1);


        findViewById(R.id.addPostToJournal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentPost = new Intent(PostListActivity.this, postsActivity.class);
                intentPost.putExtra("id", id);
                startActivity(intentPost);
                Log.d("PostListActivity", "ID passed: " + id);

            }
        });
    }
}