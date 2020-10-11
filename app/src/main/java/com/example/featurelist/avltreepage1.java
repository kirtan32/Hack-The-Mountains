package com.example.featurelist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class avltreepage1 extends AppCompatActivity
{

    int[] arrayno;
    TextView cntxt, headcntxt, girlcntxt,subhead;
    Button submit;
    EditText avlcontext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_avltreepage1);

        Intent intent2=getIntent();

        headcntxt = (TextView) findViewById(R.id.textheadavl);
        subhead = (TextView)findViewById(R.id.subheadavl);
        cntxt = (TextView) findViewById(R.id.textViewavl);
        avlcontext = (EditText) findViewById(R.id.contextavl);
        submit = (Button) findViewById(R.id.buttonavl);


    }
    public void getBackavlpage1(View view)
    {
        Intent intent = new Intent(avltreepage1.this, treevisualizer.class);
        startActivity(intent);
    }

    public void solveavl(View view)
    {

        String strarray[] = avlcontext.getText().toString().split(",");
        try {
            arrayno = new int[strarray.length];
            for (int i = 0; i < strarray.length; i++) {
                arrayno[i] = Integer.parseInt(strarray[i]);
            }

            Intent intent = new Intent(avltreepage1.this, web_avl.class);
            intent.putExtra("content",avlcontext.getText().toString());
            startActivity(intent);

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Something wrong\nEnter Only Number", Toast.LENGTH_SHORT).show();
        }

    }
}
