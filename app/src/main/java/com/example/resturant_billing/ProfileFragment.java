package com.example.resturant_billing;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_profile, container, false);
        SharedPreferences preferences = getActivity().getSharedPreferences("id", Context.MODE_PRIVATE);
        int id = preferences.getInt("userid",0);
        EditText name = view.findViewById(R.id.input_name);
        EditText email = view.findViewById(R.id.input_email);
        EditText phone = view.findViewById(R.id.input_phone);
        EditText password = view.findViewById(R.id.input_password);
        EditText gst = view.findViewById(R.id.input_gst);
        EditText rname = view.findViewById(R.id.input_restaurantname);
        EditText rAddress= view.findViewById(R.id.input_restaurantaddress);
        DBHelper dbHelper = new DBHelper(getContext());
        ArrayList<String> details = dbHelper.getDetails(id);
        email.setText(details.get(1));
        phone.setText(details.get(2));
        password.setText(details.get(3));
        rname.setText(details.get(4));
        gst.setText(details.get(6));
        name.setText(details.get(5));
        rAddress.setText(details.get(7));
        view.findViewById(R.id.btnAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(email.getText().toString().trim().isEmpty())
                {
                    Toast.makeText(getContext(),"Email is Required",Toast.LENGTH_LONG).show();
                }
                else if(phone.getText().toString().trim().isEmpty())
                {
                    Toast.makeText(getContext(),"Contact is Required",Toast.LENGTH_LONG).show();
                }
                else if(password.getText().toString().trim().isEmpty())
                {
                    Toast.makeText(getContext(),"Password is Required.",Toast.LENGTH_LONG).show();
                }
                else if(rname.getText().toString().trim().isEmpty())
                {
                    Toast.makeText(getContext(),"Name is Required",Toast.LENGTH_LONG).show();
                }
                else if(gst.getText().toString().trim().isEmpty())
                {
                    Toast.makeText(getContext(),"GST Number is Required",Toast.LENGTH_LONG).show();
                }
                else if(name.getText().toString().trim().isEmpty())
                {
                    Toast.makeText(getContext(),"Owner Name is Required",Toast.LENGTH_LONG).show();
                }
                else if(phone.getText().toString().length() != 10)
                {
                    Toast.makeText(getContext(),String.valueOf(phone.toString()),Toast.LENGTH_LONG).show();
                }
                else if(phone.getText().toString().startsWith("1") || phone.toString().startsWith("2") || phone.toString().startsWith("3") || phone.toString().startsWith("4") || phone.toString().startsWith("5"))
                {
                    Toast.makeText(getContext(),"Enter Valid Contact Number",Toast.LENGTH_LONG).show();
                }
                else
                {
                    ContentValues contentValue = new ContentValues();
                    contentValue.put("Email",String.valueOf(email.getText()));
                    contentValue.put("Phone",String.valueOf(phone.getText()));
                    contentValue.put("Password",String.valueOf(password.getText()));
                    contentValue.put("RESTAURANT",String.valueOf(rname.getText()));
                    contentValue.put("OWNER",String.valueOf(name.getText()));
                    contentValue.put("GST",String.valueOf(gst.getText()));
                    contentValue.put("ADDRESS",String.valueOf(rAddress.getText()));
                    boolean res = dbHelper.updateUser(id,contentValue);
                    if(res){
                        Toast.makeText(getContext(),"Your Profile is Updated Successfully",Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Toast.makeText(getContext(),"Error while updating your profile !! ",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        return view;
    }
}