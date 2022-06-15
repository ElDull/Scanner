package com.example.scannerapp;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DBActivity extends AppCompatActivity{

    // creating variables for our edittext, button and dbhandler
    private Button addItemBtn;
    TextView tvCode;
    EditText etName;
    EditText etPrice;
    private MainActivity scan;
    private DBHandler dbHandler;
    public String itemName;
    String itemPrice;
    Intent intent;
    String rCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dbactivity);

        // initializing all our variables.

        tvCode = findViewById(R.id.code);
        etName = findViewById(R.id.name);
        etPrice = findViewById(R.id.price);
        etName.setInputType(InputType.TYPE_CLASS_TEXT);
        etPrice.setInputType(InputType.TYPE_CLASS_PHONE);
        addItemBtn = findViewById(R.id.btnAdd);
        itemName =etName.getText().toString();
        itemPrice = etPrice.getText().toString();
        // creating a new dbhandler class
        // and passing our context to it.
        dbHandler = new DBHandler(DBActivity.this);
        intent = getIntent();
        rCode = intent.getStringExtra("variable");
        tvCode.setText(rCode);

        // below line is to add on click listener for our add course button.
        addItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // below line is to get data from all edit text fields.
                itemName = etName.getText().toString();
                itemPrice = etPrice.getText().toString();

                // validating if the text fields are empty or not.
                if (itemName.isEmpty() || itemPrice.isEmpty() || rCode.isEmpty()) {
                    Toast.makeText(DBActivity.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
                    return;
                }
                //TODO IF CODE EXISTS RETRUN THIS ITEM EXISTS
                if (dbHandler.isExist(rCode)){
                    Toast.makeText(DBActivity.this, "This Item already exists..", Toast.LENGTH_SHORT).show();
                    return;
                }

                // on below line we are calling a method to add new
                // course to sqlite data and pass all our values to it.
                else {
                    dbHandler.addNewItem(itemName, itemPrice, rCode);
                }
                    // after adding the data we are displaying a toast message.
                    Toast.makeText(DBActivity.this, "Item has been added.", Toast.LENGTH_SHORT).show();
                    Intent swapAct = new Intent(DBActivity.this,ViewItems.class);
                    startActivity(swapAct);


            }
        });


    }


}