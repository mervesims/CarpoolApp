package com.example.mervesimsek.vehicleapp.controller;


        import android.app.Activity;
        import android.content.Intent;
        import android.database.sqlite.SQLiteOpenHelper;
        import android.os.Bundle;
        import android.support.annotation.Nullable;
        import android.support.design.widget.CollapsingToolbarLayout;
        import android.support.design.widget.FloatingActionButton;
        import android.support.v4.app.Fragment;
        import android.support.v4.app.FragmentManager;
        import android.text.InputFilter;
        import android.text.Spanned;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.EditText;

        import com.example.mervesimsek.vehicleapp.common.CommonObjectManager;
        import com.example.mervesimsek.vehicleapp.common.ConstraintStrings;
        import com.example.mervesimsek.vehicleapp.dal.VehicleDAL;
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
    EditText txtModelName;
    EditText txtModelYear;
    EditText txtTypeName;
    EditText txtColor;
    EditText txtPlate;
    EditText txtNickName;
    EditText txtBrand;
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public VehicleDetailFragmentController() {}

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

    }
    private void setToolbarTitle(String title) {
        Activity activity = this.getActivity();
        CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
        if (appBarLayout != null)
        {
            appBarLayout.setTitle(title);
        }
    }
    public void SetTextEdit (String valueText, Integer editTextXmlid, View rootView)
    {
        EditText editTextdetail = ((EditText) rootView.findViewById(editTextXmlid));
        editTextdetail.setText(valueText);
        editTextdetail.setFocusableInTouchMode(false);
        editTextdetail.setFocusable(false);
    }
    private void setupView() {

        String asdasd = "";



        //AKIN    : buradaki floating butonunu fragment icerisine koymalısın biri ayrı digeri ayri olmamalı

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        View rootView = inflater.inflate(R.layout.item_detail, container, false);

        if (CommonObjectManager.VehicleListSelectedRowModel!= null)
        {
            VehicleModel _model = CommonObjectManager.VehicleListSelectedRowModel;
            this.setToolbarTitle(_model.Nickname);

            // TODO: 23/04/2017 buradaki item_detail gibi yazan xmllerin ismi dogru verilmeli. orn : txtbrandName gibi
            SetTextEdit(_model.BrandName, R.id.item_detail,rootView);
            SetTextEdit(_model.ModelName, R.id.item_detail2,rootView);
            SetTextEdit(_model.ModelYear, R.id.item_detail3,rootView);
            SetTextEdit(_model.TypeName, R.id.item_detail4,rootView);
            SetTextEdit(_model.Color, R.id.item_detail5,rootView);
            SetTextEdit(_model.Plate, R.id.item_detail6,rootView);
            SetTextEdit(_model.Nickname, R.id.item_detail7,rootView);
        }
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private InputFilter emojifilter = new InputFilter()
    {
        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend)
        {
            for (int index = start; index < end; index++)
            {
                int type = Character.getType(source.charAt(index));
                if (type == Character.SURROGATE)
                {
                    return "";
                }
            }
            return null;
        }
    };

    private boolean checkValidations() {
        // FIXME: 23/04/2017 : textlere getText gibi yaptıgın seyleri extension method gibi yapmayı dene
        boolean isValid = true;
        Integer brandLength = txtBrand.getText().toString().length();
        Integer nicknameLength = txtNickName.getText().toString().length();
        Integer modelyearLength = txtModelYear.getText().toString().length();
        String brandcontent = txtBrand.getText().toString();
        String nicknamecontent = txtNickName.getText().toString();

        if (modelyearLength == 0)
        {
            txtModelYear.setError(ConstraintStrings.ModelYearBlankError);
        }

        if (Integer.parseInt(txtModelYear.getText().toString()) < 1899 || Integer.parseInt(txtModelYear.getText().toString()) > 2018)
        {
            txtModelYear.setError(ConstraintStrings.ModelYearDateError);
            isValid = false;
        }

        if (brandLength == 0 || brandcontent.substring(0,1).contains(" "))
        {
            txtBrand.setError(ConstraintStrings.BrandBlankError);
            isValid = false;
        }

        if (nicknameLength == 0 || nicknamecontent.substring(0,1).contains(" "))
        {
            txtNickName.setError(ConstraintStrings.NicknameBlankError);
            isValid = false;
        }
        return isValid;
    }
    private void updateVehicle() {
        if (this.checkValidations())
        {
            VehicleModel vehicleModel = new VehicleModel();
            vehicleModel.Id = CommonObjectManager.VehicleListSelectedRowModel.Id;
            vehicleModel.BrandName = txtBrand.getText().toString();
            vehicleModel.ModelName = txtModelName.getText().toString();
            vehicleModel.ModelYear = txtModelYear.getText().toString();
            vehicleModel.TypeName = txtTypeName.getText().toString();
            vehicleModel.Color = txtColor.getText().toString();
            vehicleModel.Plate = txtPlate.getText().toString();
            vehicleModel.Nickname = txtNickName.getText().toString();

            VehicleDAL.getInstance().UpdateVehicle(vehicleModel);

            /*Intent in = new Intent(this.getClass(),VehicleListController.class);
            startActivity(in);*/
        }
    }
    public void btnEditOnclick() {
        int maxLength = 4;
        InputFilter[] fArray = new InputFilter[1];
        fArray[0] = new InputFilter.LengthFilter(maxLength);

        int maxOtherLength = 20;
        InputFilter[] iArray = new InputFilter[1];
        iArray[0] = new InputFilter.LengthFilter(maxOtherLength);

        txtBrand = (EditText)getView().findViewById(R.id.item_detail);
        txtBrand.setFocusable(true);
        txtBrand.setFocusableInTouchMode(true);
        txtBrand.requestFocus(); //for cursor
        txtBrand.setFilters(new InputFilter[]{emojifilter});
        txtBrand.setFilters(iArray);

        txtModelName = (EditText)getView().findViewById(R.id.item_detail2);
        txtModelName.setFocusable(true);
        txtModelName.setFocusableInTouchMode(true);
        txtModelName.setFilters(new InputFilter[]{emojifilter});
        txtModelName.setFilters(iArray);

        txtTypeName =(EditText)getView().findViewById(R.id.item_detail4);
        txtTypeName.setFocusable(true);
        txtTypeName.setFocusableInTouchMode(true);
        txtTypeName.setFilters(new InputFilter[]{emojifilter});
        txtTypeName.setFilters(iArray);

        txtModelYear = (EditText)getView().findViewById(R.id.item_detail3);
        txtModelYear.setFocusable(true);
        txtModelYear.setFocusableInTouchMode(true);
        txtModelYear.setFilters(new InputFilter[]{emojifilter});
        txtModelYear.setFilters(fArray);

        txtColor =(EditText)getView().findViewById(R.id.item_detail5);
        txtColor.setFocusable(true);
        txtColor.setFocusableInTouchMode(true);
        txtColor.setFilters(new InputFilter[]{emojifilter});
        txtColor.setFilters(iArray);

        txtPlate = (EditText)getView().findViewById(R.id.item_detail6);
        txtPlate.setFocusable(true);
        txtPlate.setFocusableInTouchMode(true);
        txtPlate.setFilters(new InputFilter[]{emojifilter});
        txtPlate.setFilters(iArray);

        txtNickName =(EditText)getView().findViewById(R.id.item_detail7);
        txtNickName.setFocusable(true);
        txtNickName.setFocusableInTouchMode(true);
        txtNickName.setFilters(new InputFilter[]{emojifilter});
        txtNickName.setFilters(iArray);


//                txtBrand = (CustomEditText) findViewById(R.id.item_detail);
//                txtBrand.requestFocus(); //for cursor
//                txtBrand.SetMaxLength(20);
//
//                txtModelName = (CustomEditText)findViewById(R.id.item_detail2);
//                txtModelName.SetMaxLength(20);
//
//                txtTypeName =(CustomEditText)findViewById(R.id.item_detail4);
//                txtTypeName.SetMaxLength(20);
//
//                txtModelYear = (CustomEditText)findViewById(R.id.item_detail3);
//                txtModelYear.SetMaxLength(4);
//
//                txtColor =(CustomEditText)findViewById(R.id.item_detail5);
//                txtColor.SetMaxLength(20);
//
//                txtPlate = (CustomEditText)findViewById(R.id.item_detail6);
//                txtPlate.SetMaxLength(20);
//
//                txtNickName =(CustomEditText)findViewById(R.id.item_detail7);
//                txtNickName.SetMaxLength(20);
    }
    public void btnSaveClick() {
        updateVehicle();
    }
}
