package com.example.mervesimsek.vehicleapp.dal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;

import com.example.mervesimsek.vehicleapp.common.CommonUtils;
import com.example.mervesimsek.vehicleapp.common.Logs;
import com.example.mervesimsek.vehicleapp.model.VehicleModel;
import com.example.mervesimsek.vehicleapp.model.VehicleTableModel;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;


/**
 * Created by mnmlondon2 on 04/04/2017.
 */

public class VehicleDAL {
    private static final VehicleDAL instance = new VehicleDAL();
    public static VehicleDAL getInstance() {
        return instance;
    }

    DatabaseService service;
    public VehicleDAL() {
        service = DatabaseService.getInstance();
    }
    public List<VehicleModel> GetVehicleList() {
        Cursor dasd = service.GET();
        return mappings(dasd);
    }
    public List<VehicleModel> GetVehicleListBy(String searchValue) {
        return mappings(service.GETBy(searchValue));
    }
    public VehicleModel GetVehicle(String id) {
        return mapping(service.GETDetailById(id));
    }
    public void InsertVehicle() {

    }
    public void UpdateVehicle(VehicleModel model) {
        service.PUT(model.GetModelSerialize(), model.Id);
    }
    public void DeleteVehicle(String id) {
        service.DELETE(id);
    }

    private List<VehicleModel> mappings(Cursor cursor) {
        List<VehicleModel> vehicles = new ArrayList<VehicleModel>();

        while (cursor.moveToNext())
        {
            vehicles.add(mapping(cursor));
        }
        return vehicles;
    }
    private VehicleModel mapping(Cursor cursor) {
        try {
            VehicleTableModel vehicleTableModel = VehicleTableModel.getInstance();

            VehicleModel vehicleModel = new VehicleModel();
            vehicleModel.Id             = CommonUtils.getValueFromCursor(cursor, vehicleTableModel.Columns.Id);
            vehicleModel.BrandName      = CommonUtils.getValueFromCursor(cursor, vehicleTableModel.Columns.BrandName);
            vehicleModel.ModelName      = CommonUtils.getValueFromCursor(cursor, vehicleTableModel.Columns.ModelName);
            vehicleModel.TypeName       = CommonUtils.getValueFromCursor(cursor, vehicleTableModel.Columns.TypeName);
            vehicleModel.ModelYear      = CommonUtils.getValueFromCursor(cursor, vehicleTableModel.Columns.ModelYear);
            vehicleModel.Color          = CommonUtils.getValueFromCursor(cursor, vehicleTableModel.Columns.Color);
            vehicleModel.Plate          = CommonUtils.getValueFromCursor(cursor, vehicleTableModel.Columns.Plate);
            vehicleModel.Nickname       = CommonUtils.getValueFromCursor(cursor, vehicleTableModel.Columns.NickName);
            vehicleModel.NicknameColor  = CommonUtils.getRandomColor();

            return vehicleModel;
        }
        catch (Exception ex) {
            Logs.error(ex);
            return new VehicleModel();
        }
    }
}
