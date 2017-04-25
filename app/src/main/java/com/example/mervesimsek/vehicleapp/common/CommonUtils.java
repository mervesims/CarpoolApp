package com.example.mervesimsek.vehicleapp.common;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.util.Random;

/**
 * Created by mnmlondon2 on 13/04/2017.
 */

public class CommonUtils {

    public static void dispatchTouchEvent(MotionEvent ev,Activity _this) {
        View view = _this.getCurrentFocus();
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
                ((InputMethodManager) _this.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow((_this.getWindow().getDecorView().getApplicationWindowToken()), 0);
        }
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

    public static void DisableEmojiCharacters(EditText _this) {
            _this.setFilters(new InputFilter[]{ emojifilter });
    }

    public static String getValueFromCursor(Cursor cursor, String columnName) {
        return cursor.getString(cursor.getColumnIndex(columnName));
    }
    public static int getRandomColor() {
        Random random = new Random();
        return Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));
    }

}


