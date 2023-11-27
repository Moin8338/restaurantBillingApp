package com.example.resturant_billing;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class TablesFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public TablesFragment() {
        // Required empty public constructor
    }

    public static TablesFragment newInstance(String param1, String param2) {
        TablesFragment fragment = new TablesFragment();
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

        View view = inflater.inflate(R.layout.fragment_tables, container, false);

        // Find the GridLayout
        GridLayout TableContainer = view.findViewById(R.id.TableGrid);

        //convert Dp to Pixel

        int widthInDp = 130;
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        float widthInPx = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, widthInDp, displayMetrics);

        int heightInDp = 120;
        float heightInPx = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, heightInDp, displayMetrics);


        // Add child views to the GridLayout

        // Example: Add a AppCompactButton

        for(int i=1;i<=12;i++){
            AppCompatButton OneTable = new AppCompatButton(getContext());
            OneTable.setId(i);
            OneTable.setText(Integer.toString(i));
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams((int) widthInPx, (int) heightInPx);
            OneTable.setLayoutParams(layoutParams);
            OneTable.setGravity(Gravity.CENTER);
            OneTable.setTextSize(TypedValue.COMPLEX_UNIT_DIP,50);

            OneTable.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Handle button click
                    int buttonId = v.getId();
                    Bundle args = new Bundle();
                    ItemAddFragment itemAddFragment=new ItemAddFragment();
                    args.putString("tableNo", Integer.toString(buttonId));
                    itemAddFragment.setArguments(args);
                    replaceFragment(itemAddFragment);
                }
            });

        //    TableContainer.addView(OneTable);
        }




        // ... Add more child views as needed

        return view;
    }

    private  void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }

}

