package com.example.mervesimsek.vehicleapp.controller;


        import android.app.Activity;
        import android.os.Bundle;
        import android.support.design.widget.CollapsingToolbarLayout;
        import android.support.v4.app.Fragment;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.EditText;

        import com.example.mervesimsek.vehicleapp.model.VehicleModel;
        import com.example.mervesimsek.vehicleapp.R;


/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a {@link VehicleListController}
 * in two-pane mode (on tablets) or a {@link VehicleDetailFragmentController}
 * on handsets.
 */
class VehicleDetailFragmentController extends Fragment
{
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";
    /**
     * The dummy content this fragment is presenting.
     */
    private VehicleModel vehicleModel;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public VehicleDetailFragmentController() {}

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        //TODO: burası list ekranından gonderilen Model yada veri yapısını alıp ekrandaki textview üzerine bastıgımız kısımdır.
        if (getArguments().containsKey("UniqueObjectName"))
        {
            VehicleModel vehicleDetailModel = new VehicleModel();
            vehicleDetailModel = (VehicleModel) getArguments().getSerializable("UniqueObjectName");
            this.vehicleModel = vehicleDetailModel;

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null)
            {
                appBarLayout.setTitle(vehicleModel.Nickname);
            }
        }
    }

    public void SetTextEdit (String valueText, Integer editTextXmlid, View rootView)
    {
        EditText editTextdetail = ((EditText) rootView.findViewById(editTextXmlid));
        editTextdetail.setText(valueText);
        editTextdetail.setFocusableInTouchMode(false);
        editTextdetail.setFocusable(false);
    }

    public static String vehicleid = "-1";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.item_detail, container, false);

        if (vehicleModel != null)
        {
            //TODO: burası da detail ekranında textview içine set edilen baska bir yer.
            vehicleid = vehicleModel.Id;

            SetTextEdit(vehicleModel.Brand,R.id.item_detail,rootView);
            SetTextEdit(vehicleModel.Model,R.id.item_detail2,rootView);
            SetTextEdit(vehicleModel.ModelYear,R.id.item_detail3,rootView);
            SetTextEdit(vehicleModel.Type,R.id.item_detail4,rootView);
            SetTextEdit(vehicleModel.Color,R.id.item_detail5,rootView);
            SetTextEdit(vehicleModel.Plate,R.id.item_detail6,rootView);
            SetTextEdit(vehicleModel.Nickname,R.id.item_detail7,rootView);
        }
        return rootView;
    }
}
