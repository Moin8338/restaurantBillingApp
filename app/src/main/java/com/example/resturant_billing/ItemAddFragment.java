package com.example.resturant_billing;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.resturant_billing.model.*;

import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ItemAddFragment extends Fragment{ //  implements View.OnClickListener

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    public static ArrayList<ArrayList> order = new ArrayList<>();

    private static final String ARG_PARAM2 = "param2";

    //    List food item
    private List<order_item> foodList= new ArrayList<order_item>();
    private List<order_item> finalOrder= new ArrayList<order_item>();

    private String mParam1;
    private String mParam2;


    public ItemAddFragment() {
        // Required empty public constructor
    }



    public static ItemAddFragment newInstance(String param1, String param2) {
        ItemAddFragment fragment = new ItemAddFragment();
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
        View view = inflater.inflate(R.layout.fragment_item_add, container, false);


        DBHelper db = new DBHelper(getContext());
        ArrayList<ArrayList> data = db.getFood();
        for(int i = 0 ; i < data.size() ; i++)
        {
            foodList.add(new order_item(Integer.parseInt((String) data.get(i).get(0)),data.get(i).get(1).toString(),Integer.parseInt(data.get(i).get(2).toString()),data.get(i).get(3).toString(),0,0));
        }
//        foodList.add(new order_item(11,"Pizza Margherita",180,"pizza",0,180));
//        foodList.add(new order_item(12,"Pizza Vegetarian",175,"pizza",0,175*2));
//        foodList.add(new order_item(13,"Veggie Burger",120,"burger",0,120));
//        foodList.add(new order_item(14,"Cheeseburger",140,"burger",0,420));
//        foodList.add(new order_item(15,"Greek Salad",220,"salad",0,440));
//        foodList.add(new order_item(16,"Grilled Cheese",60,"sandwich",0,240));
//        foodList.add(new order_item(17,"Grilled Salmon",180,"seafood",0,180));
//        foodList.add(new order_item(18,"Spicy Chicken Curry",180,"special",0,180));
//        foodList.add(new order_item(19,"Paneer Tikka Masala",220,"special",0,220));
//        foodList.add(new order_item(20,"Cola",30,"Beverages",0,60));
//        foodList.add(new order_item(21,"Mango Lassi",60,"Beverages",0,60));
//        foodList.add(new order_item(22,"Mozzarella Sticks",60,"Appetizers",0,199));
//        foodList.add(new order_item(23,"Nachos",60,"Appetizers",0,179));
//        foodList.add(new order_item(24,"Bruschetta",60,"Appetizers",0,169));
//        foodList.add(new order_item(25,"Chocolate Cake",60,"Desserts",0,80));
//        foodList.add(new order_item(26,"Vanilla Ice Cream",60,"Desserts",0,50));
//
        GridLayout foodItemContainer=(GridLayout) view.findViewById(R.id.foodItemGrid);

        for (order_item item : foodList){
            // Create the main LinearLayout
            LinearLayout mainLayout = new LinearLayout(getContext());
            mainLayout.setBackground(getResources().getDrawable(R.drawable.itemcardbkg));
            mainLayout.setOrientation(LinearLayout.VERTICAL);
            mainLayout.setId(item.getId());
            mainLayout.setClickable(true);
            mainLayout.setPadding(
                    dpToPx(5),
                    dpToPx(5),
                    dpToPx(5),
                    dpToPx(5)
            );
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    dpToPx(150),
                    dpToPx(130)
            );

            layoutParams.setMargins(dpToPx(10),dpToPx(10),dpToPx(10),dpToPx(10));

            mainLayout.setLayoutParams(layoutParams);
            mainLayout.setGravity(View.TEXT_ALIGNMENT_CENTER);
            mainLayout.setOrientation(LinearLayout.VERTICAL);

            // Create the TextView for the Food Item Name
            TextView foodItemTextView = new TextView(getContext());
            foodItemTextView.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    dpToPx(80)
            ));
            foodItemTextView.setGravity(Gravity.CENTER_VERTICAL);
            foodItemTextView.setPadding(dpToPx(5), 0, 0, 0);
            foodItemTextView.setTextSize(17);
            foodItemTextView.setTextColor(getResources().getColor(R.color.white));
            foodItemTextView.setText(item.getName());

            // Create the horizontal line
            View horizontalLine = new View(getContext());
            horizontalLine.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    dpToPx(1)
            ));
            horizontalLine.setBackgroundColor(getResources().getColor(R.color.white));
            ((LinearLayout.LayoutParams) horizontalLine.getLayoutParams()).gravity = Gravity.CENTER_VERTICAL;

            // Create the nested LinearLayout for the EditText and TextViews
            LinearLayout nestedLayout = new LinearLayout(getContext());
            nestedLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    dpToPx(40)
            ));
            nestedLayout.setPadding(0,dpToPx(1),0,dpToPx(1));
            nestedLayout.setGravity(Gravity.CENTER);
            nestedLayout.setOrientation(LinearLayout.HORIZONTAL);

            // Create the EditText
            EditText editText = new EditText(getContext());
            editText.setLayoutParams(new LinearLayout.LayoutParams(
                    dpToPx(40),
                    dpToPx(30)
            ));
            editText.setPadding(0,0,0,0);
            editText.setGravity(Gravity.CENTER);
            editText.setBackground(getResources().getDrawable(R.drawable.edittexttransparentbkg));
            editText.setTextColor(getResources().getColor(R.color.white));
            editText.setTextSize(15);
            editText.setText(Integer.toString(item.getQty()));

            // Create the first TextView
            TextView firstTextView = new TextView(getContext());
            firstTextView.setLayoutParams(new LinearLayout.LayoutParams(
                    dpToPx(30),
                    dpToPx(30)
            ));
            firstTextView.setGravity(Gravity.CENTER);
            firstTextView.setTextColor(getResources().getColor(R.color.white));
            firstTextView.setTextSize(20);
            firstTextView.setText("--");


            //decrease the quantity of item---------------------------------------
            //--------------------------------------------------------------------
            firstTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(item.getQty() > 0){
                        item.setQty(item.getQty()-1);
                        editText.setText(Integer.toString(item.getQty()));
                        ArrayList<String> arr = new ArrayList<>();

                            int pos  = getPosition(order,item.getName());
                            Log.d("W",String.valueOf(pos));
                            order.remove(pos);
                            arr.add(item.getName());
                            arr.add(String.valueOf(item.getPrice()));
                            arr.add(String.valueOf(item.getQty()));
                            order.add(arr);

                    }
                }
            });

            //---------------------------------------------------------

            // Create the second TextView
            TextView secondTextView = new TextView(getContext());
            secondTextView.setLayoutParams(new LinearLayout.LayoutParams(
                    dpToPx(50),
                    dpToPx(40)
            ));
            secondTextView.setGravity(Gravity.CENTER | Gravity.END);
            secondTextView.setTextColor(getResources().getColor(R.color.white));
            secondTextView.setTextSize(15);
            secondTextView.setText(Integer.toString(item.getPrice()));

            // Add views to the nested layout
            nestedLayout.addView(editText);
            nestedLayout.addView(firstTextView);
            nestedLayout.addView(secondTextView);

            // Add views to the main layout
            mainLayout.addView(foodItemTextView);
            mainLayout.addView(horizontalLine);
            mainLayout.addView(nestedLayout);

            //increase the quantity of item ----------------------------------------------------
            //----------------------------------------------------------------------------------
            mainLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    item.setQty(item.getQty()+1);
                    editText.setText(Integer.toString(item.getQty()));
                    ArrayList<String> arr = new ArrayList<>();
                    boolean res = checkInOrder(order,item.getName());
                    Log.d("W",String.valueOf(res));
                    if(!res){
                        arr.add(item.getName());
                        arr.add(String.valueOf(item.getPrice()));
                        arr.add(String.valueOf(item.getQty()));
                        order.add(arr);
                    }
                    else
                    {
                        int pos  = getPosition(order,item.getName());
                        Log.d("W",String.valueOf(pos));
                        order.remove(pos);
                        arr.add(item.getName());
                        arr.add(String.valueOf(item.getPrice()));
                        arr.add(String.valueOf(item.getQty()));
                        order.add(arr);
                    }
                }
            });


            foodItemContainer.addView(mainLayout);
        }




        return view;
    }

    public boolean checkInOrder(ArrayList<ArrayList> a,String name)
    {
        for(int i = 0 ; i < a.size() ; i++)
        {
            if(a.get(i).get(0).toString()==name)
            {
                return true;
            }
        }
        return false;
    }
    public int getPosition(ArrayList<ArrayList> a,String name)
    {
        for(int i = 0 ; i < a.size() ; i++)
        {
            if(a.get(i).get(0).toString()==name)
            {
                return i;
            }
        }
        return 0;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();

        finalOrder=foodList.stream()
                .filter(item -> item.getQty() > 0 )
                .collect(Collectors.toList());

        ItemAddFragment fragment = new ItemAddFragment();
        Bundle args = new Bundle();
        args.putString("order",finalOrder.toString());
        fragment.setArguments(args);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        finalOrder=foodList.stream()
                .filter(item -> item.getQty() > 0 )
                .collect(Collectors.toList());

        ItemAddFragment fragment = new ItemAddFragment();
        Bundle args = new Bundle();
        args.putString("order",finalOrder.toString());
        fragment.setArguments(args);
    }

    private int dpToPx(int dp) {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        float widthInPx = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, displayMetrics);
        return (int) widthInPx;
    }




}