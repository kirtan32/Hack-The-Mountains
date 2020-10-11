package com.example.featurelist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class apticalender extends AppCompatActivity
{
    EditText date,month,year;
    TextView day;
    String[] days;
    int[] normaldaysmonth;
    int[] extradaysyears;
    int textdate,textmonth,textyear;
    Context con;
    Button getday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_apticalender);

        date = (EditText)findViewById(R.id.date);
        month= (EditText)findViewById(R.id.month);
        year = (EditText)findViewById(R.id.year);
        day = (TextView)findViewById(R.id.day);

        getday=(Button)findViewById(R.id.getday);

        days=new String[]{"SUNDAY","MONDAY","TUESDAY","WEDNESDAY","THURSDAY","FRIDAY","SATURDAY"};
        normaldaysmonth=new int[]{3,0,3,2,3,2,3,3,2,3,2,3};

        for(int i=1;i<normaldaysmonth.length;i++)
        {
            normaldaysmonth[i]+=normaldaysmonth[i-1];
        }

        extradaysyears=new int[]{0,5,3,1};

    }
    public void calculateTheDay()
    {
        int y1,y2,y3,y4;
        int samp;
        samp=textyear-1;
        samp=samp%400;
        samp=samp/100;
        y1=extradaysyears[samp];

        samp=textyear-1;
        samp=samp%100;
        y2=(samp/4)*2 + (samp - (samp/4));

        if(textmonth==1){ y3=0; }
        else
        {
            y3=normaldaysmonth[textmonth-2];
        }
        if(checkleapyear(textyear))
        {
            y3++;
        }

        y4=textdate;

        //Toast.makeText(con, "y1,y2,y3,y4: "+y1+" "+y2+" "+y3+" "+y4, Toast.LENGTH_LONG).show();

        y1=(y1+y2+y3+y4);
        y1=y1%7;

        day.setText(""+days[y1]);

    }

    public boolean checkleapyear(int y)
    {
        if(y%400==0)
            return true;

        if(y%100==0)
            return false;

        if(y%4==0)
            return true;

        return false;
    }

    public void setday(View view)
    {
        try {

            textdate = Integer.parseInt(date.getText().toString());
            textmonth = Integer.parseInt(month.getText().toString());
            textyear = Integer.parseInt(year.getText().toString());
            int f=0;
            if(textdate>31 || textmonth>12)
            {
                f=1;
            }
            else
            {
                if(textmonth==2 || textmonth==4 || textmonth==6 || textmonth==9 || textmonth==11 )
                {

                    if(textmonth==2 && textdate>29)
                    {
                        f=1;
                    }
                    if(textdate>30)
                    {
                        f=1;
                    }
                }

            }
            if(f==1)
            {
                Toast.makeText(getApplicationContext(), "! Something wrong !", Toast.LENGTH_LONG).show();
            }
            else
            {
                calculateTheDay();
            }

        } catch (Exception e) {

        }
    }

    public void backapti(View view)
    {
        Intent intent = new Intent(apticalender.this, mainpage.class);
        startActivity(intent);
    }
}
