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
        db.execSQL("CREATE TABLE vehicles (id INTEGER PRIMARY KEY AUTOINCREMENT, brand TEXT, model TEXT, type TEXT, modelyear TEXT, color TEXT, plate TEXT, nickname TEXT);"

        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST vehicles");
        onCreate(db);
    }
}
