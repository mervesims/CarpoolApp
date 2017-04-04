package com.example.mervesimsek.vehicleapp.model;

        import android.database.Cursor;

        import com.example.mervesimsek.vehicleapp.dal.DatabaseService;

        import java.io.Serializable;
        import java.util.ArrayList;
        import java.util.List;


// FIXME: 04/04/2017 butun nesnelerin (method - class - degisken) hepsinin basina comment yazilmalidir. Yazilan commentlerin hepsi ingilizce olmalidir. Sekil olarak asagidakinni aynisi kullanilabilir.
/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * Replace all uses of this class before publishing your app.
 */
public class VehicleModel implements Serializable {
    public VehicleModel() {

    }

    // FIXME: 04/04/2017 buradaki tanimlanan nesnelerin hepsi camel case olarak tanimlanmalidir. (for example : Id - Brand)
    public String id = "";
    public String brand = "";
    public String model = "";
    public String type = "";
    public String modelyear = "";
    public String color = "";
    public String plate = "";
    public String nickname = "";
    public int nicknameColor = 0;

    //TODO:Item Array
    // FIXME: 04/04/2017 bu nesne static modundan cikartilmalidir. public olabilir fakat static olmamalidir.
    public static final List<VehicleModel> vehicleModelList = new ArrayList<VehicleModel>();

    static {
    }
}
