package com.example.resturant_billing;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

public class otpActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.getotp);

        AppCompatButton verifyMobile=(AppCompatButton) findViewById(R.id.verify);

        verifyMobile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotoGetBussinessDetail=new Intent(otpActivity.this, businessDetail.class);
                startActivity(gotoGetBussinessDetail);
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(otpActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
            }
        });
        TextView otp =(TextView) findViewById(R.id.otp);
        EditText otp_box_1 =(EditText) findViewById(R.id.otp_box_1);
        EditText otp_box_2 =(EditText) findViewById(R.id.otp_box_2);
        EditText otp_box_3 =(EditText) findViewById(R.id.otp_box_3);
        EditText otp_box_4 =(EditText) findViewById(R.id.otp_box_4);
        EditText otp_box_5 =(EditText) findViewById(R.id.otp_box_5);
        EditText otp_box_6 =(EditText) findViewById(R.id.otp_box_6);
        otp.setText(Html.fromHtml(getResources().getString(R.string.otp1)));
        otp_box_1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if(editable!=null){
                    if(editable.length()==1)
                        otp_box_2.requestFocus();
                }
            }
        });
        otp_box_2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable!=null){
                    if(editable.length()==1)
                        otp_box_3.requestFocus();
                    if(editable.length()==0)
                        otp_box_1.requestFocus();
                }
            }


        });
        otp_box_3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable!=null){
                    if(editable.length()==1)
                        otp_box_4.requestFocus();
                    if(editable.length()==0)
                        otp_box_2.requestFocus();

                }
            }
        });
        otp_box_4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable!=null){
                    if(editable.length()==1)
                        otp_box_5.requestFocus();
                    if(editable.length()==0)
                        otp_box_3.requestFocus();
                }
            }
        });
        otp_box_5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable!=null){
                    if(editable.length()==1)
                        otp_box_6.requestFocus();
                    if(editable.length()==0)
                        otp_box_4.requestFocus();
                }
            }
        });



        otp_box_6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable!=null){
                    if(editable.length()==0)
                        otp_box_5.requestFocus();
                }
            }
        });




    }
}
