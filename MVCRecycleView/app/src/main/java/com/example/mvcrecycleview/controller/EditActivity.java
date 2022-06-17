package com.example.mvcrecycleview.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mvcrecycleview.MainActivity;
import com.example.mvcrecycleview.R;

public class EditActivity extends AppCompatActivity {

    private EditText txtName;
    private EditText txtPhone;

    private String name;
    private String phone;
    private int position;

    private AppCompatButton buttonModify;
    private AppCompatButton buttonEradicate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        /*Step 1 - declare global variable*/
        name = getIntent().getStringExtra("name");
        phone = getIntent().getStringExtra("phone");
        position = getIntent().getIntExtra("position", 0);

        /*Step 2*/
        setupComponent();
        setupEvent();
        setupScreen();
    }


    /**
     * @author Phong-Kaster
     * this function maps defined componet in this class with XML layout
     * */
    private void setupComponent()
    {
        txtName = findViewById(R.id.txtName);
        txtPhone = findViewById(R.id.txtPhone);
        buttonModify = findViewById(R.id.buttonModify);
        buttonEradicate = findViewById(R.id.buttonEradicate);
    }

    /**
     * @author Phong-Kaster
     * this function handles when end-user clicks on button.
     * Depending on what button is executed, each button has specific work-flow
     */
    private void setupEvent()
    {
        buttonModify.setOnClickListener(view ->{

            name = txtName.getText().toString();
            phone = txtPhone.getText().toString();

            MainActivity.getmInstanceActivity().modify(
                    position, name, phone
            );
        });

        buttonEradicate.setOnClickListener(view->{
            MainActivity.getmInstanceActivity().eradicate(position);
        });
    }


    private void setupScreen()
    {
        txtName.setText( name);
        txtPhone.setText( phone);
    }
}