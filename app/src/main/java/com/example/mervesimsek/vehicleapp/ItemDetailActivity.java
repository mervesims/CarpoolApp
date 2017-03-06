package com.example.mervesimsek.vehicleapp;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.mervesimsek.vehicleapp.dummy.DummyContent;

/**
 * An activity representing a single Item detail screen. This
 * activity is only used narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link ItemListActivity}.
 */
public class ItemDetailActivity extends AppCompatActivity {

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        /*EditText editText= (EditText) findViewById(R.id.item_detail);
        editText.setTag(editText.getKeyListener());
        editText.setKeyListener(null); */



        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabedit);
        fab.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                EditText editText = (EditText)findViewById(R.id.item_detail);
                editText.setEnabled(true);

                EditText editText1 = (EditText)findViewById(R.id.item_detail2);
                editText1.setEnabled(true);

                EditText editText2 = (EditText)findViewById(R.id.item_detail3);
                editText2.setEnabled(true);

                EditText editText3 = (EditText)findViewById(R.id.item_detail4);
                editText3.setEnabled(true);

                EditText editText4 = (EditText)findViewById(R.id.item_detail5);
                editText4.setEnabled(true);

                EditText editText5 = (EditText)findViewById(R.id.item_detail6);
                editText5.setEnabled(true);

                EditText editText6 = (EditText)findViewById(R.id.item_detail7);
                editText6.setEnabled(true);

                                       }
        });

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
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
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.


            //TODO: diger ekrandan gonderilen modeli ya da veri yapısını buradan okuyoruz.
            Bundle vehicleBundle = new Bundle();
            vehicleBundle = getIntent().getExtras();


            ItemDetailFragment fragment = new ItemDetailFragment();
            fragment.setArguments(vehicleBundle);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.item_detail_container, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            navigateUpTo(new Intent(this, ItemListActivity.class));
            return true;

        }
        return super.onOptionsItemSelected(item);

    }
}
