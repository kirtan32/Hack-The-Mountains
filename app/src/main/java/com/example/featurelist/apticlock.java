package com.example.featurelist;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class apticlock extends AppCompatActivity
{
    ScrollView clockscroll;
    Button submit;
    EditText hourhand,minutehand;
    String hours,minutes;
    MyViewClock myview;
    Context con;
    Bitmap bmp,scaledbmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_apticlock);

        clockscroll = (ScrollView)findViewById(R.id.scrollclockin);
        submit = (Button)findViewById(R.id.setit);
        hourhand=(EditText)findViewById(R.id.hourhand);
        minutehand=(EditText)findViewById(R.id.minutehand);

        initBitmap();


    }


    public void setclock(View view)
    {
        try {
            hours=hourhand.getText().toString();
            minutes=minutehand.getText().toString();
            if(Integer.parseInt(hours)>12 || Integer.parseInt(minutes)>60)
            {
                Toast.makeText(getApplicationContext(), "! Something Wrong !\n"+hours+" : "+minutes, Toast.LENGTH_LONG).show();
            }
            else
            {
                clockscroll.removeAllViews();

                ScrollView.LayoutParams params = new ScrollView.LayoutParams(900,900);
                // LinearLayomut.LayoutParams params = new LinearLayout.LayoutParams(600,800);
                myview = new MyViewClock(getApplicationContext(),Integer.parseInt(hours),Integer.parseInt(minutes));
                // myview.setLayoutParams(params);
                clockscroll.addView(myview);

                Toast.makeText(getApplicationContext(), ""+hours+" : "+minutes, Toast.LENGTH_LONG).show();
            }
        }
        catch (Exception e)
        {
            Toast.makeText(getApplicationContext(), "ERROR  ", Toast.LENGTH_LONG).show();
        }
    }
    public void initBitmap()
    {
        bmp= BitmapFactory.decodeResource(getResources(),R.drawable.clock);
        scaledbmp = Bitmap.createScaledBitmap(bmp,650,650,false);
    }

    public void backtohomeapti(View view)
    {
        Intent intent = new Intent(apticlock.this, mainpage.class);
        startActivity(intent);
    }

    public class MyViewClock extends View
    {
        int width,height,hour,minute;
        public MyViewClock(Context context, int h, int m)
        {
            super(context);
            width=1100;
            height=900;
            hour=h;
            minute=m;
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
        {
            setMeasuredDimension(width, height);
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public void onDraw(Canvas subcanvas) {
            super.onDraw(subcanvas);
            System.out.println("Width --> :"+subcanvas.getWidth()+"   Height : "+subcanvas.getHeight());

            subcanvas.drawColor(Color.rgb(255,255,255));

            Paint clockout = new Paint();
            Paint clockin=new Paint();
            clockout.setColor(Color.BLUE);
            clockin.setColor(Color.WHITE);

            int outerlength=650;
            int startx=170;
            int starty=140;

            int minutehandlength=200;
            int hourhandlength=100;

            float anglemin=(Integer.parseInt(minutes)*6)%360;
            float anglehour=((Integer.parseInt(hours))*30 + (float)Integer.parseInt(minutes)/2)%360;

            double radiansmin = Math.toRadians(anglemin);
            double radianshour=Math.toRadians(anglehour);

            double minsinValue = minutehandlength * Math.sin(radiansmin);
            double mincosValue = minutehandlength * Math.cos(radiansmin);

            double hoursinValue = hourhandlength * Math.sin(radianshour);
            double hourcosValue = hourhandlength * Math.cos(radianshour);

            subcanvas.drawOval(startx-20,starty-20,startx+outerlength+20,starty+outerlength+20,clockout);
            subcanvas.drawOval(startx,starty,startx+outerlength,starty+outerlength,clockin);

            subcanvas.drawBitmap(scaledbmp,startx,starty,clockin);

            clockout.setStrokeWidth(10);
            //subcanvas.drawLine(startx+ (outerlength)/2,starty+ (outerlength)/2,startx+ (outerlength)/2 + minutehandlength,starty+ (outerlength)/2 + 0,clockout);
            //subcanvas.drawLine(startx + (outerlength)/2,starty+ (outerlength)/2,startx+ (outerlength)/2 ,starty+ (outerlength)/2 + hourhandlength,clockout);

            subcanvas.drawLine(startx+ (outerlength)/2,starty+ (outerlength)/2, (float) (startx+ (outerlength)/2 + minsinValue), (float) (starty+ (outerlength)/2 - mincosValue),clockout);
            subcanvas.drawLine(startx + (outerlength)/2,starty+ (outerlength)/2, (float) (startx+ (outerlength)/2 + hoursinValue), (float) (starty+ (outerlength)/2 - hourcosValue),clockout);


            clockin.setColor(Color.BLACK);
            subcanvas.drawOval(startx + (outerlength)/2 - 8,starty + (outerlength)/2 - 8,startx + (outerlength)/2 +8,starty + (outerlength)/2+ 8,clockin);

            Paint titlepaint = new Paint();
            titlepaint.setTextAlign(Paint.Align.LEFT);
            titlepaint.setTypeface(Typeface.create(Typeface.DEFAULT,Typeface.BOLD));
            titlepaint.setColor(Color.BLACK);
            titlepaint.setTextSize(35);

            subcanvas.drawText("Angle : "+(Math.abs(anglehour-anglemin))+"* , "+(360-Math.abs(anglehour-anglemin))+"*",20,45,titlepaint);

            titlepaint.setTypeface(Typeface.create(Typeface.DEFAULT,Typeface.NORMAL));
            titlepaint.setColor(Color.WHITE);
            titlepaint.setTextSize(28);

        }
    }
}
