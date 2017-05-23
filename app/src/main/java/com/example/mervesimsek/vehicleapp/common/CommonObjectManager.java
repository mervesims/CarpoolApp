package com.example.mervesimsek.vehicleapp.common;

import com.example.mervesimsek.vehicleapp.model.VehicleModel;

/**
 * Created by mnmlondon2 on 23/04/2017.
 */

public class CommonObjectManager {
    public static VehicleModel VehicleListSelectedRowModel = new VehicleModel();
    public static OperationStatus Status = OperationStatus.detail;
    public enum OperationStatus {
        update,
        insert,
        detail

    }
}


