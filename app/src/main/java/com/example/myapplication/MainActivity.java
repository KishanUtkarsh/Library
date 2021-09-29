package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private Button allbtn , currentbtn2 , wantbtn3 , alreadybtn4 ,aboutbtn5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialising();
        setOnClickLishner();
    }

    private void setOnClickLishner(){
        allbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this , AllBookActivity.class);
                startActivity(intent);
            }
        });

        currentbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,CurrentlyReadActivity.class);
                startActivity(intent);
            }
        });
        wantbtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this , wantToActivity.class);
                startActivity(intent);
            }
        });
        alreadybtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this , AlreadyReadBookActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initialising(){
        textView = (TextView) findViewById(R.id.AppName);

        allbtn = (Button) findViewById(R.id.Allbookbtn);
        currentbtn2 = (Button) findViewById(R.id.Currentreadingbookbtn);
        wantbtn3 = (Button) findViewById(R.id.wanttoreadthisbookbtn);
        alreadybtn4 = (Button) findViewById(R.id.Alreadyreadbookbtn);
        aboutbtn5 = (Button) findViewById(R.id.Aboutbtn);

    }
}