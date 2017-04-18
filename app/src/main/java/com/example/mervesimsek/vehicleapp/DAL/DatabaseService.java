package com.example.mervesimsek.vehicleapp.dal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;

import com.example.mervesimsek.vehicleapp.model.VehicleModel;

import java.util.Random;

/**
 * Created by Merve Simsek on 28/02/2017.
 */

// FIXME: 04/04/2017 bu class icerisinde sql connection ile ilgilli yapilan butun islemler bir kere tanimlanarak yapilmalidir.-------Bunu pek anlayamadım aslında tek bi kere yapılmıyo mu tum işlemler örneğin silme, select vs gibi.
// FIXME: 04/04/2017 bu class iceriside veritabani islemleri icin tanimlanan degiskenlerin isimleri duzeltilmelidir------İsimler düzeltildi.
// FIXME: 04/04/2017 bu class icerisinde  veritabani ile ilgili string islemlerin sql sorgularinin degisken olarak tanımlanıp kullanlması saglanmalıdır. -------bunun nasıl yapacağımı bilmiyorum. düşündğ bazı fikirlerim oluştu ama beraber tartısarak yapsak daha guzel olur gibi.
// FIXME: 04/04/2017 bu class icerisinde db isimli bi degisken tanimlanip butun islemlerde (insert - update - delete - search) tek nesneden kullanilmalidir. bunu yapmaktaki amac db nesnesi bir kere instance alinacaktir her veritabani isleminde tekrar tekrar ramde nesne yaratmayacaktir.--------KONTROL EDİLECEK
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

    public static void db (SQLiteDatabase db ) {

        //BU KISMI SON FIXMEYE  GÖRE YAPTIM EMİN DEĞİLİM BÖYLE OLMASI GEREKTİĞİNDEN BU YUZDEN KONTROL EDİLMESİ GEREKİYOR.
    }

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

    // Upgrade işlemi için zorunludur.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public Cursor selectRecord(SQLiteDatabase db){
        String[] SELECT = {"id,brand,model,type,modelyear,color,plate,nickname"};
        Cursor cursor = db.query("vehicles",SELECT,null,null,null,null,null,null);
        return(cursor);
    }

    //Id si belli olan row u silmek için
    public void deleteRecord(SQLiteDatabase db,String id)
    {
        db.delete(TABLE_NAME, ID + " = ?",new String[]{String.valueOf(id)});
        db.close();
    }
    // Bu methodda ise var olan veriyi güncelliyoruz
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
    //List ekranında kelime arama sql kodu
    public Cursor searchRecord(DatabaseService vehicleDB, String filterParameter)
    {
        String countQuery = "SELECT  * FROM " + TABLE_NAME + " WHERE " + this.NICKNAME + " LIKE '%" + filterParameter + "%'";
        SQLiteDatabase db = vehicleDB.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        return cursor;
    }

    // FIXME: 04/04/2017 buradaki icerik databaseservice kismindan direk VehicleList olarak gelmelidir------şuan o sekilde geliyor.
    public static void vehicleList(Cursor cursor)
    {
        // FIXME: 04/04/2017 burada kullanilan statis vehicle Model list static modundan cikarilip vehicle dal icerisinden kullanilacak hale getirilecektir.--------Kontrol edilecek.()
        VehicleDAL.vehicleModelList.clear();
        while (cursor.moveToNext())
        {
            String id = cursor.getString(cursor.getColumnIndex("id"));
            String brand = cursor.getString(cursor.getColumnIndex("brand"));
            String model = cursor.getString(cursor.getColumnIndex("model"));
            String type = cursor.getString(cursor.getColumnIndex("type"));
            String modelyear = cursor.getString(cursor.getColumnIndex("modelyear"));
            String color = cursor.getString(cursor.getColumnIndex("color"));
            String plate = cursor.getString(cursor.getColumnIndex("plate"));
            String nickname = cursor.getString(cursor.getColumnIndex("nickname"));

            VehicleModel vehicleModel = new VehicleModel();
            vehicleModel.Id = id;
            vehicleModel.Brand = brand;
            vehicleModel.Model = model;
            vehicleModel.Type = type;
            vehicleModel.ModelYear = modelyear;
            vehicleModel.Color = color;
            vehicleModel.Plate = plate;
            vehicleModel.Nickname = nickname;
            Random random = new Random();
            vehicleModel.NicknameColor =  Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));
            VehicleDAL.vehicleModelList.add(vehicleModel);
        }
    }
}





