package com.example.resturant_billing;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

public class businessDetail extends AppCompatActivity {

    EditText gst ;
    EditText owner ;
    EditText restaurant ;
    EditText address ;
    DBHelper dbHelper ;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.businessdetail);
        AppCompatButton setBdetail=(AppCompatButton) findViewById(R.id.SetBussinessDetail);
        gst = findViewById(R.id.restaurantGst);
        owner = findViewById(R.id.ownerName);
        restaurant = findViewById(R.id.restaurantName);
        address = findViewById(R.id.RestaurantAddress);
        setBdetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String gst_number = gst.getText().toString();
                String owner_name = owner.getText().toString();
                String restaurant_name = restaurant.getText().toString();
                String restaurant_address = address.getText().toString();
                dbHelper = new DBHelper(getApplicationContext());
                if(gst_number.length()!= 15){
                    Toast.makeText(getApplicationContext(), "Enter Valid GST Number", Toast.LENGTH_SHORT).show();
                }
                else if(gst_number.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Enter GST Number ",Toast.LENGTH_LONG).show();
                }
                else if(owner_name.isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Enter Owner Name ",Toast.LENGTH_LONG).show();
                }
                else if(owner_name.length() < 4){
                    Toast.makeText(getApplicationContext(),"Enter Valid Owner Name ",Toast.LENGTH_LONG).show();
                }
                else if(restaurant_name.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Enter Restaurant Name ",Toast.LENGTH_LONG).show();
                }
                else if(restaurant_name.length() < 3 )
                {
                    Toast.makeText(getApplicationContext(),"Enter Valid Restaurant Name ",Toast.LENGTH_LONG).show();
                }
                else if(restaurant_address.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Enter Valid Name ", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent getData = getIntent();
                    String email = getData.getStringExtra("email");
                    String contact = getData.getStringExtra("contact");
                    String password = getData.getStringExtra("password");
                    Boolean result  = dbHelper.registerUserHelper(email,password,contact,gst_number,owner_name,restaurant_address,restaurant_name);
                    Intent gotoMainLayout = new Intent(businessDetail.this, MainLayout.class);
                    startActivity(gotoMainLayout);
                    finish();
                }
            }
        });
    }
}
