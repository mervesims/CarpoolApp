package com.example.mervesimsek.vehicleapp;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
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
                onBackPressed(); // Hangi sayfadan gelindiyse oraya geri
            }
        });


             int maxLength = 4;
             InputFilter[] fArray = new InputFilter[1];
             fArray[0] = new InputFilter.LengthFilter(maxLength);


             int maxOtherLength = 20;
             InputFilter[] iArray = new InputFilter[1];
             iArray[0] = new InputFilter.LengthFilter(maxOtherLength);


        vehicle = new Database(this);

        brand = (EditText) findViewById(R.id.brandedit);
        brand.setFilters(new InputFilter[]{emojifilter});
        brand.setFilters(iArray);


        model = (EditText) findViewById(R.id.modeledit);
        model.setFilters(new InputFilter[]{emojifilter});
        model.setFilters(iArray);

        type = (EditText) findViewById(R.id.typeedit);
        type.setFilters(new InputFilter[]{emojifilter});
        type.setFilters(iArray);

        modelyear = (EditText) findViewById(R.id.modelyearedit);
        modelyear.setFilters(new InputFilter[]{emojifilter});
        modelyear.setFilters(fArray);

        color = (EditText) findViewById(R.id.coloredit);
        color.setFilters(new InputFilter[]{emojifilter});
        color.setFilters(iArray);

        plate = (EditText) findViewById(R.id.plateedit);
        plate.setFilters(new InputFilter[]{emojifilter});
        plate.setFilters(iArray);

        nickname = (EditText) findViewById(R.id.nicknameedit);
        nickname.setFilters(new InputFilter[]{emojifilter});
        nickname.setFilters(iArray);









        //  final Bundle bundle = new Bundle();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabsave);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Integer brandLength = brand.getText().toString().length();
                Integer nicknameLength = nickname.getText().toString().length();
                Integer modelyearLength = modelyear.getText().toString().length();
                String brandcontent = brand.getText().toString();
                String nicknamecontent = nickname.getText().toString();



                boolean isValid = true;

                if (modelyearLength == 0) {
                    modelyear.setError("Model year is required!");
                    isValid = false;
                    return;
                }
                if (Integer.parseInt(modelyear.getText().toString()) < 1899 || Integer.parseInt(modelyear.getText().toString()) > 2018) {
                    modelyear.setError("The year should be between 1900 and 2018!");
                    isValid = false;
                }
                if (brandLength == 0 || brandcontent.substring(0,1).contains(" ")) {
                    brand.setError("Brand is required!");
                    isValid = false;
                }
                if (nicknameLength == 0 || nicknamecontent.substring(0,1).contains(" ")) {
                    nickname.setError("Nickname is required!");
                    isValid = false;
                }


                if (isValid)
                {
                    try {
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
                    } finally {
                        vehicle.close();
                    }
                    Intent intent = new Intent(AddCarActivity.this, ItemListActivity.class);
                    startActivity(intent);
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
        data.put("modelyear", modelyear);
        data.put("color", color);
        data.put("plate", plate);
        data.put("nickname", nickname);
        db.insertOrThrow("vehicles", null, data);
    }

    private String[] SELECT = {"id,brand,model,type,modelyear,color,plate,nickname"};


    private Cursor getRecord() {
        SQLiteDatabase db = vehicle.getReadableDatabase();
        Cursor cursor = db.query("vehicles", SELECT, null, null, null, null, null, null);
        startManagingCursor(cursor);
        return (cursor);
    }

    private void showRecord(Cursor cursor) {
        StringBuilder builder = new StringBuilder("Vehicles: \n");

        while (cursor.moveToNext()) {
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
    public boolean dispatchTouchEvent(MotionEvent ev)
    {
        View view = getCurrentFocus();
        if (view != null && (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_MOVE) && view instanceof EditText && !view.getClass().getName().startsWith("android.webkit.")) {
            int scrcoords[] = new int[2];
            view.getLocationOnScreen(scrcoords);
            float x = ev.getRawX() + view.getLeft() - scrcoords[0];
            float y = ev.getRawY() + view.getTop() - scrcoords[1];
            if (x < view.getLeft() || x > view.getRight() || y < view.getTop() || y > view.getBottom())
                ((InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow((this.getWindow().getDecorView().getApplicationWindowToken()), 0);
        }
        return super.dispatchTouchEvent(ev);
    }


    public static InputFilter emojifilter = new InputFilter() {
        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            for (int index = start; index < end; index++) {
                int type = Character.getType(source.charAt(index));
                if (type == Character.SURROGATE) {
                    return "";
                }
            }
            return null;
        }
    };







}


