package com.example.featurelist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Spinner;

public class knapsack extends AppCompatActivity
{
    Spinner spinknap;
    EditText maxweight,wight,value;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_knapsack);

        spinknap=(Spinner)findViewById(R.id.spinknap);
        maxweight=(EditText)findViewById(R.id.knapmaxweight);
        wight=(EditText)findViewById(R.id.knapweight);
        value=(EditText)findViewById(R.id.knapvalues);
    }

    public void getback(View view)
    {
        Intent intent = new Intent(knapsack.this, algosolver.class);
        startActivity(intent);
    }

    public void gethomemain(View view)
    {
        Intent intent = new Intent(knapsack.this, mainpage.class);
        startActivity(intent);
    }

    public void solveknap(View view)
    {

    }
}
