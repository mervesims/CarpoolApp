package com.example.mervesimsek.vehicleapp.common;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.example.mervesimsek.vehicleapp.R;

/**
 * Created by mnmlondon2 on 13/04/2017.
 */

public class BaseController extends AppCompatActivity {

    protected Context currentContext;
    protected Toolbar toolbar;

    protected void customOnCreate(@Nullable Bundle savedInstanceState, int layoutResID,int toolbarResID) {
        this.currentContext = this;
        setContentView(layoutResID);
        toolbar = (Toolbar) findViewById(toolbarResID);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        toolbar.setTitle(getTitle());


    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.customOnCreate(savedInstanceState,0,0);
    }

    // TODO : Ekranın başka bir yerine dokunarak klavye kapatma
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev)
    {
        UiHelper.dispatchTouchEvent(ev, this);
        return super.dispatchTouchEvent(ev);
    }

//    protected CustomEditText getTextObject(Integer viewId) {
//        EditText _editText = (EditText) findViewById(viewId);
//        CustomEditText _customEditText = new CustomEditText(_editText.getContext());
//        _customEditText = (CustomEditText) _editText;
//        return _customEditText;
//    }



}