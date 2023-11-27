package com.example.resturant_billing;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.example.resturant_billing.model.order_item;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context)
    {
        super(context,"Restaurant",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String create_table_query = "CREATE TABLE User(ID INTEGER PRIMARY KEY AUTOINCREMENT , Email VARCHAR(40) NOT NULL UNIQUE , Phone VARCHAR(10) NOT NULL , Password VARCHAR(30) NOT NULL , RESTAURANT VARCHAR(50) NOT NULL , OWNER VARCHAR(50) NOT NULL , GST VARCHAR(50) NOT NULL , ADDRESS TEXT NOT NULL)";
        sqLiteDatabase.execSQL(create_table_query);
        String create_food_table = "CREATE TABLE FOOD(ID INTEGER PRIMARY KEY AUTOINCREMENT , NAME TEXT NOT NULL , PRICE VARCHAR(3) NOT NULL , CATEGORY TEXT NOT NULL )";
        sqLiteDatabase.execSQL(create_food_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            onCreate(sqLiteDatabase);
    }
    public boolean checkEmail(String email)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM User WHERE Email = '"+email+"'";
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst())
        {
            return true;
        }
        else{
            return false;
        }
    }
    public boolean registerUserHelper(String email , String password , String contact , String gst , String owner , String address , String restaurant)
    {
        ContentValues content = new ContentValues();
        content.put("Email",email);
        content.put("Phone",contact);
        content.put("ADDRESS",address);
        content.put("GST",gst);
        content.put("OWNER",owner);
        content.put("RESTAURANT",restaurant);
        content.put("Password",password);
        SQLiteDatabase db = this.getWritableDatabase();
        long l = db.insert("User",null,content);
        if(l > 0 )
        {
            return true ;
        }
        else
        {
            return false ;
        }
    }
    public boolean checkLogin(String email , String password){
        String query = "SELECT * FROM User WHERE Email = '"+email+"' AND Password = '"+password+"'";
        Log.d("Jigar",query);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst()){

            return true ;
        }
        else
        {
            return false ;
        }
    }
    public boolean addItem(String name , String price , String category)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Log.d("A",name);
        Log.d("B",price);
        Log.d("C",category);
        ContentValues contentValues = new ContentValues();
        contentValues.put("NAME",name);
        contentValues.put("PRICE",price);
        contentValues.put("CATEGORY",category);
        long l = db.insert("FOOD",null,contentValues);
        if(l>0)
            return  true;
        else
            return false;
    }
    public ArrayList<ArrayList> getFood(){
        String query = "SELECT * FROM FOOD";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        ArrayList<ArrayList> allData = new ArrayList<>();
        if(cursor.moveToFirst())
        {
            do{
                ArrayList<String> data = new ArrayList<>();
                data.add(cursor.getString(0));
                data.add(cursor.getString(1));
                data.add(cursor.getString(2));
                data.add(cursor.getString(3));
                data.add("0");
                data.add("0");
                allData.add(data);

            }while(cursor.moveToNext());
        }
        return  allData;
    }
    public int getUserId(String email , String password)
    {
        String query = "SELECT * FROM User WHERE Email = '"+email+"' AND Password = '"+password+"'";
        SQLiteDatabase db =  this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst())
        {
            return Integer.parseInt(cursor.getString(0));
        }
        return 0;
    }
    public boolean updateUser(int id , ContentValues content)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        int res = db.update("User",content,"ID=?",new String[]{ String.valueOf(id)});
        if(res > 0)
            return true ;
        else
            return false;
    }
    public ArrayList<String> getDetails(int id)
    {
        String query = "SELECT * FROM User Where ID = '"+id+"'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        ArrayList<String> details = new ArrayList<>();
        if(cursor.moveToFirst())
        {
            for(int i = 0 ; i < cursor.getColumnCount() ; i++)
            {
                details.add(cursor.getString(i));
            }
        }
        return details;
    }


}
