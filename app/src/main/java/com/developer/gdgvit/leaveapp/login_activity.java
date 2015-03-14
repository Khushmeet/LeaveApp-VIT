package com.developer.gdgvit.leaveapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.developer.gdgvit.leaveapp.dataHandlers.CheckIdPass;
import com.developer.gdgvit.leaveapp.dataHandlers.CheckInternet;
import com.developer.gdgvit.leaveapp.syncAdaptors.LeaveAppSyncAdapter;

/**
 * Created by my-pc on 13-03-2015.
 */
public class login_activity extends Activity {

    EditText reg_text, password_text;
    Button login;
    private String reg, paswrd;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Intent init;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        reg_text = (EditText) findViewById(R.id.reg_box);
        password_text = (EditText) findViewById(R.id.pswrd_text);
        login = (Button) findViewById(R.id.login_btn);


        pref = getSharedPreferences("Check",MODE_PRIVATE);


    }


    public void onClickLogin(View view) {
        reg = reg_text.getText().toString();
        paswrd = password_text.getText().toString();
        if (reg.equals("") && paswrd.equals("")) {
            Toast.makeText(getBaseContext(), "Enter the fields", Toast.LENGTH_SHORT).show();
        }
        else
        {
            if (new CheckInternet(getBaseContext()).state())
            {

                Toast.makeText(getBaseContext(), "Logging in...", Toast.LENGTH_SHORT).show();
                new CheckIdPass(getBaseContext()).putData("reg_no", reg);
                new CheckIdPass(getBaseContext()).putData("pass",paswrd);
                LeaveAppSyncAdapter.syncImmediately(this);
                calling();
                finish();
            }
            else
            {
                Toast.makeText(getBaseContext(), "OOPS! Please connect to internet first", Toast.LENGTH_LONG).show();
            }
        }

    }

    public void calling(){
        init = new Intent(this,Home.class);
        startActivity(init);
    }


}
