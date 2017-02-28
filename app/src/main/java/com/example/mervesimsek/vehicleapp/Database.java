package com.example.mervesimsek.vehicleapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by mnmlondon2 on 28/02/2017.
 */

public class Database extends SQLiteOpenHelper {

    private static final String DATABASE = "vehicle" ;
    private static final int VERSION = 1;

    public Database(Context context){
        super(context,DATABASE,null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE vehicles (id INTEGER PRIMARY_KEY AUTOINCREMENT, brand_name TEXT, model_name TEXT, type TEXT, model_year DATE, color TEXT, plate TEXT, nickname TEXT, active BOOLEAN);"

        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST vehicles");
        onCreate(db);
    }
}
