package com.example.mervesimsek.vehicleapp.controller;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import com.example.mervesimsek.vehicleapp.common.BaseController;
import com.example.mervesimsek.vehicleapp.dal.DatabaseService;
import com.example.mervesimsek.vehicleapp.R;
import com.example.mervesimsek.vehicleapp.dal.VehicleDAL;

public class VehicleAddController extends BaseController {
    EditText brand, model, type, modelyear, color, plate, nickname;


    private DatabaseService vehicle;

    @Override
    protected void customOnCreate(@Nullable Bundle savedInstanceState, int layoutResID, int toolbarResID) {
        super.customOnCreate(savedInstanceState, R.layout.activity_item_detail, R.id.detail_toolbar);







/*
        //TODO: Bir edittext'e girilebilecek maximum harf sayısı
        int maxLength = 4;
        InputFilter[] fArray = new InputFilter[1];
        fArray[0] = new InputFilter.LengthFilter(maxLength);

        int maxOtherLength = 20;
        InputFilter[] iArray = new InputFilter[1];
        iArray[0] = new InputFilter.LengthFilter(maxOtherLength);

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


        // Save butonu kontrolleri
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabsave);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING); // Butonun klavyeyle beraber yükselmemesi için.
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Integer brandLength = brand.getText().toString().length();
                Integer nicknameLength = nickname.getText().toString().length();
                Integer modelyearLength = modelyear.getText().toString().length();
                String brandcontent = brand.getText().toString();
                String nicknamecontent = nickname.getText().toString();

                boolean isValid = true;

                if (modelyearLength == 0)
                {
                    modelyear.setError("Model year is required!");
                    isValid = false;
                    return;
                }
                if (Integer.parseInt(modelyear.getText().toString()) < 1899 || Integer.parseInt(modelyear.getText().toString()) > 2018)
                {
                    modelyear.setError("The year should be between 1900 and 2018!");
                    isValid = false;
                }
                if (brandLength == 0 || brandcontent.substring(0,1).contains(" "))
                {
                    brand.setError("Brand is required!");
                    isValid = false;
                }
                if (nicknameLength == 0 || nicknamecontent.substring(0,1).contains(" "))
                {
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
                    }
                    finally
                    {

                    }
                    Intent intent = new Intent(VehicleAddController.this, VehicleListController.class);
                    startActivity(intent);
                }
            }
        });
    }



    //TODO: Kayıt methodu
    private void saveRecord(String brand, String model, String type, String modelyear, String color, String plate, String nickname) {
        *//*SQLiteDatabase db = vehicle.getWritableDatabase();
        ContentValues data = new ContentValues();
        data.put("txtBrand", brand);
        data.put("txtModelName", model);
        data.put("txtTypeName", type);
        data.put("txtModelYear", modelyear);
        data.put("color", color);
        data.put("txtPlate", plate);
        data.put("txtNickName", nickname);
        db.insertOrThrow("vehicles", null, data);
        *//*
    }


    public static InputFilter emojifilter = new InputFilter()
    {
        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend)
        {
            for (int index = start; index < end; index++)
            {
                int type = Character.getType(source.charAt(index));
                if (type == Character.SURROGATE)
                {
                    return "";
                }
            }
            return null;
        }
    };*/

    }
}