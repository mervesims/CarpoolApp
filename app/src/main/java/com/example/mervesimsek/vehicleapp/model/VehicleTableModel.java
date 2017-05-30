package com.example.mervesimsek.vehicleapp.model;

import android.database.sqlite.SQLiteDatabase;
import android.icu.text.SelectFormat;

/**
 * Created by mnmlondon2 on 22/04/2017.
 */

public class VehicleTableModel extends BaseTableModel {

    private static final VehicleTableModel instance = new VehicleTableModel();
    public static VehicleTableModel getInstance() {
        return instance;
    }
    public VehicleTableModel() {
        this.DatabaseName = "vehicle";
        this.VersionNumber = 1;
        this.TableName = "vehicles";
    }
    public VehicleTableColumnModel Columns;

    public void CreateTable(SQLiteDatabase db) {
        String CURRENT_CREATE_TABLE_SQL = "";
        CURRENT_CREATE_TABLE_SQL = CREATE_TABLE_SQL.replaceAll("#TableName#", this.TableName);
        CURRENT_CREATE_TABLE_SQL = CURRENT_CREATE_TABLE_SQL.replaceAll("#Id#",VehicleTableColumnModel.Id);
        CURRENT_CREATE_TABLE_SQL = CURRENT_CREATE_TABLE_SQL.replaceAll("#BrandName#",VehicleTableColumnModel.BrandName);
        CURRENT_CREATE_TABLE_SQL = CURRENT_CREATE_TABLE_SQL.replaceAll("#ModelName#",VehicleTableColumnModel.ModelName);
        CURRENT_CREATE_TABLE_SQL = CURRENT_CREATE_TABLE_SQL.replaceAll("#ModelYear#",VehicleTableColumnModel.ModelYear);
        CURRENT_CREATE_TABLE_SQL = CURRENT_CREATE_TABLE_SQL.replaceAll("#TypeName#",VehicleTableColumnModel.TypeName);
        CURRENT_CREATE_TABLE_SQL = CURRENT_CREATE_TABLE_SQL.replaceAll("#Color#",VehicleTableColumnModel.Color);
        CURRENT_CREATE_TABLE_SQL = CURRENT_CREATE_TABLE_SQL.replaceAll("#Plate#",VehicleTableColumnModel.Plate);
        CURRENT_CREATE_TABLE_SQL = CURRENT_CREATE_TABLE_SQL.replaceAll("#NickName#",VehicleTableColumnModel.NickName);
        db.execSQL(CURRENT_CREATE_TABLE_SQL);
    }
    public String GetSelectSQL(String searchValue) {
        String CURRENT_SELECT_TABLE_SQL = "";
        CURRENT_SELECT_TABLE_SQL = SELECT_TABLE_SQL.replaceAll("#TableName#", this.TableName);
        if (searchValue != "") {
            CURRENT_SELECT_TABLE_SQL = CURRENT_SELECT_TABLE_SQL + SELECT_TABLE_WHERE_SQL.replaceAll("#SearchValue#", searchValue);
            CURRENT_SELECT_TABLE_SQL = CURRENT_SELECT_TABLE_SQL.replaceAll("#NickName#", this.Columns.NickName);
        }
        return CURRENT_SELECT_TABLE_SQL;
    }
    public String GetSelectDetailSQL(String searchValue) {
        String CURRENT_SELECT_DETAIL_SQL = SELECT_TABLE_SQL.replaceAll("#TableName#", this.TableName);
        if (searchValue != "") {
            CURRENT_SELECT_DETAIL_SQL = CURRENT_SELECT_DETAIL_SQL  + SELECT_TABLE_WHERE_FIRST_ROW_SQL.replaceAll("#SearchValue#", searchValue);
        }
        return CURRENT_SELECT_DETAIL_SQL;
    }
    public String GetDeleteSQL(String id) {
        String CURRENT_DELETE_TABLE_SQL = DELETE_TABLE_SQL.replaceAll("#TableName#", this.TableName);
        CURRENT_DELETE_TABLE_SQL = CURRENT_DELETE_TABLE_SQL .replaceAll("#ID#", id);
        return CURRENT_DELETE_TABLE_SQL ;
    }

    private String CREATE_TABLE_SQL = "CREATE TABLE #TableName# ("
            + "#Id# INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "#BrandName# TEXT,"
            + "#ModelName# TEXT,"
            + "#ModelYear# TEXT,"
            + "#TypeName# TEXT,"
            + "#Color# TEXT,"
            + "#Plate# TEXT,"
            + "#NickName# TEXT)";

    private String SELECT_TABLE_SQL = "SELECT * FROM #TableName# ";
    private String SELECT_TABLE_WHERE_SQL = "WHERE #NickName# LIKE '%#SearchValue#%'";
    private String SELECT_TABLE_WHERE_FIRST_ROW_SQL = "WHERE ID=#SearchValue#";
    private String DELETE_TABLE_SQL = "DELETE FROM #TableName# WHERE id=#ID#" ;

}

