package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class Full_item_recView extends AppCompatActivity {

    private static final String TAG = "Full_item_recView";

    private Books incommingbook;

    private TextView booktext , authortext , descriptiontext , page;
    private ImageView bookimage;
    private Button wantreadbtn , currentreadbtn , alreadyreadbtn;
    private Util util;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_item_rec_view);
        overridePendingTransition(R.anim.in,R.anim.out);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initwigits();

        Intent intent = getIntent();
        int id = intent.getIntExtra("Bookid" , 0);

        util = new Util();

        ArrayList<Books> book = util.getAllbooks();

        for (Books b : book){
            if (b.getId() == id){
                incommingbook = b;
                booktext.setText(b.getname());
                authortext.setText((b.getauthor()));
                descriptiontext.setText(b.getdescription());
                page.setText("pages : " + b.getPages());

                Glide.with(this)
                        .asBitmap()
                        .load(b.getimageUrl())
                        .into(bookimage);

            }
        }

        currentreadbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btncurReadingTaped();
            }
        });

        alreadyreadbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alreadyreadbtntapped();
            }
        });

        wantreadbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wantreadbtnTapped();
            }
        });

    }

    private void btncurReadingTaped(){

        Log.d(TAG, "btncurReadingTaped: Started");

        ArrayList<Books> currentlyReadingBook = util.getCurrentreadingbooks();


        if(currentlyReadingBook.contains(incommingbook)){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Already Added in Current Reading List");
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            builder.setCancelable(true);
            builder.create().show();
        }else{
            ArrayList<Books> alreadyreadbook = util.getAlreadyreadbooks();
            if(alreadyreadbook.contains(incommingbook)){
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("You Want to Read this Book Again");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        util.removealreadyreadbooks(incommingbook);
                        util.addCurrentReadingBooks(incommingbook);
                        Toast.makeText(Full_item_recView.this, incommingbook.getname() + "is Added for Reading One More Time.", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setCancelable(false);
                builder.create().show();
            }
            else{
                ArrayList<Books> wanttoread = util.getWanttoreadbooks();
                if(wanttoread.contains(incommingbook)){
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("You want to Read this Book Now");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            util.removewanttoreadbooks(incommingbook);
                            util.addCurrentReadingBooks(incommingbook);
                            Toast.makeText(Full_item_recView.this, incommingbook.getname() +" Added to your Currently read Book List", Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    builder.setCancelable(false);
                    builder.create().show();
                }else{

                    util.addCurrentReadingBooks(incommingbook);
                    Toast.makeText(this , "Book "+ incommingbook.getname() + "Added to your currently reading list.",Toast.LENGTH_SHORT ).show();

                }

            }

        }
    }

    private void alreadyreadbtntapped(){
        Log.d(TAG, "alreadyreadbtntapped: Started");

        ArrayList<Books> alreadyReadingBook = util.getAlreadyreadbooks();

        if(alreadyReadingBook.contains(incommingbook)){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setMessage("you already add this book to already read book list");
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            builder.setCancelable(true);
            builder.create().show();

        }else{
            ArrayList<Books> currentlyReadBook = util.getCurrentreadingbooks();

            if(currentlyReadBook.contains(incommingbook)){
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Are you Finish This Book");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        util.removeCurrentReadingBooks(incommingbook);
                        util.addalreadyreadbooks(incommingbook);
                        Toast.makeText(Full_item_recView.this,incommingbook.getname() + " is Added to your Already Read book List" , Toast.LENGTH_SHORT).show();

                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                builder.setCancelable(false);
                builder.create().show();

            }else{
                ArrayList<Books> wanttoReadBook = util.getWanttoreadbooks();

                if(wanttoReadBook.contains(incommingbook)) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("Are you Finish this book.");

                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            util.removewanttoreadbooks(incommingbook);
                            util.addalreadyreadbooks(incommingbook);
                            Toast.makeText(Full_item_recView.this,incommingbook.getname() + " is Added to your want to Read book List" , Toast.LENGTH_SHORT).show();

                        }
                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

                    builder.setCancelable(false);
                    builder.create().show();
                }else{

                    util.addalreadyreadbooks(incommingbook);
                    Toast.makeText(this,incommingbook.getname() + " is Added to your Already Read book List" , Toast.LENGTH_SHORT).show();

                }
            }

                    }


    }

    private void wantreadbtnTapped(){
        Log.d(TAG, "wantreadbtnTapped: Started");

        ArrayList<Books> wanttoReadBook = util.getWanttoreadbooks();


        if(wanttoReadBook.contains(incommingbook)){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("you Already added this book to Want to read Book List");
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });

            builder.setCancelable(true);
            builder.create().show();
        }else{
            ArrayList<Books> alreadyreadbook = util.getAlreadyreadbooks();

            if(alreadyreadbook.contains(incommingbook)){
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("You Already Read this book you want to read one more time.");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        util.removealreadyreadbooks(incommingbook);
                        util.addwanttoreadbooks(incommingbook);
                        Toast.makeText(Full_item_recView.this, incommingbook.getname() + "Added for read One more Time.", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setCancelable(false);
                builder.create().show();
            }else{
                ArrayList<Books> currentlyReadBook = util.getCurrentreadingbooks();

                if(currentlyReadBook.contains(incommingbook)){
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("currently reading this book are you want to read after some time.");

                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            util.removeCurrentReadingBooks(incommingbook);
                            util.addwanttoreadbooks(incommingbook);
                            Toast.makeText(Full_item_recView.this, incommingbook.getname() + "Added to Want to add list.", Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

                    builder.setCancelable(true);
                    builder.create().show();

                }
                else{

                    util.addwanttoreadbooks(incommingbook);
                    Toast.makeText(this,incommingbook.getname() + "is Added to your Want to Read List",Toast.LENGTH_SHORT).show();
                }


            }

        }
    }

    public void initwigits(){
        booktext = (TextView) findViewById(R.id.booktext);
        authortext = (TextView) findViewById(R.id.authortext);
        descriptiontext = (TextView) findViewById(R.id.bookdescription);
        page = (TextView) findViewById(R.id.pagestext);

        bookimage = (ImageView) findViewById(R.id.bookimage);

        wantreadbtn = (Button) findViewById(R.id.WantReadbtn);
        currentreadbtn = (Button) findViewById(R.id.CurrntReadbtn);
        alreadyreadbtn = (Button) findViewById(R.id.AlreadyReadbtn);
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