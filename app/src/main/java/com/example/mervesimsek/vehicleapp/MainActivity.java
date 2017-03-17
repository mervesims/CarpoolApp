package com.example.mervesimsek.vehicleapp;



import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.net.Uri;
import android.os.Bundle;

import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.TextView;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView logo = (TextView) findViewById(R.id.logo);
        Typeface font = Typeface.createFromAsset(context.getAssets(), "fonts/gist.ttf");
        logo.setTypeface(font);











        ShapeDrawable drawable = new ShapeDrawable(new OvalShape());
        drawable.getPaint().setColor(getResources().getColor(R.color.white));


        final FloatingActionButton actionA = (FloatingActionButton) findViewById(R.id.action_a);
        actionA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intcars = new Intent(MainActivity.this, ItemListActivity.class);
                startActivity(intcars);
            }
        });

        final FloatingActionButton actionB = (FloatingActionButton) findViewById(R.id.action_b);
        actionB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intgo = new Intent(MainActivity.this, AddCarActivity.class);
                startActivity(intgo);
            }
        });



  /*
        ImageView icon = new ImageView(this);
        icon.setImageResource(R.drawable.plusneww);



        final FloatingActionButton fab = new FloatingActionButton.Builder(this).setContentView(icon).build();
        SubActionButton.Builder builder = new SubActionButton.Builder(this);

        ImageView addcarIcon = new ImageView(this);
        addcarIcon.setImageResource(R.drawable.plusnew);
        SubActionButton addcarBtn = builder.setContentView(addcarIcon).build();

        ImageView shareIcon = new ImageView(this);
        shareIcon.setImageResource(R.drawable.share);
        SubActionButton shareBtn = builder.setContentView(shareIcon).build();

        ImageView infoIcon = new ImageView(this);
        infoIcon.setImageResource(R.drawable.infovar);
        SubActionButton infoBtn = builder.setContentView(infoIcon).build();

        final FloatingActionMenu fam = new FloatingActionMenu.Builder(this)
                .addSubActionView(infoBtn)
                .addSubActionView(shareBtn)
                .addSubActionView(addcarBtn)
                .attachTo(fab)
                .build();
        infoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browser = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.appsims.com"));
                startActivity(browser);
            }
        });
        shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = " ";
                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("text/plain");
                share.putExtra(Intent.EXTRA_TEXT, message);
                startActivity(Intent.createChooser(share, "Carpool"));
            }
        });
        addcarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentadd = new Intent(MainActivity.this,AddCarActivity.class);
                startActivity(intentadd);
            }
        });


/*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intgo = new Intent(MainActivity.this, AddCarActivity.class);
                startActivity(intgo);
            }
        }); */

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if(id == R.id.nav_main) {

            Intent intentmain = new Intent(MainActivity.this, MainActivity.class);
            startActivity(intentmain);

        } else if (id == R.id.nav_car) {

            Intent intentitem = new Intent(MainActivity.this, ItemListActivity.class);
            startActivity(intentitem);

        } else if (id == R.id.nav_add) {

            Intent intentadd = new Intent(MainActivity.this,AddCarActivity.class);
            startActivity(intentadd);

        } else if (id == R.id.nav_aboutappsims) {
            Intent browser = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.appsims.com"));
            startActivity(browser);

        } else if (id == R.id.nav_share) {
            String message = " ";
            Intent share = new Intent(Intent.ACTION_SEND);
            share.setType("text/plain");
            share.putExtra(Intent.EXTRA_TEXT, message);

            startActivity(Intent.createChooser(share, "Carpool"));

        } else if (id == R.id.nav_send) {

            String message = " ";
            Intent share = new Intent(Intent.ACTION_SEND);
            share.setType("text/plain");
            share.putExtra(Intent.EXTRA_TEXT, message);

            startActivity(Intent.createChooser(share, "Carpool"));

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



}
