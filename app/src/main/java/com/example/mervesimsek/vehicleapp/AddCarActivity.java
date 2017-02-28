package com.example.mervesimsek.vehicleapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

public class AddCarActivity extends AppCompatActivity {
    EditText brand, model, type, modelyear, color, plate, nickname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);
        Toolbar toolbar2 = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar2);

        //previous işlemi buradan yapılmaktadır.
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar2.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentback = new Intent(AddCarActivity.this, MainActivity.class);
                startActivity(intentback);
                finish();
            }
        });



        brand = (EditText)findViewById(R.id.brand);
        model = (EditText)findViewById(R.id.model);
        type =(EditText)findViewById(R.id.type);
        modelyear = (EditText)findViewById(R.id.modelyear);
        color =(EditText)findViewById(R.id.color);
        plate = (EditText)findViewById(R.id.plate);
        nickname =(EditText)findViewById(R.id.nickname);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabsave);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

            }

    }


