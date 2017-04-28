package com.example.mervesimsek.vehicleapp.controller;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.mervesimsek.vehicleapp.R;
import com.example.mervesimsek.vehicleapp.common.BaseController;
import com.example.mervesimsek.vehicleapp.common.CommonObjectManager;
import com.example.mervesimsek.vehicleapp.common.ConstraintStrings;
import com.example.mervesimsek.vehicleapp.dal.DatabaseConnectionService;
import com.getbase.floatingactionbutton.FloatingActionButton;



/**
 * Kullanıcının splash screendn sonra karşısına çıkacak ilk ekrandır.
 * Navigation Drawer işlemleri bu ekrandadır.
 */


public class MainController extends BaseController
        implements NavigationView.OnNavigationItemSelectedListener
{
    final Context context = this;

    @Override
    protected void customOnCreate(@Nullable Bundle savedInstanceState, int layoutResID, int toolbarResID) {
        super.customOnCreate(savedInstanceState, R.layout.activity_main,R.id.toolbar);



        TextView logo = (TextView) findViewById(R.id.logo);
        Typeface font = Typeface.createFromAsset(context.getAssets(), "fonts/gist.ttf");
        logo.setTypeface(font);

        //Sirali floating action button yaratma.
        ShapeDrawable drawable = new ShapeDrawable(new OvalShape());
        drawable.getPaint().setColor(getResources().getColor(R.color.buttonpressed2));
        final FloatingActionButton actionMyCars = (FloatingActionButton) findViewById(R.id.action_a);
        actionMyCars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CommonObjectManager.IsUpdateMode = true;
                Intent intcars = new Intent(MainController.this, VehicleListController.class);
                startActivity(intcars);
            }
        });
        final FloatingActionButton actionAddNewCar = (FloatingActionButton) findViewById(R.id.action_b);
        actionAddNewCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CommonObjectManager.IsUpdateMode = false;
                Intent intgo = new Intent(MainController.this, VehicleDetailActivityController.class);
                startActivity(intgo);
            }
        });

        //Ekranın herhangi bir yerine dokunulduğunda drawer kapatma
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        // create database instance, because it should be used only 1 instance
        DatabaseConnectionService dcs = new DatabaseConnectionService();
        dcs.SetupDatabase(context);
    }

    //Navigation Drawer Kapatma
    @Override
    public void onBackPressed()
    {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        return true;
    }

    //Setting menüsü yaratma
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        if (id == R.id.action_settings)
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //Navigation menüye tıklandığında yapılacak işlemler ve içerik
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        if(id == R.id.nav_main)
        {
            Intent intentmain = new Intent(MainController.this, MainController.class);
            startActivity(intentmain);
        }
        else if (id == R.id.nav_car)
        {
            CommonObjectManager.IsUpdateMode = true;
            Intent intentitem = new Intent(MainController.this, VehicleListController.class);
            startActivity(intentitem);
        }
        else if (id == R.id.nav_add)
        {
            CommonObjectManager.IsUpdateMode = false;
            Intent intentadd = new Intent(MainController.this,VehicleDetailActivityController.class);
            startActivity(intentadd);
        }
        else if (id == R.id.nav_aboutappsims)
        {
            Intent browser = new Intent(Intent.ACTION_VIEW, Uri.parse(ConstraintStrings.AppSimsWebSiteURL));
            startActivity(browser);
        }
        else if (id == R.id.nav_share)
        {
            String message = ConstraintStrings.ShareMessage;
            Intent share = new Intent(Intent.ACTION_SEND);
            share.setType("text/plain");
            share.putExtra(Intent.EXTRA_TEXT, message);
            startActivity(Intent.createChooser(share, ConstraintStrings.AppName));
        }
        else if (id == R.id.nav_send)
        {
            String message = ConstraintStrings.ShareMessage;
            Intent share = new Intent(Intent.ACTION_SEND);
            share.setType("text/plain");
            share.putExtra(Intent.EXTRA_TEXT, message);
            startActivity(Intent.createChooser(share, ConstraintStrings.AppName));
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
