package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class BookrecviewAdapter extends RecyclerView.Adapter<BookrecviewAdapter.ViewHolder> {

    private ArrayList<Books> books = new ArrayList<>();


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

        Glide.with(context)
                .asBitmap()
                .load(books.get(position).getimageUrl())
                .into(holder.bookimage);

    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    private static final String TAG = "BookrecviewAdapter";
    private Context context;

    public BookrecviewAdapter(Context context) {
        this.context = context;
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
}
