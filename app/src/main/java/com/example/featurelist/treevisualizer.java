package com.example.featurelist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class treevisualizer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_treevisualizer);
    }

    public void reachtobinarytree(View view)
    {

        Intent intent = new Intent(treevisualizer.this,binarytree.class);
        intent.putExtra("idcont","1");
        startActivity(intent);
    }

    public void reachtoavltree(View view)
    {

        Intent intent = new Intent(treevisualizer.this,avltreepage1.class);
        intent.putExtra("idcont","1");
        startActivity(intent);
    }

    public void reachtobtree(View view)
    {

        Intent intent = new Intent(treevisualizer.this,b_bplus_tree.class);
        intent.putExtra("idcont","1");
        startActivity(intent);
    }
}
