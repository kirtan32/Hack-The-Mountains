package com.example.featurelist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class algosolver extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_algosolver);
    }

    public void solveknapsack(View view)
    {

        Intent intent = new Intent(algosolver.this, knapsack.class);
        startActivity(intent);
    }

    public void getbackmain(View view)
    {
        Intent intent = new Intent(algosolver.this, mainpage.class);
        startActivity(intent);
    }
}
