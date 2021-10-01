package com.example.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private TextView textView;
    private Button allbtn , currentbtn2 , wantbtn3 , alreadybtn4 ,aboutbtn5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        overridePendingTransition(R.anim.in,R.anim.out);

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

        aboutbtn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AboutbtnTapped();
            }
        });
    }
    private void AboutbtnTapped(){
        Log.e(TAG, "AboutbtnTapped: Started");
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle(" About : ")
                .setMessage("Build And Published By Kishan Kumar \n" +
                        "if you Want ot hire me\n" +
                        "If your want to check my Other Work\n" +
                        "Tap yes to Visit my Github Account")
                .setPositiveButton("visit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(MainActivity.this , WebViewActivity.class);
                        intent.putExtra("url" , "https://github.com/KishanUtkarsh");
                        startActivity(intent);
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        builder.create().show();

    }

    private void initialising(){
        textView = (TextView) findViewById(R.id.AppName);

        allbtn = (Button) findViewById(R.id.Allbookbtn);
        currentbtn2 = (Button) findViewById(R.id.Currentreadingbookbtn);
        wantbtn3 = (Button) findViewById(R.id.wanttoreadthisbookbtn);
        alreadybtn4 = (Button) findViewById(R.id.Alreadyreadbookbtn);
        aboutbtn5 = (Button) findViewById(R.id.Aboutbtn);

    }
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.close_in,R.anim.close_out);
    }
}