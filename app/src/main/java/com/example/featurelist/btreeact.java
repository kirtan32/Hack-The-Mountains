package com.example.featurelist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class btreeact extends AppCompatActivity
{
    String context;
    EditText cont;
    int[] arrayno;
    String degreebtree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_btreeact);

        Intent intent=getIntent();
        degreebtree=intent.getStringExtra("degree");

        Button back=(Button)findViewById(R.id.btreebackhome);
        TextView head=(TextView)findViewById(R.id.textheadbtree);
        TextView subhead=(TextView)findViewById(R.id.textViewbtree);
        cont=(EditText)findViewById(R.id.contextbtree);
        Button sub=(Button)findViewById(R.id.buttonbtree);

    }
    public void getBackbtreepage(View view)
    {
        Intent intent = new Intent(btreeact.this,treevisualizer.class);
        startActivity(intent);
    }

    public void solvebtree(View view)
    {
        String strarray[] = cont.getText().toString().split(",");
        try {
            arrayno = new int[strarray.length];
            for (int i = 0; i < strarray.length; i++) {
                arrayno[i] = Integer.parseInt(strarray[i]);
            }

            Intent intent = new Intent(btreeact.this, web_btree.class);
            intent.putExtra("content",cont.getText().toString());
            intent.putExtra("degree",degreebtree);
            startActivity(intent);

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Something wrong\nEnter Only Number", Toast.LENGTH_SHORT).show();
        }
    }
}
