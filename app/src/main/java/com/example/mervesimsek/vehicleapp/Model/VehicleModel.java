package com.example.mervesimsek.vehicleapp.model;

        import android.content.ContentValues;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;

        import com.example.mervesimsek.vehicleapp.common.CommonUtils;
        import com.example.mervesimsek.vehicleapp.dal.DatabaseService;
        import com.example.mervesimsek.vehicleapp.dal.VehicleDAL;

        import java.io.Serializable;
        import java.util.ArrayList;
        import java.util.List;


// -----Tüm commentleri en son kodu incelerken yazmayı doğru buldugum için bu işi en sona bırakacağım.
/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * Replace all uses of this class before publishing your app.
 */
public class VehicleModel {
    //singleton pattern
    private static final VehicleModel instance = new VehicleModel();
    public static VehicleModel getInstance() {
        return instance;
    }

    public VehicleModel() {

    }

    public String Id = "";
    public String BrandName = "";
    public String ModelName = "";
    public String ModelYear = "";
    public String TypeName = "";
    public String Color = "";
    public String Plate = "";
    public String Nickname = "";
    public int NicknameColor = 0;

    public ContentValues GetModelSerialize(boolean isInsert) {
        VehicleTableModel vehicleTableModel = VehicleTableModel.getInstance();

        ContentValues parametersAndValues = new ContentValues();

        if (!isInsert) {
            parametersAndValues.put(vehicleTableModel.Columns.Id, this.Id);
        }
        parametersAndValues.put(vehicleTableModel.Columns.BrandName, this.BrandName);
        parametersAndValues.put(vehicleTableModel.Columns.ModelName, this.ModelName);
        parametersAndValues.put(vehicleTableModel.Columns.ModelYear, this.ModelYear);
        parametersAndValues.put(vehicleTableModel.Columns.TypeName, this.TypeName);
        parametersAndValues.put(vehicleTableModel.Columns.Color, this.Color);
        parametersAndValues.put(vehicleTableModel.Columns.Plate, this.Plate);
        parametersAndValues.put(vehicleTableModel.Columns.NickName, this.Nickname);

        return parametersAndValues;
    }
}




