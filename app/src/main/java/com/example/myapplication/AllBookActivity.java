package com.example.myapplication;


import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AllBookActivity extends AppCompatActivity {
    private static final String TAG = "AllBookActivity";

    private RecyclerView booksrecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_book);
        overridePendingTransition(R.anim.in,R.anim.out);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        booksrecyclerView = (RecyclerView) findViewById(R.id.RecyclerView);
        BookrecviewAdapter adapter = new BookrecviewAdapter(this);
        booksrecyclerView.setAdapter(adapter);
        booksrecyclerView.setLayoutManager(new GridLayoutManager(this,2));

        ArrayList<Books> book = new ArrayList<>();
        Util util = new Util();

        book = util.getAllbooks();

        adapter.setBooks(book);
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
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.close_in,R.anim.close_out);
    }
}