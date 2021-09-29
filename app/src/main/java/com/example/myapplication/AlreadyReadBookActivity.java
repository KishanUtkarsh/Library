package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

public class AlreadyReadBookActivity extends AppCompatActivity {
    private static final String TAG = "AlreadyReadBookActivity";

    private RecyclerView recyclerView;
    private BookrecviewAdapter adapter;
    private Util util;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_already_read_book);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        util = new Util();

        recyclerView = (RecyclerView) findViewById(R.id.AlreadyReadRecView);
        adapter = new BookrecviewAdapter(this);
        recyclerView.setAdapter(adapter);
        adapter.setBooks(util.getAlreadyreadbooks());

        recyclerView.setLayoutManager(new GridLayoutManager(this,2));

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                super.onBackPressed();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}