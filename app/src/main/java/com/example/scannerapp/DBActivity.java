package com.example.scannerapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.scannerapp.DBHandler;
import com.example.scannerapp.R;
//TODO extract varialbes from scan to data base
public class DBActivity extends AppCompatActivity {

    // creating variables for our edittext, button and dbhandler
    private Button addItemBtn;
    private DBHandler dbHandler;
    private String itemName,itemPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initializing all our variables.

        addItemBtn = findViewById(R.id.btnAdd);

        // creating a new dbhandler class 
        // and passing our context to it.
        dbHandler = new DBHandler(DBActivity.this);

        // below line is to add on click listener for our add course button.
        addItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // below line is to get data from all edit text fields.


                // validating if the text fields are empty or not.
                if (itemName.isEmpty() && itemPrice.isEmpty()) {
                    Toast.makeText(DBActivity.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
                    return;
                }

                // on below line we are calling a method to add new 
                // course to sqlite data and pass all our values to it.
                dbHandler.addNewItem(itemName, itemPrice);

                // after adding the data we are displaying a toast message.
                Toast.makeText(DBActivity.this, "Course has been added.", Toast.LENGTH_SHORT).show();


            }
        });
    }
}