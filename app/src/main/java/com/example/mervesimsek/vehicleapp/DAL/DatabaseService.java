package com.example.mervesimsek.vehicleapp.dal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Merve Simsek on 28/02/2017.
 */

// FIXME: 04/04/2017 bu class icerisinde sql connection ile ilgilli yapilan butun islemler bir kere tanimlanarak yapilmalidir.
// FIXME: 04/04/2017 bu class iceriside veritabani islemleri icin tanimlanan degiskenlerin isimleri duzeltilmelidir
// FIXME: 04/04/2017 bu class icerisinde  veritabani ile ilgili string islemlerin sql sorgularinin degisken olarak tanımlanıp kullanlması saglanmalıdır. 
// FIXME: 04/04/2017 bu class icerisinde db isimli bi degisken tanimlanip butun islemlerde (insert - update - delete - search) tek nesneden kullanilmalidir. bunu yapmaktaki amac db nesnesi bir kere instance alinacaktir her veritabani isleminde tekrar tekrar ramde nesne yaratmayacaktir. 
public class DatabaseService extends SQLiteOpenHelper
{
    private static final String DATABASE = "vehicle";
    private static final int VERSION = 1;
    private static final String TABLE_NAME = "vehicles";
    private static String BRAND_NAME = "brand";
    private static String ID = "id";
    private static String MODEL_NAME = "model";
    private static String MODEL_YEAR = "modelyear";
    private static String TYPE_NAME = "type";
    private static String COLOR = "color";
    private static String PLATE = "plate";
    private static String NICKNAME = "nickname";

    public DatabaseService(Context context) {
        super(context, DATABASE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + BRAND_NAME + " TEXT,"
                + MODEL_NAME + " TEXT,"
                + MODEL_YEAR + " TEXT,"
                + TYPE_NAME + " TEXT,"
                + COLOR + " TEXT,"
                + PLATE + " TEXT,"
                + NICKNAME + " TEXT" + ")";
        db.execSQL(CREATE_TABLE);

    }

    // FIXME: 04/04/2017 bu method silinnerek denenmelidir. eger patlamiyorsa zorunlu degilse silinmelidir. 
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //TODO:id si belli olan row u silmek için
    public void deleteRecord(SQLiteDatabase db,String id)
    {
        db.delete(TABLE_NAME, ID + " = ?",new String[]{String.valueOf(id)});
        db.close();
    }
    // TODO: Bu methodda ise var olan veriyi güncelliyoruz
    public void updateRecord(String brand_name, String model_name, String model_year, String type,String color_name, String plate, String nickname,int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ID, id);
        values.put(BRAND_NAME, brand_name);
        values.put(MODEL_NAME, model_name);
        values.put(MODEL_YEAR, model_year);
        values.put(TYPE_NAME, type);
        values.put(COLOR, color_name);
        values.put(PLATE, plate);
        values.put(NICKNAME, nickname);
        db.update(TABLE_NAME, values, ID + " = ?", new String[] { String.valueOf(id) });
    }
    //TODO: List ekranında kelime arama sql kodu
    public Cursor searchRecord(DatabaseService vehicleDB, String filterParameter)
    {
        String countQuery = "SELECT  * FROM " + TABLE_NAME + " WHERE " + this.NICKNAME + " LIKE '%" + filterParameter + "%'";
        SQLiteDatabase db = vehicleDB.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        return cursor;
    }
}





