package com.example.resturant_billing;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

public class registerActivity extends AppCompatActivity {
    EditText email ;
    EditText contact ;
    EditText password ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        TextView goLogin=(TextView) findViewById(R.id.gotoLogin);
        AppCompatButton register=(AppCompatButton) findViewById(R.id.SignUpbtn);
        email = findViewById(R.id.registerEmail);
        contact = findViewById(R.id.registerPhone);
        password = findViewById(R.id.registerPassword);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String useremail = email.getText().toString();
                String userContact = contact.getText().toString();
                String userPassword = password.getText().toString();
                DBHelper dbHelper = new DBHelper(getApplicationContext());
                if(useremail.trim().isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Enter Valid Email",Toast.LENGTH_LONG).show();
                }
                else if(!useremail.contains("@"))
                {
                    Toast.makeText(getApplicationContext(),"Enter Valid Email",Toast.LENGTH_LONG).show();
                }
                else if(userContact.trim().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Enter Valid Contact",Toast.LENGTH_LONG).show();
                }
                else if(userContact.trim().length()!=10){
                    Toast.makeText(getApplicationContext(),"Enter 10 Digit Contact Number",Toast.LENGTH_LONG).show();
                }
                else if(userPassword.trim().isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Password is Required.",Toast.LENGTH_LONG).show();
                }
                else if(dbHelper.checkEmail(useremail)){
                    Toast.makeText(getApplicationContext(),"Email is already used.",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Intent registerNow=new Intent(registerActivity.this,businessDetail.class);
                    registerNow.putExtra("email",email.getText().toString());
                    registerNow.putExtra("contact",contact.getText().toString());
                    registerNow.putExtra("password",password.getText().toString());
                    startActivity(registerNow);
                    finish();
                }
            }
        });

        goLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(registerActivity.this, loginActivity.class);
                startActivity(intent);
            }
        });



    }
}
