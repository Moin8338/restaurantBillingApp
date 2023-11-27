package com.example.resturant_billing;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.textfield.TextInputLayout;


public class add_item_admin extends Fragment {
    private EditText inputName, inputQty, inputPrice, inputCategory;
    private TextInputLayout inputLayoutName, inputLayoutQty, inputLayoutPrice, inputLayoutCategory;
    private AppCompatButton btnAdd;


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public add_item_admin() {
        // Required empty public constructor
    }



    public static add_item_admin newInstance(String param1, String param2) {
        add_item_admin fragment = new add_item_admin();
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

        View view=inflater.inflate(R.layout.fragment_add_item_admin, container, false);
//
        inputLayoutName = (TextInputLayout) view.findViewById(R.id.input_layout_name);
    //    inputLayoutQty = (TextInputLayout) view.findViewById(R.id.input_layout_qty);
        inputLayoutPrice = (TextInputLayout) view.findViewById(R.id.input_layout_price);
        inputLayoutCategory = (TextInputLayout) view.findViewById(R.id.input_layout_category);
        inputName = (EditText) view.findViewById(R.id.input_name);
        //inputQty = (EditText) view.findViewById(R.id.input_qty);
        inputPrice = (EditText) view.findViewById(R.id.input_price);
        inputCategory = (EditText) view.findViewById(R.id.input_category);
        btnAdd=(AppCompatButton) view.findViewById(R.id.btnAdd);

        inputName.addTextChangedListener(new MyTextWatcher(inputName));
        //inputQty.addTextChangedListener(new MyTextWatcher(inputQty));
        inputPrice.addTextChangedListener(new MyTextWatcher(inputPrice));
        inputCategory.addTextChangedListener(new MyTextWatcher(inputCategory));


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitForm();
            }
        });


        return view;
    }


    /**
     * Validating form
     */
    private void submitForm() {
        if (!validateName()) {
            return;
        }

//        if (!validateQty()) {
//            return;
//        }

        if (!validatePrice()) {
            return;
        }
        DBHelper dbHelper = new DBHelper(getContext());
        boolean result = dbHelper.addItem(inputName.getText().toString(),inputPrice.getText().toString(),inputCategory.getText().toString());
        Toast.makeText(getContext(),"Thank you ! ", Toast.LENGTH_SHORT).show();
        inputPrice.setText("");
        inputCategory.setText("");
        inputName.setText("");
    }

    private boolean validateName() {
        if (inputName.getText().toString().trim().isEmpty()) {
            inputLayoutName.setError("Error !!!");
            requestFocus(inputName);
            return false;
        } else {
            inputLayoutName.setErrorEnabled(false);
        }

        return true;
    }
//
//    private boolean validateQty() {
//        String qty = inputQty.getText().toString().trim();
//
//        if (qty.isEmpty() ) {
//            inputLayoutQty.setError("Error !!!");
//            requestFocus(inputQty);
//            return false;
//        } else {
//            inputLayoutQty.setErrorEnabled(false);
//        }
//
//        return true;
//    }

    private boolean validatePrice() {
        if (inputPrice.getText().toString().trim().isEmpty()) {
            inputLayoutPrice.setError("Error !!!");
            requestFocus(inputPrice);
            return false;
        } else {
            inputLayoutPrice.setErrorEnabled(false);
        }

        return true;
    }

    private static boolean isValidDigit(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
//                case R.id.input_name:
//                    validateName();
//                    break;
//                case R.id.:
//                    validateEmail();
//                    break;
//                case R.id.input_password:
//                    validatePassword();
//                    break;
            }
        }
    }


}