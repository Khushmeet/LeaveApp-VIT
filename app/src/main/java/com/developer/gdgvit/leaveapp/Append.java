package com.developer.gdgvit.leaveapp;

import android.app.Activity;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by SELVAM on 15-Feb-15.
 */
public class Append extends Activity {
    Append(Button b,TextView t)
    {

        t.setText(t.getText().toString()+b.getText().toString());
    }
    Append(TextView t)
    {
        t.setText(t.getText().toString().substring(0,t.getText().toString().length()-1));
    }
}
