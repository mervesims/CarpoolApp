package com.example.mervesimsek.vehicleapp;

import android.app.Activity;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mervesimsek.vehicleapp.dummy.DummyContent;

import static com.example.mervesimsek.vehicleapp.R.id.container;

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a {@link ItemListActivity}
 * in two-pane mode (on tablets) or a {@link ItemDetailActivity}
 * on handsets.
 */
public class ItemDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";



    /**
     * The dummy content this fragment is presenting.
     */
    private DummyContent.VehicleModel vehicleModel;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //TODO: burası list ekranından gonderilen model yada veri yapısını alıp ekrandaki textview üzerine bastıgımız kısımdır.
        if (getArguments().containsKey("UniqueObjectName")) {


            DummyContent.VehicleModel vehicleDetailModel = new DummyContent.VehicleModel();
            vehicleDetailModel = (DummyContent.VehicleModel) getArguments().getSerializable("UniqueObjectName");
            this.vehicleModel = vehicleDetailModel;


            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(vehicleModel.nickname);
            }
        }
    }
    public void SetTextEdit (String valueText, Integer editTextXmlid, View rootView) {

        EditText editTextdetail = ((EditText) rootView.findViewById(editTextXmlid));
        editTextdetail.setText(valueText);
        editTextdetail.setFocusableInTouchMode(false);
        editTextdetail.setFocusable(false);
       // editTextdetail.setTextColor(R.color.black_semi_transparent);
    }

    public static String vehicleid = "-1";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.item_detail, container, false);

        // Show the dummy content as text in a TextView.
        if (vehicleModel != null) {
            //TODO: burası da detail ekranında textview içine set edilen baska bir yer.
            vehicleid = vehicleModel.id;

           SetTextEdit("Brand Name : " + vehicleModel.brand,R.id.item_detail,rootView);
           SetTextEdit("Model : " + vehicleModel.model,R.id.item_detail2,rootView);
           SetTextEdit("Model Year : " + vehicleModel.modelyear,R.id.item_detail3,rootView);
           SetTextEdit("Type : " + vehicleModel.type,R.id.item_detail4,rootView);
           SetTextEdit("Color : " + vehicleModel.color,R.id.item_detail5,rootView);
           SetTextEdit("Plate : " + vehicleModel.plate,R.id.item_detail6,rootView);
           SetTextEdit("Nickname : " + vehicleModel.nickname,R.id.item_detail7,rootView);

        }


        return rootView;
    }
}
