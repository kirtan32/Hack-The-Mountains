package com.example.featurelist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

public class b_bplus_tree extends AppCompatActivity
{
    LinearLayout btree,bplustree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_b_bplus_tree);

        btree=(LinearLayout)findViewById(R.id.btreelayout);
        bplustree=(LinearLayout)findViewById(R.id.bplustreelayout);
    }
    public void getBackbtreespage1(View view)
    {
        Intent intent = new Intent(b_bplus_tree.this,treevisualizer.class);
        startActivity(intent);
    }

    public void btreeopen(View view)
    {
        Intent intent = new Intent(b_bplus_tree.this,btreechooser.class);
        startActivity(intent);
    }

    public void bplustreeopen(View view)
    {

    }
}
