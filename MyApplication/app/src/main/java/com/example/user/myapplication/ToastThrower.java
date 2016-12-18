package com.example.user.myapplication;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Acrylon3 on 13/12/2016.
 */

public class ToastThrower {
    public static void throwToast(Context context, String s){
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context,s, duration);
        toast.show();
    }
}
