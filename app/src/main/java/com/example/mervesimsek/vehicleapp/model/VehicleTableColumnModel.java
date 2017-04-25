package com.example.mervesimsek.vehicleapp.model;

/**
 * Created by mnmlondon2 on 22/04/2017.
 */


public class VehicleTableColumnModel {
    public static String Id = "id";
    public static String BrandName = "brand";
    public static String ModelName = "model";
    public static String ModelYear = "modelyear";
    public static String TypeName = "type";
    public static String Color = "color";
    public static String Plate = "plate";
    public static String NickName = "nickname";
    public static String[] GetColumnNames() {
        return new String[] {
                Id + "," + BrandName + "," + ModelName + "," + ModelYear + "," + TypeName + "," + Color + "," + Plate + "," + NickName
        };
    }
}
