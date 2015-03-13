package com.developer.gdgvit.leaveapp.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.developer.gdgvit.leaveapp.Home;
import com.developer.gdgvit.leaveapp.R;
import com.developer.gdgvit.leaveapp.dataHandlers.Apply_Leave_Service;


/**
 * Created by pk on 29/12/14.
 *
 * New leave apply fragment..
 *
 */
public class Apply_New_Leave_Fragment extends Fragment {

    Spinner applySpinner, leaveSpinner;

    ArrayAdapter<CharSequence> applyAdapter, leaveAdapter;

    EditText exit, entry, place, reason;

    Button apply;
    String first,second;
    int find1=0,find2=0,temp=0;
    public Apply_New_Leave_Fragment()
    {
        //Required default constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_apply, container, false);

        applySpinner = (Spinner) rootView.findViewById(R.id.applyToValueSpinner);
        leaveSpinner = (Spinner) rootView.findViewById(R.id.leaveTypeValueSpinner);

        applyAdapter = ArrayAdapter.createFromResource(
                getActivity(),
                R.array.applyToList,
                android.R.layout.simple_spinner_item
        );

        applyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        leaveAdapter = ArrayAdapter.createFromResource(
                getActivity(),
                R.array.leaveTypeList,
                android.R.layout.simple_spinner_item
        );

        leaveAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        applySpinner.setAdapter(applyAdapter);
        leaveSpinner.setAdapter(leaveAdapter);

        exit = (EditText) rootView.findViewById(R.id.exitOnValue);
        entry = (EditText) rootView.findViewById(R.id.entryOnValue);
        place = (EditText) rootView.findViewById(R.id.placeToValue);
        reason = (EditText) rootView.findViewById(R.id.reasonValue);

        apply = (Button) rootView.findViewById(R.id.applyNowBtn);

        apply.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                find1=0;
                find2=0;
                temp=0;

                if(exit.getText().toString().equals("")) {
                    Toast.makeText(getActivity(), "Please provide the exit date", Toast.LENGTH_LONG).show();

                }
                else if(entry.getText().toString().equals(""))
                    Toast.makeText(getActivity(), "Please provide the entry date", Toast.LENGTH_LONG).show();
                else if(place.getText().toString().equals(""))
                    Toast.makeText(getActivity(), "Please provide the address", Toast.LENGTH_LONG).show();
                else if(reason.getText().toString().equals(""))
                    Toast.makeText(getActivity(), "Please provide the reason for leave", Toast.LENGTH_LONG).show();
                else
                {
                    first=exit.getText().toString();
                    second=entry.getText().toString();
                    if(first.length()==10)
                    {
                        find1=1;
                    }
                    if (second.length()==10)
                    {
                        find2=1;
                    }
                    if(first.length()==8)
                    {
                        find1=2;
                        Toast.makeText(getActivity(), "Enter Two Digits for Month and Date On Exit", Toast.LENGTH_LONG).show();
                    }
                    if (second.length()==8)
                    {   find2=2;
                        Toast.makeText(getActivity(), "Enter Two Digits for Month and Date On Entry", Toast.LENGTH_LONG).show();
                    }
                    if((find1==0)||(find2==0))
                    {

                        Toast.makeText(getActivity(), "Enter Date Properly", Toast.LENGTH_LONG).show();
                    }

                }
                if(find1==1)
                {
                    try {
                        int d = Integer.parseInt(first.substring(0, 2));
                        int m = Integer.parseInt(first.substring(3, 5));
                        int y = Integer.parseInt(first.substring(6, 10));
                        if(m==02)
                        {
                            temp=1;
                            if ((d>00) && (d<29))
                            {

                            }
                             else
                            {
                                find1=0;
                                if((d==29) || (d==30))
                                    Toast.makeText(getActivity(), "This is February Month", Toast.LENGTH_LONG).show();
                                else
                                    Toast.makeText(getActivity(), "Invalid Month on Exit", Toast.LENGTH_LONG).show();
                            }

                        }
                        if(temp==0)
                        {


                        if ((d>00) && (d<32))
                        {

                        }
                        else
                        {
                            Toast.makeText(getActivity(), "Invalid Date on Exit", Toast.LENGTH_LONG).show();
                            find1=0;
                        }
                        }

                        if((m>=01) && (m<=12))
                        {

                        }
                        else
                        {
                            Toast.makeText(getActivity(), "Invalid Month on Exit", Toast.LENGTH_LONG).show();
                            find1=0;
                        }





                        if((y>=2015) && (y<=2030))
                        {

                        }
                        else
                        {
                            Toast.makeText(getActivity(), "Invalid year on Exit", Toast.LENGTH_LONG).show();
                            find1=0;
                        }


                    }catch(Exception e)
                    {
                        Toast.makeText(getActivity(), "Invalid Entry on Exit", Toast.LENGTH_LONG).show();
                        find1=0;
                    }
                }
                if(find2==1)
                {
                    try {
                        int d = Integer.parseInt(second.substring(0, 2));
                        int m = Integer.parseInt(second.substring(3, 5));
                        int y = Integer.parseInt(second.substring(6, 10));
                        if(m==02)
                        {
                            temp=1;
                            if ((d>00) && (d<29))
                            {

                            }
                            else
                            {
                                find1=0;
                                if((d==29) || (d==30))
                                Toast.makeText(getActivity(), "This is February Month", Toast.LENGTH_LONG).show();
                                else
                                    Toast.makeText(getActivity(), "Invalid Month on Entry", Toast.LENGTH_LONG).show();

                            }

                        }
                        if(temp==0)
                        {


                            if ((d>00) && (d<32))
                            {

                            }
                            else
                            {
                                Toast.makeText(getActivity(), "Invalid Date on Entry", Toast.LENGTH_LONG).show();
                                find1=0;
                            }
                        }

                        if((m>=01) && (m<=12))
                        {

                        }
                        else
                        {
                            Toast.makeText(getActivity(), "Invalid Month on Entry", Toast.LENGTH_LONG).show();
                            find1=0;
                        }





                        if((y>=2015) && (y<=2030))
                        {

                        }
                        else
                        {
                            Toast.makeText(getActivity(), "Invalid year on Entry", Toast.LENGTH_LONG).show();
                            find1=0;
                        }


                    }catch(Exception e)
                    {
                        Toast.makeText(getActivity(), "Invalid Entry on Entry", Toast.LENGTH_LONG).show();
                        find2=0;
                    }
                }




                if((find1==1) && (find2==1))

                {

                    Intent intent = new Intent(getActivity(), Apply_Leave_Service.class);

                    intent.putExtra(Home.AUTH_TAG, applySpinner.getSelectedItem().toString());
                    intent.putExtra(Home.LEAVE_TYPE_TAG, leaveSpinner.getSelectedItem().toString());
                    intent.putExtra(Home.EXIT_ON_TAG, exit.getText().toString());
                    intent.putExtra(Home.ENTRY_ON_TAG, entry.getText().toString());
                    intent.putExtra(Home.PLACE_TAG, place.getText().toString());
                    intent.putExtra(Home.REASON_TAG, reason.getText().toString());
                    getActivity().startService(intent);
                    Toast.makeText(getActivity(), "Applying leave...please be patient..", Toast.LENGTH_LONG).show();

                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.replace(R.id.container, new Leave_List_Fragment(), "home");
                    ft.addToBackStack(null);
                    ft.commit();
                }
            }
        });

        return rootView;
    }




}

