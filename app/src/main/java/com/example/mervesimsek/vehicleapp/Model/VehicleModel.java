package com.example.mervesimsek.vehicleapp.model;

        import java.io.Serializable;
        import java.util.ArrayList;
        import java.util.List;


// FIXME: 04/04/2017 butun nesnelerin (method - class - degisken) hepsinin basina comment yazilmalidir. Yazilan commentlerin hepsi ingilizce olmalidir. Sekil olarak asagidakinni aynisi kullanilabilir.--------Tüm commentleri en son kodu incelerken yazmayı doğru buldugum için bu işi en sona bırakacağım.
/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * Replace all uses of this class before publishing your app.
 */
public class VehicleModel implements Serializable {

    public VehicleModel() {

    }

    // FIXME: 04/04/2017 buradaki tanimlanan nesnelerin hepsi pascal case olarak tanimlanmalidir. (for example : Id - Brand) ---------Yapıldı.
    public String Id = "";
    public String Brand = "";
    public String Model = "";
    public String Type = "";
    public String ModelYear = "";
    public String Color = "";
    public String Plate = "";
    public String Nickname = "";
    public int NicknameColor = 0;

    //TODO:Item Array
    // FIXME: 04/04/2017 bu nesne static modundan cikartilmalidir. public olabilir fakat static olmamalidir.--------Kontrol edilecek.
    public final List<VehicleModel> vehicleModelList = new ArrayList<VehicleModel>();


}
