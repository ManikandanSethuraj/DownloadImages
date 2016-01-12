package com.manikandansethuraj.downloadimages;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by ManikandanSethuraj on 2016-01-11.
 */
public class L {
    public static void m(String message){
        Log.d("Log message",message);
    }
    public static void s(Context context,String message){
        Toast.makeText(context,message, Toast.LENGTH_SHORT).show();
    }
}
