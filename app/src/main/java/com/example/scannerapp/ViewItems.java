package com.example.scannerapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ViewItems extends AppCompatActivity  {

    // creating variables for our array list,
    // dbhandler, adapter and recycler view.
    private ArrayList<ItemModal> itemModalArrayList;
    private DBHandler dbHandler;
    private ItemRVAdapter itemsRVAdapter;
    private RecyclerView itemsRV;
    public Button btnMenu;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_item);

        // initializing our all variables.
        itemModalArrayList = new ArrayList<>();
        dbHandler = new DBHandler(ViewItems.this);
        //btnMenu = findViewById(R.id.btnMenu);
       // btnMenu.setOnClickListener(new View.OnClickListener(){

          //  @Override
           // public void onClick(View v) {
               // PopupMenu popupMenu = new PopupMenu(ViewItems.this,v);


            //}
       // });

        // getting our course array
        // list from db handler class.
        itemModalArrayList = dbHandler.readItems();

        // on below line passing our array lost to our adapter class.
        itemsRVAdapter = new ItemRVAdapter(itemModalArrayList, ViewItems.this);
        itemsRV = findViewById(R.id.idRVItems);

        // setting layout manager for our recycler view.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ViewItems.this, RecyclerView.VERTICAL, false);
        itemsRV.setLayoutManager(linearLayoutManager);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(itemsRV);
        // setting our adapter to recycler view.
        itemsRV.setAdapter(itemsRVAdapter);
    }

    ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

            itemModalArrayList.remove(viewHolder.getAdapterPosition());
            dbHandler.deleteItem(viewHolder.getItemId());
            itemsRVAdapter.notifyDataSetChanged();



        }
    };

}