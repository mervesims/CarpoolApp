package com.example.mervesimsek.vehicleapp.controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.mervesimsek.vehicleapp.R;
import com.example.mervesimsek.vehicleapp.common.BaseController;
import com.example.mervesimsek.vehicleapp.common.CommonObjectManager;

import static com.example.mervesimsek.vehicleapp.common.CommonObjectManager.OperationStatus.insert;

/**
 * An activity representing a single Item detail screen. This
 * activity is only used narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link VehicleListController}.
 */
public class VehicleDetailActivityController extends BaseController {


    @Override
    protected void customOnCreate(@Nullable Bundle savedInstanceState, int layoutResID, int toolbarResID) {
        super.customOnCreate(savedInstanceState, R.layout.activity_item_detail, R.id.detail_toolbar);


        // Show the Up button in the action bar.vehicleModel
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
            VehicleDetailFragmentController fragment = new VehicleDetailFragmentController();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.item_detail_container, fragment)
                    .commit();
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabedit);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment vehicleDetailFragment = getSupportFragmentManager().findFragmentById(R.id.item_detail_container);
                if (vehicleDetailFragment.isVisible()) {
                    ((VehicleDetailFragmentController) vehicleDetailFragment).btnEditOnclick();
                }
            }
        });

        if (CommonObjectManager.Status == insert) {
            fab.hide();
        } else {
            fab.show();
        }

        FloatingActionButton fabeditsave = (FloatingActionButton) findViewById(R.id.fabeditsave);
        fabeditsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment vehicleDetailFragment = getSupportFragmentManager().findFragmentById(R.id.item_detail_container);
                if (vehicleDetailFragment.isVisible()) {
                    ((VehicleDetailFragmentController) vehicleDetailFragment).btnSaveClick();
                }
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            navigateUpTo(new Intent(this, VehicleListController.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}

