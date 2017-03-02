package com.example.mervesimsek.vehicleapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class AddCarActivity extends AppCompatActivity {
    EditText brand, model, type, modelyear, color, plate, nickname;
    private Database vehicle;
   // TextView showdata =(TextView)findViewById(R.id.showrecord);

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


        vehicle = new Database(this);

        brand = (EditText)findViewById(R.id.brand);
        model = (EditText)findViewById(R.id.model);
        type =(EditText)findViewById(R.id.type);
        modelyear = (EditText)findViewById(R.id.modelyear);
        color =(EditText)findViewById(R.id.color);
        plate = (EditText)findViewById(R.id.plate);
        nickname =(EditText)findViewById(R.id.nickname);

      //  final Bundle bundle = new Bundle();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabsave);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                saveRecord(brand.getText().toString(),
                           model.getText().toString(),
                           type.getText().toString(),
                           modelyear.getText().toString(),
                           color.getText().toString(),
                           plate.getText().toString(),
                           nickname.getText().toString()
                        );
                    Cursor cursor = getRecord();
                    showRecord(cursor);
                    }
                finally {
                    vehicle.close();
                }
               Intent intent = new Intent(AddCarActivity.this, ItemListActivity.class);
              /*   String data = brand.getText().toString();
                bundle.putString("dataitem", data);
                intent.putExtras(bundle);
                showdata.setText(brand.getText());*/
                startActivity(intent);
                }
        });
    }

    private void saveRecord(String brand, String model, String type, String modelyear, String color, String plate, String nickname) {
        SQLiteDatabase db = vehicle.getWritableDatabase();
        ContentValues data = new ContentValues();
        data.put("brand", brand);
        data.put("model", model);
        data.put("type", type);
        data.put("modelyear",modelyear);
        data.put("color", color);
        data.put("plate", plate);
        data.put("nickname", nickname);
        db.insertOrThrow("vehicles",null,data);
    }

    private String[] SELECT = {"id,brand,model,type,modelyear,color,plate,nickname"};




    private Cursor getRecord(){
        SQLiteDatabase db = vehicle.getReadableDatabase();
        Cursor cursor = db.query("vehicles",SELECT,null,null,null,null,null,null);
        startManagingCursor(cursor);
        return(cursor);
    }

    private void showRecord(Cursor cursor){
        StringBuilder builder = new StringBuilder("Vehicles: \n" );

        while (cursor.moveToNext()){
            long id = cursor.getLong(cursor.getColumnIndex("id"));
            String brand = cursor.getString(cursor.getColumnIndex("brand"));
            String model = cursor.getString(cursor.getColumnIndex("model"));
            String type = cursor.getString(cursor.getColumnIndex("type"));
            String modelyear = cursor.getString(cursor.getColumnIndex("modelyear"));
            String color = cursor.getString(cursor.getColumnIndex("color"));
            String plate = cursor.getString(cursor.getColumnIndex("plate"));
            String nickname = cursor.getString(cursor.getColumnIndex("nickname"));


            builder.append(id).append("\n" + "Brand Name : ");
            builder.append(brand).append("\n" + "Model Name : ");
            builder.append(model).append("\n" + "Type Name : ");
            builder.append(type).append("\n" + "Model Year : ");
            builder.append(modelyear).append("\n" + "Color : ");
            builder.append(color).append("\n" + "Plate Name : ");
            builder.append(plate).append("\n" + "Nickname : ");
            builder.append(nickname).append("\n \n");
        }
        TextView text = (TextView)findViewById(R.id.showrecord);
        text.setText(builder);
    }








}


