package com.example.mervesimsek.vehicleapp.controller;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import com.example.mervesimsek.vehicleapp.R;

/**
 * Created by Merve Simsek on 27/02/2017.
 */

public class SplashController extends AppCompatActivity
{
    final Context context =this;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        TextView logosplash = (TextView)findViewById(R.id.logosplash);
        Typeface font = Typeface.createFromAsset(context.getAssets(),"fonts/gist.ttf");
        logosplash.setTypeface(font);

        Thread thread = new Thread()
        {
            @Override
            public void run()
            {
                try
                {
                    sleep(3000);
                    Intent intent = new Intent(getApplicationContext(),MainController.class);
                    startActivity(intent);
                    finish();

                }
                catch (Exception e) {
                    String akingundogdu = "";
                }
            }
        };
        thread.start();
    }
}
