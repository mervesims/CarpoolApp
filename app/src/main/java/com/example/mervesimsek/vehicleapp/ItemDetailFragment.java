package com.example.mervesimsek.vehicleapp;

import android.app.Activity;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mervesimsek.vehicleapp.dummy.DummyContent;

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
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.item_detail, container, false);

        // Show the dummy content as text in a TextView.
        if (vehicleModel != null) {
            //TODO: burası da detail ekranında textview içine set edilen baska bir yer fakat burada modelYear verisini set edildi
            ((TextView) rootView.findViewById(R.id.item_detail)).setText(vehicleModel.modelyear);

            //TODO: yukarıdaki text gibi ekrana textler koyarak detay ekranında gostermek istedigin bilgileri aşağıdaki örnekte gösterildiği gibi doldurarak gosterebilirsin.
            /*((TextView) rootView.findViewById(R.id.item_detail)).setText(vehicleModel.brand);
            ((TextView) rootView.findViewById(R.id.item_detail)).setText(vehicleModel.color);
            ((TextView) rootView.findViewById(R.id.item_detail)).setText(vehicleModel.nickname);
            */
        }

        return rootView;
    }
}
