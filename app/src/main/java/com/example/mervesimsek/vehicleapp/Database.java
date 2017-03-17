package com.example.mervesimsek.vehicleapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by mnmlondon2 on 28/02/2017.
 */

public class Database extends SQLiteOpenHelper {

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

    public Database(Context context) {
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

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public void deleteRecord(SQLiteDatabase db,String id) { //TODO:id si belli olan row u silmek için
        db.delete(TABLE_NAME, ID + " = ?",new String[]{String.valueOf(id)});
        db.close();
    }

    public void insertRecord(String brand_name, String model_name, String model_year, String type,String color_name, String plate, String nickname) {
        //TODO:insertRecord methodu Database veri eklemek için
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(BRAND_NAME, brand_name);
        values.put(MODEL_NAME, model_name);
        values.put(MODEL_YEAR, model_year);
        values.put(TYPE_NAME, type);
        values.put(COLOR, color_name);
        values.put(PLATE, plate);
        values.put(NICKNAME, nickname);

        db.insert(TABLE_NAME, null, values);
        db.close(); //TODO : // Database Bağlantısını kapattık*/
    }

    public HashMap<String, String> detailRecord(int id){
        /** TODO:Databaseden id si belli olan row u çekmek için
        Bu methodda sadece tek row değerleri alınır.
        HashMap bir çift boyutlu arraydir.anahtar-değer ikililerini bir arada tutmak için tasarlanmıştır.
        map.put("x","300"); mesala burda anahtar x değeri 300.*/

        HashMap<String,String> record = new HashMap<String,String>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME+ " WHERE id="+id;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if(cursor.getCount() > 0){
            record.put(BRAND_NAME, cursor.getString(1));
            record.put(MODEL_NAME, cursor.getString(2));
            record.put(MODEL_YEAR, cursor.getString(3));
            record.put(TYPE_NAME, cursor.getString(4));
            record.put(COLOR, cursor.getString(5));
            record.put(PLATE, cursor.getString(6));
            record.put(NICKNAME, cursor.getString(7));
        }
        cursor.close();
        db.close();
        // return record
        return record;
    }
    public ArrayList<HashMap<String, String>> records(){

        /** TODO :Bu methodda ise tablodaki tüm değerleri alıyoruz
        ** ArrayList adı üstünde Array lerin listelendiği bir Array.Burda hashmapleri listeleyeceğiz
        ** Herbir satırı değer ve value ile hashmap a atıyoruz. Her bir satır 1 tane hashmap arrayı demek.
        ** olusturdugumuz tüm hashmapleri ArrayList e atıp geri dönüyoruz(return). **/

        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(selectQuery, null);
        ArrayList<HashMap<String, String>> recordlist = new ArrayList<HashMap<String, String>>();


        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                for(int i=0; i<cursor.getColumnCount();i++)
                {
                    map.put(cursor.getColumnName(i), cursor.getString(i));
                }

                recordlist.add(map);
            } while (cursor.moveToNext());
        }
        db.close();
        return recordlist;
    }
    public void updateRecord(String brand_name, String model_name, String model_year, String type,String color_name, String plate, String nickname,int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        // TODO: Bu methodda ise var olan veriyi güncelliyoruz(update)
        ContentValues values = new ContentValues();
        values.put(ID, id);
        values.put(BRAND_NAME, brand_name);
        values.put(MODEL_NAME, model_name);
        values.put(MODEL_YEAR, model_year);
        values.put(TYPE_NAME, type);
        values.put(COLOR, color_name);
        values.put(PLATE, plate);
        values.put(NICKNAME, nickname);

        // updating row
        db.update(TABLE_NAME, values, ID + " = ?",
                new String[] { String.valueOf(id) });
    }
    public int getRowCount() {
        // TODO: Bu method bu uygulamada kullanılmıyor ama appsentence'ta lazım olacak.Tablodaki row sayısını geri döner.
        //TODO:Login uygulamasında da kullanılabilir.
        String countQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int rowCount = cursor.getCount();
        db.close();
        cursor.close();
        return rowCount;
    }
    public void resetTables(){
        // TODO: Tüm verileri siler. tabloyu resetler.
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, null, null);
        db.close();
}

}




