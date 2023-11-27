package com.example.resturant_billing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button get_started=(Button) findViewById(R.id.getstarted);

        get_started.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences = getSharedPreferences("id",MODE_PRIVATE);
                int i = preferences.getInt("userid",0);
                if(i==0)
                {
                    startActivity(new Intent(MainActivity.this,loginActivity.class));
                }
                else
                {
                    startActivity(new Intent(MainActivity.this, MainLayout.class));
                }
            }
        });


    }
}