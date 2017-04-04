package com.example.mervesimsek.vehicleapp.controller;


        import android.app.Activity;
        import android.content.Context;
        import android.content.Intent;
        import android.os.Bundle;
        import android.support.design.widget.CollapsingToolbarLayout;
        import android.support.design.widget.FloatingActionButton;
        import android.support.v4.app.Fragment;
        import android.support.v7.widget.Toolbar;
        import android.text.InputFilter;
        import android.text.Spanned;
        import android.view.LayoutInflater;
        import android.view.MotionEvent;
        import android.view.View;
        import android.support.v7.app.AppCompatActivity;
        import android.support.v7.app.ActionBar;
        import android.view.MenuItem;
        import android.view.ViewGroup;
        import android.view.inputmethod.InputMethodManager;
        import android.widget.EditText;

        import com.example.mervesimsek.vehicleapp.dal.DatabaseService;
        import com.example.mervesimsek.vehicleapp.model.VehicleModel;
        import com.example.mervesimsek.vehicleapp.R;


/**
 * An activity representing a single Item detail screen. This
 * activity is only used narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link VehicleListController}.
 */
 class VehicleDetailActivityController extends AppCompatActivity
{
    EditText brand, model, type, modelyear, color, plate, nickname;
    private DatabaseService vehicle;

    public void updateRecord(String id, String brand, String model, String type, String modelyear, String color, String plate, String nickname)
    {
        vehicle.updateRecord(brand,model,modelyear,type,color,plate,nickname, Integer.parseInt(id));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

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
            }
        });

        FloatingActionButton fabeditsave = (FloatingActionButton)findViewById(R.id.fabeditsave);
        fabeditsave.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Integer brandLength = brand.getText().toString().length();
                Integer nicknameLength = nickname.getText().toString().length();
                Integer modelyearLength = modelyear.getText().toString().length();
                String brandcontent = brand.getText().toString();
                String nicknamecontent = nickname.getText().toString();


                // FIXME: 04/04/2017 buradaki bu islemlerin hepsinin bi method icerisinde olmasi gerekmektedir. method ismi IsValid() : Bool dondurmelidir. if icerisinde bunun kontrolu yapilarak kod sadeelestirilmelidir.
                boolean isValid = true;

                if (modelyearLength == 0)
                {
                    // FIXME: 04/04/2017 Sistem icerisinde ui nesneleri icin kullanilan butun textlerin tamami ortak bir class icerisine toplanip oradan okunmalidir. Ornek olarak common isimli bir packgage olusturulacak icerisine ConstraintStrings diye bir class olusturup icerisine degiskenler tanimlayarak yapabilirsin. yada direk android icerisine xml tanimlayarakta yapabilirsin tamamen senin secimine kalmis.,
                    modelyear.setError("Model year is required!");
                    return;
                }
                if (Integer.parseInt(modelyear.getText().toString()) < 1899 || Integer.parseInt(modelyear.getText().toString()) > 2018)
                {
                    modelyear.setError("The year should be between 1900 and 2018!");
                    isValid = false;
                }
                if (brandLength == 0 || brandcontent.substring(0,1).contains(" "))
                {
                    brand.setError("Brand is required!");
                    isValid = false;
                }
                if (nicknameLength == 0 || nicknamecontent.substring(0,1).contains(" "))
                {
                    nickname.setError("Nickname is required!");
                    isValid = false;
                }

                if (isValid)
                {
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

    // TODO : Ekranın başka bir yerine dokunarak klavye kapatma
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev)
    {
        View view = getCurrentFocus();
        if (view != null &&
                (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_MOVE) &&
                view instanceof EditText &&
                !view.getClass().getName().startsWith("android.webkit."))
        {
            int scrcoords[] = new int[2];
            view.getLocationOnScreen(scrcoords);
            float x = ev.getRawX() + view.getLeft() - scrcoords[0];
            float y = ev.getRawY() + view.getTop() - scrcoords[1];
            if (x < view.getLeft() || x > view.getRight() || y < view.getTop() || y > view.getBottom())
                ((InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow((this.getWindow().getDecorView().getApplicationWindowToken()), 0);
        }
        return super.dispatchTouchEvent(ev);
    }

    public static InputFilter emojifilter = new InputFilter()
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

        //TODO: burası list ekranından gonderilen model yada veri yapısını alıp ekrandaki textview üzerine bastıgımız kısımdır.
        if (getArguments().containsKey("UniqueObjectName"))
        {
            VehicleModel vehicleDetailModel = new VehicleModel();
            vehicleDetailModel = (VehicleModel) getArguments().getSerializable("UniqueObjectName");
            this.vehicleModel = vehicleDetailModel;

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null)
            {
                appBarLayout.setTitle(vehicleModel.nickname);
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
            vehicleid = vehicleModel.id;

            SetTextEdit(vehicleModel.brand,R.id.item_detail,rootView);
            SetTextEdit(vehicleModel.model,R.id.item_detail2,rootView);
            SetTextEdit(vehicleModel.modelyear,R.id.item_detail3,rootView);
            SetTextEdit(vehicleModel.type,R.id.item_detail4,rootView);
            SetTextEdit(vehicleModel.color,R.id.item_detail5,rootView);
            SetTextEdit(vehicleModel.plate,R.id.item_detail6,rootView);
            SetTextEdit(vehicleModel.nickname,R.id.item_detail7,rootView);
        }
        return rootView;
    }
}
