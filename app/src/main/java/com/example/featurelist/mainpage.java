package com.example.featurelist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class mainpage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_mainpage);

        isReadStoragePermissionGranted();
        isWriteStoragePermissionGranted();
    }
    public  boolean isReadStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                System.out.println("Permission is granted");
                return true;
            } else {
                System.out.println("Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 3);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            System.out.println("Permission is granted in < 23 version");
            return true;
        }
    }

    public  boolean isWriteStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                System.out.println("Permission is granted");
                return true;
            } else {
                System.out.println("Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            System.out.println("Permission is granted in < 23 version");
            return true;
        }
    }

    public void treevisualizer(View view)
    {
        Intent intent = new Intent(mainpage.this,treevisualizer.class);
        startActivity(intent);
    }

    public void binaryconversion(View view)
    {
        Intent intent = new Intent(mainpage.this,binaryconversion.class);
        startActivity(intent);
    }

    public void aptitudehelper(View view)
    {

        Intent intent = new Intent(mainpage.this,aptitudehelper.class);
        startActivity(intent);
    }

    public void algosolver(View view)
    {
        Intent intent = new Intent(mainpage.this,algosolver.class);
        startActivity(intent);
    }

    public void infixprefixpostfix(View view)
    {
        Intent intent = new Intent(mainpage.this,inpostpreconvertor.class);
        startActivity(intent);
    }
}
