package com.example.scannerapp;

import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.motion.widget.OnSwipe;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ItemRVAdapter extends RecyclerView.Adapter<ItemRVAdapter.ViewHolder> {

    // variable for our array list and context
    private ArrayList<ItemModal> itemModalArrayList;
    private Context context;


    // constructor
    public ItemRVAdapter(ArrayList<ItemModal> itemModalArrayList, Context context) {
        this.itemModalArrayList = itemModalArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // on below line we are inflating our layout
        // file for our recycler view items.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_show_db, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // on below line we are setting data
        // to our views of recycler view item.
        ItemModal modal = itemModalArrayList.get(position);
        holder.itemNameTV.setText(modal.getitemName());
        holder.itemPriceTV.setText(modal.getItemPrice());
        holder.itemCodeTV.setText(modal.getItemCode());

    }

    @Override
    public int getItemCount() {
        // returning the size of our array list
        return itemModalArrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        // creating variables for our text views.
        private TextView itemNameTV, itemPriceTV, itemCodeTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            int pos = 0;
            // initializing our text views
            itemNameTV = itemView.findViewById(R.id.idTVItemName);
            itemPriceTV = itemView.findViewById(R.id.idTVItemPrice);
            itemCodeTV = itemView.findViewById(R.id.idTVItemCode);
            itemView.setTag(pos);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {

            Toast.makeText(view.getContext(),itemCodeTV.getText(),Toast.LENGTH_SHORT).show();
        }
    }

}
