package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class BookrecviewAdapter extends RecyclerView.Adapter<BookrecviewAdapter.ViewHolder> {

    private ArrayList<Books> books = new ArrayList<>();
    private static final String TAG = "BookrecviewAdapter";
    private Context context;
    private String type = " ";
    private Util util;


    @NonNull
    @Override
    public BookrecviewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_rec_view , parent , false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Log.e(TAG, "onBindViewHolder: called");
        holder.booktext.setText(books.get(position).getname());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Full_item_recView.class);
                intent.putExtra("Bookid" , books.get(position).getId());
                context.startActivity(intent);
            }
        });

        holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                final Books curbook = books.get(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(context)
                        .setTitle("Delete" + curbook.getname())
                        .setMessage("You want ot Delete")
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                switch (type){
                    case "Current Read Book":
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(util.removeCurrentReadingBooks(books.get(position))){
                                    notifyDataSetChanged();
                                    Toast.makeText(context, curbook.getname()+ " Deleted Sucessfully.",Toast.LENGTH_SHORT).show();
                                }
                            }
                        }).create().show();

                        break;
                    case "Want to Read Book":
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(util.removewanttoreadbooks(books.get(position))){
                                    notifyDataSetChanged();
                                    Toast.makeText(context, curbook.getname()+ " Deleted Sucessfully.",Toast.LENGTH_SHORT).show();
                                }
                            }
                        }).create().show();

                        break;
                    case "Already Read Book":
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(util.removealreadyreadbooks(books.get(position))){
                                    notifyDataSetChanged();
                                    Toast.makeText(context, curbook.getname()+ " Deleted Sucessfully.",Toast.LENGTH_SHORT).show();
                                }
                            }
                        }).create().show();

                        break;
                    default:
                        break;
                }
                return  true;
            }
        });

        Glide.with(context)
                .asBitmap()
                .load(books.get(position).getimageUrl())
                .into(holder.bookimage);

    }

    @Override
    public int getItemCount() {
        return books.size();
    }



    public BookrecviewAdapter(Context context) {
        this.context = context;
        util = new Util();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView bookimage;
        private TextView booktext;

        private CardView cardView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            bookimage = (ImageView) itemView.findViewById(R.id.bookimage);
            booktext = (TextView) itemView.findViewById(R.id.booktext);

            cardView = (CardView) itemView.findViewById(R.id.cardview);
        }

    }

    public void setBooks(ArrayList<Books> books) {
        this.books = books;
        notifyDataSetChanged();
    }

    public void setType(String type){
        this.type = type;
    }
}
