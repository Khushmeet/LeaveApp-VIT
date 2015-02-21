package com.developer.gdgvit.leaveapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by SELVAM on 22-Feb-15.
 */
public class Setpass extends Activity {
    EditText t1,t2;
    Button b1;
    int temp=0;




    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setpass);
        b1=(Button)findViewById(R.id.button14);
        t1=(EditText)findViewById(R.id.editText);
        t2=(EditText)findViewById(R.id.editText2);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if(t1.getText().toString().equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Empty Password Cannot Accepted :(", Toast.LENGTH_LONG).show();
                    temp=1;
                }
                else
                {
                    temp=0;
                }
                if(t2.getText().toString().equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Re Enter your password", Toast.LENGTH_LONG).show();
                    temp=1;
                }
                else
                {
                    temp=0;
                }


                if(t2.getText().toString().equals(t1.getText().toString())&& temp==0)
                {
                    temp=0;
                }
                else
                {
                    temp=1;
                    Toast.makeText(getApplicationContext(), "Invalid Password :(", Toast.LENGTH_LONG).show();
                }

                if(temp==0)
                {
                    Dblogin ob=new Dblogin(Setpass.this);
                    String send=t2.getText().toString();
                    ob.open();
                    ob.createEntry(send);
                    ob.close();

                    try {
                        Class<?> Ourclass = Class.forName("com.developer.gdgvit.leaveapp.login_activity");
                        Intent ourIntent = new Intent(Setpass.this, Ourclass);

                        startActivity(ourIntent);




                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }




            }
        });



    }
}
