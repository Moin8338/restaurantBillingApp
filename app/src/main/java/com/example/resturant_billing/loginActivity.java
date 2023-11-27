package com.example.resturant_billing;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

public class loginActivity extends AppCompatActivity {

    EditText useremail ;
    EditText userPassword ;
    AppCompatButton login ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        login = findViewById(R.id.login);
        TextView gotoLogin=(TextView) findViewById(R.id.signupbtn);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                useremail = findViewById(R.id.emailInput);
                userPassword = findViewById(R.id.passwordInput);
                String email = useremail.getText().toString();
                String password = userPassword.getText().toString();
                if (email.isEmpty()) {
                    Toast.makeText(loginActivity.this, "Email is Required.", Toast.LENGTH_SHORT).show();
                } else if (password.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Password is Required.", Toast.LENGTH_SHORT).show();
                } else {
                    DBHelper dbHelper = new DBHelper(getApplicationContext());
                    boolean result = dbHelper.checkLogin(email, password);
                    if (result) {
                        Intent intent = new Intent(getApplicationContext(), MainLayout.class);

                        SharedPreferences preferences = getSharedPreferences("id",MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        int id = dbHelper.getUserId(email,password);
                        Log.d("w",String.valueOf(id));
                        editor.putInt("userid",id);
                        editor.apply();
                        startActivity(intent);
                        finish();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Invalid Login", Toast.LENGTH_LONG).show();
                    }
                }

            }
        });
        gotoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(loginActivity.this, registerActivity.class);
                startActivity(intent);
            }
        });


    }
}
