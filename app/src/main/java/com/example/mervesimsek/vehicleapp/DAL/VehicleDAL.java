package com.example.mervesimsek.vehicleapp.dal;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.mervesimsek.vehicleapp.model.VehicleModel;

import java.util.ArrayList;
import java.util.List;

import static com.example.mervesimsek.vehicleapp.dal.DatabaseService.vehicleList;

/**
 * Created by mnmlondon2 on 04/04/2017.
 */

// FIXME: 04/04/2017 bu class icerisindeki butun methodların icerigi DatabaseService icerisindeki methodlardan cekilecek . Bu class icerisinde hicbir sekilde dogrudan database ile iletisime gecilmeyecek---------Control edilecek
public class VehicleDAL {


    // FIXME: 04/04/2017 burasida ayni sekilde diger methodlar gibi datasbase service kismina alinacak ----------Control edilecek.
    public static void searchVehicleList(Context currentContext, String filterParameter) {
        DatabaseService vehicleDB = new DatabaseService(currentContext);
        Cursor filterVehicleCursor = vehicleDB.searchRecord(vehicleDB, filterParameter);
        vehicleList(filterVehicleCursor);
    }


    // FIXME: 04/04/2017 diger methodlar gibi buranin icerigi de database service icerisinde olmalidir.-------Bunun burada olması gerektiğini düşünüyorum.
    public static void deleteRow(String vehicleid, Context currentContext) {
        DatabaseService vehicle = new DatabaseService(currentContext);
        SQLiteDatabase db = vehicle.getReadableDatabase();
        vehicle.deleteRecord(db, vehicleid);
    }

    // FIXME: 04/04/2017 bu methodun icerigi direk olarak DatabaseService icerisine tasinacak.--------Kontrol edilecek.
    public static Cursor setupVehicleDatabase(Context currentContext) {
        DatabaseService vehicle = new DatabaseService(currentContext);
        SQLiteDatabase db = vehicle.getReadableDatabase();

        Cursor selectVehicleCursor = vehicle.selectRecord(db);
        vehicle.selectRecord(db);
        return selectVehicleCursor;
    }
    //FIXME: Vehicle modelden buraya taşınmıştır. Bana saçma geldi. Kontrol edilecek.
    public static final List<VehicleModel> vehicleModelList = new ArrayList<VehicleModel>();

}
