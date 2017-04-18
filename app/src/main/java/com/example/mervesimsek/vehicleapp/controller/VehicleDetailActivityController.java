package com.example.mervesimsek.vehicleapp.controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.example.mervesimsek.vehicleapp.R;
import com.example.mervesimsek.vehicleapp.common.BaseController;
import com.example.mervesimsek.vehicleapp.common.ConstraintStrings;
import com.example.mervesimsek.vehicleapp.dal.DatabaseService;

/**
 * An activity representing a single Item detail screen. This
 * activity is only used narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link VehicleListController}.
 */
 class VehicleDetailActivityController extends BaseController
{
    EditText brand, model, type, modelyear, color, plate, nickname;;
    private DatabaseService vehicle;

    public void updateRecord(String id, String brand, String model, String type, String modelyear, String color, String plate, String nickname)
    {
        vehicle.updateRecord(brand,model,modelyear,type,color,plate,nickname, Integer.parseInt(id));
    }

    @Override
    protected void customOnCreate(@Nullable Bundle savedInstanceState, int layoutResID, int toolbarResID) {
        super.customOnCreate(savedInstanceState, R.layout.activity_item_detail, R.id.detail_toolbar);


        vehicle = new DatabaseService(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabedit);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                int maxLength = 4;
                InputFilter[] fArray = new InputFilter[1];
                fArray[0] = new InputFilter.LengthFilter(maxLength);

                int maxOtherLength = 20;
                InputFilter[] iArray = new InputFilter[1];
                iArray[0] = new InputFilter.LengthFilter(maxOtherLength);

                brand =(EditText)findViewById(R.id.item_detail);
                brand.setFocusable(true);
                brand.setFocusableInTouchMode(true);
                brand.requestFocus(); //for cursor
                brand.setFilters(new InputFilter[]{emojifilter});
                brand.setFilters(iArray);

                model = (EditText)findViewById(R.id.item_detail2);
                model.setFocusable(true);
                model.setFocusableInTouchMode(true);
                model.setFilters(new InputFilter[]{emojifilter});
                model.setFilters(iArray);

                type =(EditText)findViewById(R.id.item_detail4);
                type.setFocusable(true);
                type.setFocusableInTouchMode(true);
                type.setFilters(new InputFilter[]{emojifilter});
                type.setFilters(iArray);

                modelyear = (EditText)findViewById(R.id.item_detail3);
                modelyear.setFocusable(true);
                modelyear.setFocusableInTouchMode(true);
                modelyear.setFilters(new InputFilter[]{emojifilter});
                modelyear.setFilters(fArray);

                color =(EditText)findViewById(R.id.item_detail5);
                color.setFocusable(true);
                color.setFocusableInTouchMode(true);
                color.setFilters(new InputFilter[]{emojifilter});
                color.setFilters(iArray);

                plate = (EditText)findViewById(R.id.item_detail6);
                plate.setFocusable(true);
                plate.setFocusableInTouchMode(true);
                plate.setFilters(new InputFilter[]{emojifilter});
                plate.setFilters(iArray);

                nickname =(EditText)findViewById(R.id.item_detail7);
                nickname.setFocusable(true);
                nickname.setFocusableInTouchMode(true);
                nickname.setFilters(new InputFilter[]{emojifilter});
                nickname.setFilters(iArray);


//                brand = (CustomEditText) findViewById(R.id.item_detail);
//                brand.requestFocus(); //for cursor
//                brand.SetMaxLength(20);
//
//                model = (CustomEditText)findViewById(R.id.item_detail2);
//                model.SetMaxLength(20);
//
//                type =(CustomEditText)findViewById(R.id.item_detail4);
//                type.SetMaxLength(20);
//
//                modelyear = (CustomEditText)findViewById(R.id.item_detail3);
//                modelyear.SetMaxLength(4);
//
//                color =(CustomEditText)findViewById(R.id.item_detail5);
//                color.SetMaxLength(20);
//
//                plate = (CustomEditText)findViewById(R.id.item_detail6);
//                plate.SetMaxLength(20);
//
//                nickname =(CustomEditText)findViewById(R.id.item_detail7);
//                nickname.SetMaxLength(20);
            }
        });

        FloatingActionButton fabeditsave = (FloatingActionButton)findViewById(R.id.fabeditsave);
        fabeditsave.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                if (isValid())
                {
                    updateRec();
                }

            }
        });

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
        {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
        if (savedInstanceState == null)
        {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            //TODO: diger ekrandan gonderilen modeli ya da veri yapısını buradan okuyoruz.
            Bundle vehicleBundle = new Bundle();
            vehicleBundle = getIntent().getExtras();

            // FIXME: 04/04/2017 buradaki kodlari bir method haline getirip sistem icerisinde kullanilan butun ekranlari tek methodtan geelcek sekilde yazmalisin
            VehicleDetailFragmentController fragment = new VehicleDetailFragmentController();
            fragment.setArguments(vehicleBundle);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.item_detail_container, fragment)
                    .commit();
        }
    }




    public boolean isValid()
    {
        boolean isValid = true;

        Integer brandLength = brand.getText().toString().length();
        Integer nicknameLength = nickname.getText().toString().length();
        Integer modelyearLength = modelyear.getText().toString().length();
        String brandcontent = brand.getText().toString();
        String nicknamecontent = nickname.getText().toString();

        // FIXME: 04/04/2017 buradaki bu islemlerin hepsinin bi method icerisinde olmasi gerekmektedir. method ismi IsValid() : Bool dondurmelidir. if icerisinde bunun kontrolu yapilarak kod sadeelestirilmelidir.


        if (modelyearLength == 0)
        {
            // FIXME: 04/04/2017 Sistem icerisinde ui nesneleri icin kullanilan butun textlerin tamami ortak bir class icerisine toplanip oradan okunmalidir. Ornek olarak common isimli bir packgage olusturulacak icerisine ConstraintStrings diye bir class olusturup icerisine degiskenler tanimlayarak yapabilirsin. yada direk android icerisine xml tanimlayarakta yapabilirsin tamamen senin secimine kalmis.,
            modelyear.setError(ConstraintStrings.ModelYearBlankError);
        }
        if (Integer.parseInt(modelyear.getText().toString()) < 1899 || Integer.parseInt(modelyear.getText().toString()) > 2018)
        {
            modelyear.setError(ConstraintStrings.ModelYearDateError);
            isValid = false;
        }
        if (brandLength == 0 || brandcontent.substring(0,1).contains(" "))
        {
            brand.setError(ConstraintStrings.BrandBlankError);
            isValid = false;
        }
        if (nicknameLength == 0 || nicknamecontent.substring(0,1).contains(" "))
        {
            nickname.setError(ConstraintStrings.NicknameBlankError);
            isValid = false;
        }
        return isValid;
    }

    public void updateRec(){
        // TODO: Parametreleri methoda bagliyoruz.
        updateRecord(
                VehicleDetailFragmentController.vehicleid,
                brand.getText().toString(),
                model.getText().toString(),
                type.getText().toString(),
                modelyear.getText().toString(),
                color.getText().toString(),
                plate.getText().toString(),
                nickname.getText().toString());

        Intent in = new Intent(VehicleDetailActivityController.this,VehicleListController.class);
        startActivity(in);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        if (id == android.R.id.home)
        {
            navigateUpTo(new Intent(this, VehicleListController.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
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


}

class CustomEditText extends EditText {

    public CustomEditText(Context context) {
        super(context);
    }

    public CustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    //    public CustomEditText(Context context) {
//        super(context);
//        this.setupView();
//    }
    private void setupView() {
        this.disableEmojiCharacters();
        this.setupFocusable();
    }

    public void SetMaxLength(int length) {
        int maxLength = length;
        InputFilter[] iArray = new InputFilter[1];
        iArray[0] = new InputFilter.LengthFilter(maxLength);
        this.setFilters(iArray);
    }
    public void SetMinLength() {

    }


    private void disableEmojiCharacters() {
        this.setFilters(new InputFilter[]{ emojifilter });
    }

    private void setupFocusable() {
        this.setFocusable(true);
        this.setFocusableInTouchMode(true);
    }

    public String getValue(){
        return this.getText().toString();
    }

    public void Content(){  //aslında böyle bişey yapmak istedim ama vehicle addcontroller veya başka bi yerde kullanamadım bunu.
        String thiscontent = this.getText().toString();
    }
    public Integer Length(){
        return this.getValue().length();
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


}
