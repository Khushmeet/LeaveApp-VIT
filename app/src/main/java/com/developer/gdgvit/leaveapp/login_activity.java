package com.developer.gdgvit.leaveapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by my-pc on 13-03-2015.
 */
public class login_activity extends Activity {
    EditText reg_text,password_text;
    Button login;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        reg_text = (EditText)findViewById(R.id.reg_box);
        password_text= (EditText)findViewById(R.id.pswrd_text);
        login = (Button)findViewById(R.id.login_btn);



    }
}
