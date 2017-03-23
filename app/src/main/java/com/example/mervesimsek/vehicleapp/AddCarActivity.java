package com.example.mervesimsek.vehicleapp;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

public class AddCarActivity extends AppCompatActivity {
    EditText brand, model, type, modelyear, color, plate, nickname;
    private Database vehicle;


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
                onBackPressed(); // Hangi sayfadan gelindiyse oraya geri döner.
            }
        });



        vehicle = new Database(this);

        brand = (EditText) findViewById(R.id.brandedit);
        model = (EditText) findViewById(R.id.modeledit);
        type = (EditText) findViewById(R.id.typeedit);
        modelyear = (EditText) findViewById(R.id.modelyearedit);
        color = (EditText) findViewById(R.id.coloredit);
        plate = (EditText) findViewById(R.id.plateedit);
        nickname = (EditText) findViewById(R.id.nicknameedit);

        //  final Bundle bundle = new Bundle();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabsave);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // TODO : İlk harfin boşluk olamama durumu

//                if (brand.getText().toString().contains("") && nickname.getText().toString().contains("") && modelyear.getText().toString().contains("")) {
//                    if (brand.getText().toString().matches(" ")) {
//                        brand.setError("No Spaces Allowed");
//                    }
//                    if (modelyear.getText().toString().matches(" ")) {
//                        modelyear.setError("No Spaces Allowed");
//                    }
//                    if (nickname.getText().toString().matches(" ")) {
//                        nickname.setError("No Spaces Allowed");
//                    }
//                }
//                if (brand.getText().toString().substring(0,1) != " " && nickname.getText().toString().substring(0,1) != " " && modelyear.getText().toString().substring(0,1) != " ") {
//                    try {
//                        saveRecord(brand.getText().toString(),
//                                model.getText().toString(),
//                                type.getText().toString(),
//                                modelyear.getText().toString(),
//                                color.getText().toString(),
//                                plate.getText().toString(),
//                                nickname.getText().toString()
//                        );
//                        Cursor cursor = getRecord();
//                        showRecord(cursor);
//                    } finally {
//                        vehicle.close();
//                    }
//                    Intent intent = new Intent(AddCarActivity.this, ItemListActivity.class);
//                    startActivity(intent);
//                } else {
//                    final Context context = view.getContext();
//                    Toast.makeText(context, "Empty spaces are available.", Toast.LENGTH_SHORT).show();
//                    if (brand.getText().toString().substring(0,1) == " " ) {
//                        brand.setError("Brand is required!");
//                    }
//                    if (nickname.getText().toString().substring(0,1) == " " ) {
//                        nickname.setError("Nickname is required!");
//                    }
//                    if (modelyear.getText().toString().substring(0,1) == " " ) {
//                        modelyear.setError("Model year is required!");
//                    }
//
//                }

                if ( brand.getText().toString().length() >= 1 && nickname.getText().length() >= 1 && modelyear.getText().length() >=1 ) {
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
                    startActivity(intent);
                }
                else
                {
                    final Context context = view.getContext();
                    Toast.makeText(context, "Empty spaces are available.", Toast.LENGTH_SHORT).show();
                    if (brand.getText().length() == 0){
                        brand.setError( "Brand is required!" ); }
                    if (nickname.getText().length() == 0){
                        nickname.setError("Nickname is required!"); }
                    if (modelyear.getText().length() == 0){
                        modelyear.setError("Model year is required!"); }
                }




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

    }

// TODO : Ekranın başka bir yerine dokunarak klavye kapatma
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View view = getCurrentFocus();
        if (view != null && (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_MOVE) && view instanceof EditText && !view.getClass().getName().startsWith("android.webkit.")) {
            int scrcoords[] = new int[2];
            view.getLocationOnScreen(scrcoords);
            float x = ev.getRawX() + view.getLeft() - scrcoords[0];
            float y = ev.getRawY() + view.getTop() - scrcoords[1];
            if (x < view.getLeft() || x > view.getRight() || y < view.getTop() || y > view.getBottom())
                ((InputMethodManager)this.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow((this.getWindow().getDecorView().getApplicationWindowToken()), 0);
        }
        return super.dispatchTouchEvent(ev);
    }





}


