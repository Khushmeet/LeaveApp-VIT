package com.developer.gdgvit.leaveapp.dataHandlers;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;

import com.developer.gdgvit.leaveapp.LeaveAppClass;

/**
 * Created by pk on 26/12/14.
 *
 * Utility class to check for id and pass data present in the settings.
 *
 */
public class CheckIdPass {

    Context context;
    SharedPreferences pref;
    public CheckIdPass(Context context)
    {
        this.context = context;
    }

    public boolean checkIdPas()
    {
        pref  = PreferenceManager.getDefaultSharedPreferences(context);
        String reg = pref.getString("reg_no","");  //Registration Number
        String pas = pref.getString("pass", ""); //Password

        return !(reg.equals("") || pas.equals(""));

    }

    public String getData(String key)
    {
        pref  = PreferenceManager.getDefaultSharedPreferences(context);
        return pref.getString(key, "");
    }

    public String putData(String key, String value){
        pref  = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(key,value);
        editor.commit();
        return null;
    }

}
