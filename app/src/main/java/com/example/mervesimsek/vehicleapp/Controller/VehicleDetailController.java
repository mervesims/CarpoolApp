package com.example.mervesimsek.vehicleapp.controller;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
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

import static com.example.mervesimsek.vehicleapp.common.CommonObjectManager.OperationStatus.detail;
import static com.example.mervesimsek.vehicleapp.common.CommonObjectManager.OperationStatus.insert;
import static com.example.mervesimsek.vehicleapp.common.CommonObjectManager.OperationStatus.update;
import static com.example.mervesimsek.vehicleapp.common.CommonObjectManager.Status;


/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a {@link VehicleListController}
 * in two-pane mode (on tablets) or a {@link VehicleDetailFragmentController}
 * on handsets.
 */

class VehicleDetailFragmentController extends Fragment {
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
    public VehicleDetailFragmentController() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }


    private void setToolbarTitle(String title) {
        Activity activity = this.getActivity();
        CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
        if (appBarLayout != null) {
            appBarLayout.setTitle(title);
        }
    }

    public void SetTextEdit(String valueText, Integer editTextXmlid, View rootView) {
        EditText editTextdetail = ((EditText) rootView.findViewById(editTextXmlid));

        editTextdetail.setFocusable(Status == insert);
        editTextdetail.setFocusableInTouchMode(Status == insert);

        if (Status == update)
            editTextdetail.setText(valueText);
    }

    private void disableUIControlsBy(boolean enable) {
        int maxLength = 4;
        InputFilter[] fArray = new InputFilter[1];
        fArray[0] = new InputFilter.LengthFilter(maxLength);

        int maxOtherLength = 20;
        InputFilter[] iArray = new InputFilter[1];
        iArray[0] = new InputFilter.LengthFilter(maxOtherLength);

        txtBrand.setFocusable(enable);
        txtBrand.setFocusableInTouchMode(enable);
        txtBrand.requestFocus(); //for cursor
        txtBrand.setFilters(new InputFilter[]{emojifilter});
        txtBrand.setFilters(iArray);

        txtModelName.setFocusable(enable);
        txtModelName.setFocusableInTouchMode(enable);
        txtModelName.setFilters(new InputFilter[]{emojifilter});
        txtModelName.setFilters(iArray);

        txtTypeName.setFocusable(enable);
        txtTypeName.setFocusableInTouchMode(enable);
        txtTypeName.setFilters(new InputFilter[]{emojifilter});
        txtTypeName.setFilters(iArray);

        txtModelYear.setFocusable(enable);
        txtModelYear.setFocusableInTouchMode(enable);
        txtModelYear.setFilters(new InputFilter[]{emojifilter});
        txtModelYear.setFilters(fArray);

        txtColor.setFocusable(enable);
        txtColor.setFocusableInTouchMode(enable);
        txtColor.setFilters(new InputFilter[]{emojifilter});
        txtColor.setFilters(iArray);

        txtPlate.setFocusable(enable);
        txtPlate.setFocusableInTouchMode(enable);
        txtPlate.setFilters(new InputFilter[]{emojifilter});
        txtPlate.setFilters(iArray);

        txtNickName.setFocusable(enable);
        txtNickName.setFocusableInTouchMode(enable);
        txtNickName.setFilters(new InputFilter[]{emojifilter});
        txtNickName.setFilters(iArray);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.item_detail, container, false);

        if (CommonObjectManager.VehicleListSelectedRowModel != null && Status != detail) {
            VehicleModel _model = CommonObjectManager.VehicleListSelectedRowModel;
            if (Status == update) {
                this.setToolbarTitle(_model.Nickname);
            } else {
                this.setToolbarTitle("Add New Car");
            }

            txtBrand = (EditText) rootView.findViewById(R.id.txt_brand_name);
            txtModelName = (EditText) rootView.findViewById(R.id.txt_model_name);
            txtTypeName = (EditText) rootView.findViewById(R.id.txt_type_name);
            txtModelYear = (EditText) rootView.findViewById(R.id.txt_modelyear);
            txtColor = (EditText) rootView.findViewById(R.id.txt_color_name);
            txtPlate = (EditText) rootView.findViewById(R.id.txt_plate_name);
            txtNickName = (EditText) rootView.findViewById(R.id.txt_nickname);

            SetTextEdit(_model.BrandName, R.id.txt_brand_name, rootView);
            SetTextEdit(_model.ModelName, R.id.txt_model_name, rootView);
            SetTextEdit(_model.ModelYear, R.id.txt_modelyear, rootView);
            SetTextEdit(_model.TypeName, R.id.txt_type_name, rootView);
            SetTextEdit(_model.Color, R.id.txt_color_name, rootView);
            SetTextEdit(_model.Plate, R.id.txt_plate_name, rootView);
            SetTextEdit(_model.Nickname, R.id.txt_nickname, rootView);
        }
        if (Status != insert ) {
            Status = detail;
        }
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private InputFilter emojifilter = new InputFilter() {
        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            for (int index = start; index < end; index++) {
                int type = Character.getType(source.charAt(index));
                if (type == Character.SURROGATE) {
                    return "";
                }
            }
            return null;
        }
    };

    private boolean checkValidations() {
        boolean isValid = true;

        Integer brandLength = txtBrand.getText().toString().length();
        Integer nicknameLength = txtNickName.getText().toString().length();
        Integer modelyearLength = txtModelYear.getText().toString().length();
        String brandcontent = txtBrand.getText().toString();
        String nicknamecontent = txtNickName.getText().toString();

        if (modelyearLength == 0) {
            txtModelYear.setError(ConstraintStrings.ModelYearBlankError);
            isValid = false;
        }

        else if (Integer.parseInt(txtModelYear.getText().toString()) < 1899 || Integer.parseInt(txtModelYear.getText().toString()) > 2018) {
            txtModelYear.setError(ConstraintStrings.ModelYearDateError);
            isValid = false;
        }

        if (brandLength == 0 || brandcontent.substring(0, 1).contains(" ")) {
            txtBrand.setError(ConstraintStrings.BrandBlankError);
            isValid = false;
        }

        if (nicknameLength == 0 || nicknamecontent.substring(0, 1).contains(" ")) {
            txtNickName.setError(ConstraintStrings.NicknameBlankError);
            isValid = false;
        }
        return isValid;
    }

    private void updateVehicle() {

        if (this.checkValidations()) {
            VehicleModel vehicleModel = new VehicleModel();
            vehicleModel.Id           = CommonObjectManager.VehicleListSelectedRowModel.Id;
            vehicleModel.BrandName    = txtBrand.getText().toString();
            vehicleModel.ModelName    = txtModelName.getText().toString();
            vehicleModel.ModelYear    = txtModelYear.getText().toString();
            vehicleModel.TypeName     = txtTypeName.getText().toString();
            vehicleModel.Color        = txtColor.getText().toString();
            vehicleModel.Plate        = txtPlate.getText().toString();
            vehicleModel.Nickname     = txtNickName.getText().toString();

            if (Status == update) {
                VehicleDAL.getInstance().UpdateVehicle(vehicleModel);
            } else if (Status == insert) {
                VehicleDAL.getInstance().InsertVehicle(vehicleModel);
            }
        }
    }


    public void btnEditOnclick() {
        Status = update;
        disableUIControlsBy(true);
    }

    public void btnSaveClick() {

            updateVehicle();

        if (this.checkValidations()) {
             this.getActivity().onBackPressed();
        }

    }
}
