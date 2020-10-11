package com.example.featurelist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class binarytree extends AppCompatActivity
{

    EditText binarycontext;
    TextView cntxt, headcntxt, girlcntxt;
    Button submit;
    int arrayno[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_binarytree);

        Intent intent2=getIntent();

        binarycontext = (EditText) findViewById(R.id.contextbinary);
        submit = (Button) findViewById(R.id.buttonbinary);

        binarycontext.getBackground().setColorFilter(getResources().getColor(R.color.colorAccent),
                PorterDuff.Mode.SRC_ATOP);

    }

    public void getBackBinary(View view)
    {
        //go to the tree visualizer page
        Intent intent = new Intent(binarytree.this, treevisualizer.class);
        startActivity(intent);
    }

    public void solvebinary(View view)
    {
        String strarray[] = binarycontext.getText().toString().split(",");
        try {
            arrayno = new int[strarray.length];
            for (int i = 0; i < strarray.length; i++) {
                arrayno[i] = Integer.parseInt(strarray[i]);
            }

            Intent intent = new Intent(binarytree.this, web_binary.class);
            intent.putExtra("content",binarycontext.getText().toString());
            startActivity(intent);

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Something wrong\nEnter Only Number", Toast.LENGTH_SHORT).show();
        }
    }
}
