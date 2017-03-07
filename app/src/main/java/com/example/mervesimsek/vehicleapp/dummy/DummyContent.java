package com.example.mervesimsek.vehicleapp.dummy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.mervesimsek.vehicleapp.Database;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<DummyItem> ITEMS = new ArrayList<DummyItem>();
    public static final List<VehicleModel> vehicleModelList = new ArrayList<VehicleModel>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

    private static final int COUNT = 25;

    static Context context;

    static
    {
    }
    public static String[] SELECT = {"id,brand,model,type,modelyear,color,plate,nickname"};
    public static Cursor setupVehicleDatabase(Context currentContext) {
        Database vehicle = new Database(currentContext);
        SQLiteDatabase db = vehicle.getReadableDatabase();
        Cursor cursor = db.query("vehicles",SELECT,null,null,null,null,null,null);

       // vehicle.close();

        //startManagingCursor(cursor);
        return(cursor);
    }
    public DummyContent() {
    }

    private static void addItem(DummyItem brand) {
        ITEMS.add(brand);
        ITEM_MAP.put(brand.id, brand);
    }

    private static DummyItem createDummyItem(String index) {
      //  return new DummyItem(String.valueOf(cursor), "Item " , makeDetails(cursor));
        return new DummyItem("1" + index,  "Merve" + index, "Simsek" + index);
    }
    public static void createVehicleList(Cursor cursor) {
        vehicleModelList.clear();
        while (cursor.moveToNext()) {
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
            vehicleModelList.add(vehicleModel);
        }
    }
    public static void deleteRow(String vehicleid, Context currentContext) {
        Database vehicle = new Database(currentContext);
        SQLiteDatabase db = vehicle.getReadableDatabase();
        vehicle.deleteRecord(db,vehicleid);
    }

   /* private static String makeDetails(Cursor cursor) {
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

        return builder.toString();
    }*/


    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem {
        public final String id;
        public final String content;
        public final String details;

        public DummyItem(String id, String content, String details) {
            this.id = id;
            this.content = content;
            this.details = details;
        }

        @Override
        public String toString() {
            return content;
        }
    }

    public static class VehicleModel implements Serializable {
        public String id ="";
        public String brand = "";
        public String model = "";
        public String type = "";
        public String modelyear ="";
        public String color = "";
        public String plate = "";
        public String nickname = "";
    }
}
