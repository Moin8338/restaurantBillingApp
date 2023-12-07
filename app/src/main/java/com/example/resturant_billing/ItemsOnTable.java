package com.example.resturant_billing;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.MainThread;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.resturant_billing.model.order_item;
import com.google.gson.Gson;

import com.google.gson.reflect.TypeToken;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import de.hdodenhof.circleimageview.CircleImageView;
import kotlin.jvm.internal.TypeReference;

public class ItemsOnTable extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private static int counter = 0;

    String uniqueId;

    private List<order_item> foodList= new ArrayList<order_item>();

    public ItemsOnTable() {
        // Required empty public constructor
    }

    public static ItemsOnTable newInstance(String param1, String param2) {
        ItemsOnTable fragment = new ItemsOnTable();
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

        View view=inflater.inflate(R.layout.fragment_items_on_table, container, false);


        if(getArguments() == null && ItemAddFragment.order.size() == 0 ){

            LinearLayout validateLayout=(LinearLayout) view.findViewById(R.id.viewLinearLayout);

            validateLayout.removeAllViews();

            TextView notfound=new TextView(getContext());

            notfound.setText("Oops, Not Found !!");
            notfound.setTextSize(30);
            notfound.setTextColor(getResources().getColor(R.color.black));
            notfound.setGravity(Gravity.CENTER);
            notfound.setPadding(0,400,0,20);

            TextView message=new TextView(getContext());

            message.setText("Please add Item before generating a bill");
            message.setTextSize(10);
            message.setTextColor(getResources().getColor(R.color.black));
            message.setGravity(Gravity.CENTER);

            validateLayout.addView(notfound);
            validateLayout.addView(message);

        }else{
            DBHelper dbHelper = new DBHelper(getContext());
            SharedPreferences preferences = getActivity().getSharedPreferences("id", Context.MODE_PRIVATE);
            int id = preferences.getInt("userid",0);
            ArrayList<String> details = dbHelper.getDetails(id);




            TextView restName=(TextView) view.findViewById(R.id.restaurantName);
            TextView restAddress=(TextView) view.findViewById(R.id.restaurantAddress);
            TextView restGst=(TextView) view.findViewById(R.id.restaurantGst);
            TextView ownerName=(TextView) view.findViewById(R.id.ownerName);
            TextView ownerPhone=(TextView) view.findViewById(R.id.onwerphone);
            TextView billNo=(TextView) view.findViewById(R.id.billNumber);

            restName.setText(details.get(4));
            restGst.setText("GST N0 : \n"+details.get(6));
            ownerName.setText("Owner Name : \n"+details.get(5));
            ownerPhone.setText("Phone : \n"+details.get(2));
            restAddress.setText(details.get(7));

            TableLayout tableLayout = view.findViewById(R.id.foodListTable);


            //generate unique id using date and unique randome id --------------------
//        -----------------------------------------------------------------------

            uniqueId=generateUniqueId();
            billNo.setText("Bill No : \n"+uniqueId.substring(uniqueId.length()-7));

            //        -----------------------------------------------------------------------
            //        -----------------------------------------------------------------------

            TableLayout bill=(TableLayout) view.findViewById(R.id.billTable);
            view.findViewById(R.id.removeBill).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ItemAddFragment.order.clear();
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.frame_layout,new ItemsOnTable());
                    fragmentTransaction.commit();
                }
            });

            if(getArguments() == null){
//            foodList.add(new order_item(11,"Pizza Margherita",180,"pizza",1,180));
//            foodList.add(new order_item(12,"Pizza Vegetarian",175,"pizza",2,175*2));
//            foodList.add(new order_item(13,"Veggie Burger",120,"burger",1,120));
//            foodList.add(new order_item(14,"Cheeseburger",140,"burger",3,420));
//            foodList.add(new order_item(15,"Greek Salad",220,"salad",2,440));
//            foodList.add(new order_item(16,"Grilled Cheese",60,"sandwich",4,240));
//            foodList.add(new order_item(17,"Grilled Salmon",180,"seafood",1,180));
//            foodList.add(new order_item(18,"Spicy Chicken Curry",180,"special",1,180));
//            foodList.add(new order_item(19,"Paneer Tikka Masala",220,"special",1,220));
//            foodList.add(new order_item(20,"Cola",30,"Beverages",2,60));
//            foodList.add(new order_item(21,"Mango Lassi",60,"Beverages",1,60));
                for(int i = 0 ; i < ItemAddFragment.order.size() ; i++)
                {
                    foodList.add(new order_item(i+1 , ItemAddFragment.order.get(i).get(0).toString(),Integer.parseInt(ItemAddFragment.order.get(i).get(1).toString()),"",Integer.parseInt(ItemAddFragment.order.get(i).get(2).toString()),Integer.parseInt(ItemAddFragment.order.get(i).get(2).toString()) * Integer.parseInt(ItemAddFragment.order.get(i).get(1).toString())));

                }
            }else{
                Toast.makeText(getContext(), "Something went Wrong", Toast.LENGTH_SHORT).show();
            }



            // Inflate the layout for this fragment
            // Create a new table row
            int i=1;
            int sum=0;
            for(order_item item : foodList){
                sum+=item.getQty()*item.getPrice();
                TableRow row = new TableRow(getContext());
                row.setLayoutParams(new TableLayout.LayoutParams(
                        TableLayout.LayoutParams.MATCH_PARENT,
                        TableLayout.LayoutParams.WRAP_CONTENT));
                row.setPadding(5, 0, 5, 0);
                TextView index = new TextView(getContext());
                index.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.WRAP_CONTENT));
                index.setText(Integer.toString(i));
                index.setGravity(Gravity.CENTER);
                index.setTextColor(getResources().getColor(R.color.black));
                index.setLayoutParams(new TableRow.LayoutParams(
                        0,
                        TableRow.LayoutParams.WRAP_CONTENT,
                        0.5f));

                ///Item name --------------------------

                TextView itemname = new TextView(getContext());
                itemname.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.WRAP_CONTENT));
                itemname.setText(item.getName());
                itemname.setGravity(Gravity.CENTER);
                itemname.setTextColor(getResources().getColor(R.color.black));
                itemname.setLayoutParams(new TableRow.LayoutParams(
                        0,
                        TableRow.LayoutParams.WRAP_CONTENT,
                        2f));

                /////  item Quantity ----------------------

                TextView itemQty = new TextView(getContext());
                itemQty.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.WRAP_CONTENT));
                itemQty.setText(Integer.toString(item.getQty()));
                itemQty.setGravity(Gravity.CENTER);
                itemQty.setTextColor(getResources().getColor(R.color.black));
                itemQty.setLayoutParams(new TableRow.LayoutParams(
                        0,
                        TableRow.LayoutParams.WRAP_CONTENT,
                        0.5f));


                // item Total --------------------------------

                TextView itemTotal = new TextView(getContext());
                itemTotal.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.WRAP_CONTENT));
                itemTotal.setTextColor(getResources().getColor(R.color.black));
                itemTotal.setText(Integer.toString(item.getQty() * item.getPrice()));
                itemTotal.setGravity(Gravity.CENTER);
                itemTotal.setLayoutParams(new TableRow.LayoutParams(
                        0,
                        TableRow.LayoutParams.WRAP_CONTENT,
                        1f));
                row.addView(index);
                row.addView(itemname);
                row.addView(itemQty);
                row.addView(itemTotal);
                bill.addView(row);

                //billStructure ---------------------------------------------------------------
                //----------------------------------------------------------------------------


                TableRow tableRow = new TableRow(getContext());
                if(i%2==0){
                    tableRow.setBackgroundColor(getResources().getColor(R.color.white));
                }else{
                    tableRow.setBackgroundColor(getResources().getColor(R.color.tableRowColor));

                }
                tableRow.setPadding(5, 5, 5, 5);

                // Create the first TextView
                TextView index1 = new TextView(getContext());
                index1.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.WRAP_CONTENT, 0.5f));
                index1.setText(Integer.toString(i));
                index1.setTextColor(getResources().getColor(R.color.black));

                // Create the second TextView
                TextView itemName = new TextView(getContext());
                itemName.setTextColor(getResources().getColor(R.color.black));
                itemName.setLayoutParams(new TableRow.LayoutParams(
                        200, // Set the width in pixels or use appropriate measure
                        TableRow.LayoutParams.WRAP_CONTENT, 2f));
                itemName.setText(item.getName());

                // Create the third TextView
                TextView qty = new TextView(getContext());
                qty.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.WRAP_CONTENT, 0.5f));
                qty.setText(Integer.toString(item.getQty()));
                qty.setTextColor(getResources().getColor(R.color.black));

                //create the fourth TextView
                TextView price = new TextView(getContext());
                price.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.WRAP_CONTENT, 0.5f));
                price.setText(Integer.toString(item.getPrice()));
                price.setTextColor(getResources().getColor(R.color.black));

                // Create the fifth TextView
                TextView totalOfitem = new TextView(getContext());
                totalOfitem.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.WRAP_CONTENT, 1f));
                totalOfitem.setText(Integer.toString(item.getQty() * item.getPrice()));
                totalOfitem.setTextColor(getResources().getColor(R.color.black));

                // Add TextViews to the TableRow
                tableRow.addView(index1);
                tableRow.addView(itemName);
                tableRow.addView(qty);
                tableRow.addView(price);
                tableRow.addView(totalOfitem);

                // Add TableRow to your TableLayout (assuming you have a TableLayout in your XML layout)

                tableLayout.addView(tableRow);

                i++;
            }

            TextView total1=(TextView) view.findViewById(R.id.totalPrice);
            total1.setText(Integer.toString(sum));
//
            TextView total=(TextView) view.findViewById(R.id.totalCost);
            total.setText(Integer.toString(sum));




            LinearLayout billstructure=(LinearLayout) view.findViewById(R.id.billStructure);

            AppCompatButton printBtn=(AppCompatButton) view.findViewById(R.id.printBill);

            printBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    generatePDFfromView(billstructure,uniqueId);
                    ItemAddFragment.order.clear();
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.frame_layout,new ItemsOnTable());
                    fragmentTransaction.commit();
                }
            });


        }

        return view;
    }

    public void generatePDFfromView(View view,String filename) {
        Bitmap bitmap = getBitmapFromView(view);

        // Create a PDF document
        PdfDocument document = new PdfDocument();
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(bitmap.getWidth(), bitmap.getHeight(), 1).create();
        PdfDocument.Page page = document.startPage(pageInfo);
        Canvas canvas = page.getCanvas();
        canvas.drawBitmap(bitmap, 0, 0, null);
        document.finishPage(page);

        // Create a file for the PDF
        File pdfFile = createFile(filename);

        try {
            // Write the PDF content to the file
            document.writeTo(new FileOutputStream(pdfFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Close the document
        document.close();
    }
    public Bitmap getBitmapFromView(View view) {
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);
        Drawable bgDrawable = view.getBackground();
        if (bgDrawable != null) {
            bgDrawable.draw(canvas);
        } else {
            canvas.drawColor(getResources().getColor(android.R.color.white));
        }
        view.draw(canvas);
        return returnedBitmap;
    }
    public File createFile(String filename) {
        // Create a directory for the PDF file
        String filepath=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();
        Log.d("file",filepath);
        File directory = new File(filepath);
//        if (!directory.exists()) {
//            directory.mkdirs();
//        }
        // Create the PDF file
        File pdfFile = new File(directory, filename+".pdf");
        // Open the file in write mode, creating it if necessary
        try {
            pdfFile.createNewFile();
            Toast.makeText(getContext(), "File is Saved !!", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pdfFile;
    }
    private int dpToPx(int dp) {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        float widthInPx = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, displayMetrics);
        return (int) widthInPx;
    }
    public static String generateUniqueId() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String formatedDate = now.format(formatter);
        UUID uuid = UUID.randomUUID();
        // Convert the UUID to a string
        String uniqueId = uuid.toString();
        // Remove hyphens to get a compact string
        uniqueId = uniqueId.replaceAll("-", "");
        uniqueId = formatedDate+uniqueId;
        return uniqueId;
    }
}