package com.example.featurelist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

public class btreechooser extends AppCompatActivity
{
    RadioButton degree3,degree4,degree5,degree6;
    Button sub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_btreechooser);

        sub=(Button)findViewById(R.id.submithere);

        degree3=(RadioButton)findViewById(R.id.radiodegree3);

        // for sequrity purpose
        degree3.setEnabled(false);
        degree3.setVisibility(View.INVISIBLE);
        // we not update the code for order 3 yet!
        degree4=(RadioButton)findViewById(R.id.radiodegree4);
        degree5=(RadioButton)findViewById(R.id.radiodegree5);
        degree6=(RadioButton)findViewById(R.id.radiodegree6);

    }
    public void getbacktodraw(View view)
    {

        Intent intent = new Intent(btreechooser.this,b_bplus_tree.class);
        startActivity(intent);
    }

    public void gethomebtree(View view)
    {

        Intent intent = new Intent(btreechooser.this,mainpage.class);
        startActivity(intent);
    }

    public void checkradiobutton(View view)
    {

        if(degree3.isChecked())
        {
            Intent intent = new Intent(btreechooser.this,btreeact.class);
            intent.putExtra("degree","3");
            startActivity(intent);
        }
        else if(degree4.isChecked())
        {
            Intent intent = new Intent(btreechooser.this,btreeact.class);
            intent.putExtra("degree","4");
            startActivity(intent);
        }
        else if(degree5.isChecked())
        {
            Intent intent = new Intent(btreechooser.this,btreeact.class);
            intent.putExtra("degree","5");
            startActivity(intent);
        }
        else if(degree6.isChecked())
        {
            Intent intent = new Intent(btreechooser.this,btreeact.class);
            intent.putExtra("degree","6");
            startActivity(intent);
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Please select the button", Toast.LENGTH_LONG).show();
        }
    }
}
