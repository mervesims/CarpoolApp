package com.example.mervesimsek.vehicleapp.dal;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.mervesimsek.vehicleapp.common.Logs;
import com.example.mervesimsek.vehicleapp.model.VehicleTableModel;

/**
 * Created by Merve Simsek on 28/02/2017.
 */

public class DatabaseService
{
    public static final DatabaseService instance = new DatabaseService();
    public static DatabaseService getInstance() { return instance; };

    public DatabaseService() {

    }

    public Cursor GET() {
        try {
            Cursor cursor = DatabaseConnectionService
                    .getInstance()
                    .getDatabaseInstance()
                    .rawQuery(VehicleTableModel.getInstance().GetSelectSQL(""), null);
            return cursor;
        }
        catch (Exception ex)
        {
            Logs.error(ex);
            return null;
        }
    }
    public Cursor GETBy(String searchValue) {

        try {
            Cursor cursor = DatabaseConnectionService
                    .getInstance()
                    .getDatabaseInstance()
                    .rawQuery(VehicleTableModel.getInstance().GetSelectSQL(searchValue), null);
            return cursor;
        }
        catch (Exception ex)
        {
            Logs.error(ex);
            return null;
        }
    }
    public Cursor GETDetailById(String id) {

        try {
            Cursor cursor = DatabaseConnectionService
                    .getInstance()
                    .getDatabaseInstance()
                    .rawQuery(VehicleTableModel.getInstance().GetSelectDetailSQL(id), null);
            return cursor;
        }
        catch (Exception ex)
        {
            Logs.error(ex);
            return null;
        }
    }
    public boolean POST(ContentValues parametersAndValues) {
        try {
            DatabaseConnectionService
                    .getInstance()
                    .getDatabaseInstance()
                    .insertOrThrow(VehicleTableModel.getInstance().TableName, null, parametersAndValues);
        }
        catch (Exception ex)
        {
            Logs.error(ex);
            return false;
        }
        finally {
            return true;
        }
    }
    public boolean PUT(ContentValues parametersAndValues ,String id) {
        try {
            VehicleTableModel vehicleTableModel = VehicleTableModel.getInstance();
            DatabaseConnectionService
                    .getInstance()
                    .getDatabaseInstance()
                    .update(vehicleTableModel.TableName, parametersAndValues, vehicleTableModel.Columns.Id  + " = ?", new String[] { String.valueOf(id) });
        }
        catch (Exception ex)
        {
            Logs.error(ex);
            return false;
        }
        finally {
            return true;
        }
    }
    public boolean DELETE(String id) {
        try {
            DatabaseConnectionService
                    .getInstance()
                    .getDatabaseInstance()
                    .execSQL(VehicleTableModel.getInstance().GetDeleteSQL(id));
        }
        catch (Exception ex)
        {
            Logs.error(ex);
            return false;
        }
        finally {
            return true;
        }
    }
}





