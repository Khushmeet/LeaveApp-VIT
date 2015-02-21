package com.developer.gdgvit.leaveapp;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

/**
 * Created by SELVAM on 10-02-2015.
 */
public class login_activity extends Activity {
    Button b1,b2,b3,b4,b5,b6,b7,b8,b9,b0,b11,b12,b13;
    TextView t1,t2;
    public static Activity fa;
    Intent i;
    public static String cname="Home";
    boolean fla=true;

    int temp1=0;
    login_activity ob;
    @Override

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        ob= new login_activity();
        temp1++;
        fa = this;


        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        final Animation animAlpha = AnimationUtils.loadAnimation(this, R.anim.anim_alpha);
        b11=(Button)findViewById(R.id.button11);
        b1=(Button)findViewById(R.id.button);
        b2=(Button)findViewById(R.id.button2);
        b3=(Button)findViewById(R.id.button3);
        b4=(Button)findViewById(R.id.button4);
        b5=(Button)findViewById(R.id.button5);
        b6=(Button)findViewById(R.id.button6);
        b7=(Button)findViewById(R.id.button7);
        b8=(Button)findViewById(R.id.button8);
        b9=(Button)findViewById(R.id.button9);
        b0=(Button)findViewById(R.id.button10);
        b12=(Button)findViewById(R.id.button12);
        //b13=(Button)findViewById(R.id.button13);
        t1=(TextView)findViewById(R.id.textView2);
        t2=(TextView)findViewById(R.id.textView3);
        t2.setVisibility(View.INVISIBLE);
        //t1.setText("");
        b11.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(t1.getText().toString().equals("1234"))
                {

                    v.startAnimation(animAlpha);



                    try {
                        Class<?> Ourclass = Class.forName("com.developer.gdgvit.leaveapp."+ob.cname);
                        Intent ourIntent = new Intent(login_activity.this, Ourclass);
                        fla=false;
                        startActivity(ourIntent);




                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }



                else
                {
                    v.startAnimation(animAlpha);
                    t2.setVisibility(View.VISIBLE);
                    t1.setText("");
                }

            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                t2.setVisibility(View.INVISIBLE);
                if(t1.getText().toString().length()<4)
                {

                    v.startAnimation(animAlpha);


                    new Append(b1,t1);
                }

            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                t2.setVisibility(View.INVISIBLE);
                if(t1.getText().toString().length()<4)
                {
                    v.startAnimation(animAlpha);
                    new Append(b2,t1);
                }

            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                t2.setVisibility(View.INVISIBLE);
                if(t1.getText().toString().length()<4)
                {
                    v.startAnimation(animAlpha);
                    new Append(b3,t1);
                }

            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                t2.setVisibility(View.INVISIBLE);
                if(t1.getText().toString().length()<4)
                {
                    v.startAnimation(animAlpha);
                    new Append(b4,t1);
                }

            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                t2.setVisibility(View.INVISIBLE);
                if(t1.getText().toString().length()<4)
                {
                    v.startAnimation(animAlpha);
                    new Append(b5,t1);
                }

            }
        });
        b6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                t2.setVisibility(View.INVISIBLE);
                if(t1.getText().toString().length()<4)
                {
                    v.startAnimation(animAlpha);
                    new Append(b6,t1);
                }

            }
        });
        b7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                t2.setVisibility(View.INVISIBLE);
                if(t1.getText().toString().length()<4)
                {
                    v.startAnimation(animAlpha);
                    new Append(b7,t1);
                }

            }
        });
        b8.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                t2.setVisibility(View.INVISIBLE);
                if(t1.getText().toString().length()<4)
                {
                    v.startAnimation(animAlpha);
                    new Append(b8,t1);
                }

            }
        });
        b9.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                t2.setVisibility(View.INVISIBLE);
                if(t1.getText().toString().length()<4)
                {
                    v.startAnimation(animAlpha);
                    new Append(b9,t1);
                }

            }
        });
        b0.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                t2.setVisibility(View.INVISIBLE);
                if(t1.getText().toString().length()<4)
                {
                    v.startAnimation(animAlpha);
                    new Append(b0,t1);
                }

            }
        });
        b12.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                t2.setVisibility(View.INVISIBLE);
                v.startAnimation(animAlpha);
                if(!t1.getText().toString().equals(""))

                {
                    v.startAnimation(animAlpha);
                    new Append(t1);
                }

            }
        });
       /* b13.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //t2.setVisibility(View.INVISIBLE);
                //v.startAnimation(animAlpha);

            }
        }); */

    }


    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        temp1++;
    }



    public login_activity run() {






        return ob;


    }
}


