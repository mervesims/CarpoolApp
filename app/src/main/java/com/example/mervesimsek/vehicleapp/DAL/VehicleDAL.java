package com.example.mervesimsek.vehicleapp.dal;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;

import com.example.mervesimsek.vehicleapp.model.VehicleModel;

import java.util.Random;

/**
 * Created by mnmlondon2 on 04/04/2017.
 */

// FIXME: 04/04/2017 bu class icerisindeki butun methodların icerigi DatabaseService icerisindeki methodlardan cekilecek . Bu class icerisinde hicbir sekilde dogrudan database ile iletisime gecilmeyecek
public class VehicleDAL {


    // FIXME: 04/04/2017 burasida ayni sekilde diger methodlar gibi datasbase service kismina alinacak
    public static void searchVehicleList(Context currentContext, String filterParameter)
    {
        DatabaseService vehicleDB = new DatabaseService(currentContext);
        Cursor filterVehicleCursor = vehicleDB.searchRecord(vehicleDB,filterParameter);
        createVehicleList(filterVehicleCursor);

    }

    // FIXME: 04/04/2017 buradaki icerik databaseservice kismindan direk VehicleList olarak gelmelidir
    public static void createVehicleList(Cursor cursor)
    {
        // FIXME: 04/04/2017 burada kullanilan statis vehicle model list static modundan cikarilip vehicle dal icerisinden kullanilacak hale getirilecektir.
        VehicleModel.vehicleModelList.clear();
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
            vehicleModel.id = id;
            vehicleModel.brand = brand;
            vehicleModel.model = model;
            vehicleModel.type = type;
            vehicleModel.modelyear = modelyear;
            vehicleModel.color = color;
            vehicleModel.plate = plate;
            vehicleModel.nickname = nickname;
            Random random = new Random();
            vehicleModel.nicknameColor =  Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));
            VehicleModel.vehicleModelList.add(vehicleModel);
        }
    }

    // FIXME: 04/04/2017 diger methodlar gibi buranin icerigi de database service icerisinde olmalidir.
    public static void deleteRow(String vehicleid, Context currentContext)
    {
        DatabaseService vehicle = new DatabaseService(currentContext);
        SQLiteDatabase db = vehicle.getReadableDatabase();
        vehicle.deleteRecord(db,vehicleid);
    }

    // FIXME: 04/04/2017 bu methodun icerigi direk olarak DatabaseService icerisine tasinacak.
    public static Cursor setupVehicleDatabase(Context currentContext)
    {
        DatabaseService vehicle = new DatabaseService(currentContext);
        SQLiteDatabase db = vehicle.getReadableDatabase();

        // FIXME: 04/04/2017 buradaki SELECT icerisinde yazan kolonlar DatabaseService icerisine tasinacak ve orada private olacak disaridan kimse ulasamayacak.
        String[] SELECT = {"id,brand,model,type,modelyear,color,plate,nickname"};
        Cursor cursor = db.query("vehicles",SELECT,null,null,null,null,null,null);
        return(cursor);
    }


}
