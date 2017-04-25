package com.example.mervesimsek.vehicleapp.dal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.mervesimsek.vehicleapp.model.VehicleTableModel;

/**
 * Created by mnmlondon2 on 23/04/2017.
 */

public class DatabaseConnectionService {
    private static final DatabaseConnectionService instance = new DatabaseConnectionService();
    public static DatabaseConnectionService getInstance() {
        return instance;
    }

    private static SQLiteOpenHelper dbProvider;
    public void SetupDatabase(Context context) {

        instance.dbProvider = new SQLiteOpenHelper(context, VehicleTableModel.getInstance().DatabaseName, null, VehicleTableModel.getInstance().VersionNumber) {
            @Override
            public void onCreate(SQLiteDatabase db) {
                new VehicleTableModel().CreateTable(db);
            }
            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { }
        };
    }

    public SQLiteDatabase getDatabaseInstance() {
      return instance.dbProvider.getReadableDatabase();
    }
}
