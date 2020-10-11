package com.example.featurelist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class aptitudehelper extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_aptitudehelper);


    }

    public void reachtoclockangle(View view)
    {
        Intent intent = new Intent(aptitudehelper.this,apticlock.class);
        startActivity(intent);
    }

    public void reachtocalender(View view)
    {
        Intent intent = new Intent(aptitudehelper.this,apticalender.class);
        startActivity(intent);
    }
}
