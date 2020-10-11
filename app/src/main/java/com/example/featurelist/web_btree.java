package com.example.featurelist;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Toast;

class BTreeNode
{
    int[] keys;
    int t;
    BTreeNode[] c;
    int n;
    boolean leaf;
    int maxdegree;
    int height;

    BTreeNode(int t1,boolean leaf1,int m1)
    {
        t=t1;
        leaf=leaf1;
        maxdegree=m1;
        height=0;
        keys = new int[maxdegree - 1];

        c = new BTreeNode[maxdegree];
        n=0;

    }

    void traverse()
    {
        int i=0;
        for(i=0;i<n;i++)
        {
            if(leaf==false)
            {
                c[i].traverse();
            }
            System.out.println("  "+keys[i]+"  -->"+leaf+"  -->"+i+"   ->"+n);
        }

        if(leaf==false)
        {
            c[i].traverse();
        }
    }

    BTreeNode search(int k)
    {
        int i=0;
        while(i<n && k>keys[i])
        {
            i++;
        }

        if(keys[i]==k)
        {
            return this;
        }

        if(leaf==true)
        {
            return null;
        }

        return c[i].search(k);
    }

    void insertNonFull(int k)
    {
        int i=n-1;
        if(leaf==true)
        {
            while(i>=0 && keys[i]>k)
            {
                keys[i+1]=keys[i];
                i--;
            }
            keys[i+1]=k;
            n++;
        }

        else
        {
            while(i>=0 && keys[i]>k)
            {
                i--;
            }
            if(c[i+1].n==maxdegree - 1)
            {
                splitchild(i+1,c[i+1]);

                if(keys[i+1]<k)
                {
                    i++;
                }
            }

            c[i+1].insertNonFull(k);
        }
    }

    void splitchild(int i,BTreeNode y)
    {
        BTreeNode z = new BTreeNode(y.t,y.leaf,y.maxdegree);
        z.n=0;

        for(int j=0;j<(t-1) ; j++)
        {
            if((j+t)<y.n)
            {
                z.keys[j]=y.keys[j+t];
                z.n++;
            }
            else
            {
                break;
            }
        }

        if(y.leaf==false)
        {
            for(int j=0;j<t;j++)
            {
                z.c[j]=y.c[j+t];
            }
        }

        y.n = t-1;

        for(int j=n;j>=(i+1);j--)
        {
            if(i==0)
            {
                c[j+1]=c[j];
            }
            else
            {
                c[j+i]=c[j];
            }
        }

        c[i+1]=z;

        for(int j=(n-1);j>=i;j--)
        {
            keys[j+1]=keys[j];
        }

        keys[i]=y.keys[t-1];
        n++;

    }

}


public class web_btree extends AppCompatActivity
{

    BTreeNode root;     // tree root
    int t;              //minimum degree
    String btreedegree;
    String cont;
    int[] arrayno;
    int treeHeight;      // tree height;
    //Bitmap bmp,scaledbmp;
    MyViewbtree yourView;
    ScrollView ss1;
    int onechildlength;
    EditText insertion,deletion,savetext;
    Button insertbut,deletebut;
    int ordertree;
    int m;               // tree maxmum order


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_web_btree);

        savetext = (EditText)findViewById(R.id.editTextbtree);
        insertion=(EditText)findViewById(R.id.insertbtree);
        deletion=(EditText)findViewById(R.id.deletebtree);

        insertbut = (Button)findViewById(R.id.insertbuttonbtree);
        deletebut = (Button)findViewById(R.id.deletebuttonbtree);

        Intent intent=getIntent();
        btreedegree=intent.getStringExtra("degree");
        cont=intent.getStringExtra("content");
        treeHeight=0;

        ss1=(ScrollView)findViewById(R.id.scrollbtree);

        root=null;
        ordertree=Integer.parseInt(btreedegree);
        m=ordertree;
        t=(int)Math.ceil(m/2);

        System.out.println("###  Order : "+m+"    --->min "+t);

        solvebtreesol();

        inittreeheight(root,1);
        if(root==null)
        {
            System.out.println("Root is Null    ---############################################");
        }
        else
        {
            System.out.println("Root is ## not ## Null    ---############################################");
            System.out.println("Root is ---> "+root.keys[0]+"  Total num -> "+root.n+"  Leaf -> "+root.leaf);
            System.out.println(" Height  is  ------> "+treeHeight);
        }
        initcompo();
        traverse();

    }

    public void solvebtreesol() {
        String strarray[] = cont.split(",");
        try {
            arrayno = new int[strarray.length];
            for (int i = 0; i < strarray.length; i++) {
                arrayno[i] = Integer.parseInt(strarray[i]);
                insert(arrayno[i]);
            }

            //initBitmap();

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Something wrong"+"\n"+e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    /*public void initBitmap()
    {
        bmp= BitmapFactory.decodeResource(getResources(),R.drawable.logo);
        scaledbmp = Bitmap.createScaledBitmap(bmp,200,200,false);
    }
*/
    public void initcompo()
    {
        onechildlength=(ordertree*100) + (ordertree+1)*20 + 50;
        int pageWidth=200 + onechildlength*(int)Math.pow(ordertree,treeHeight - 1);
        int pageHeight=250 + 250*(treeHeight-1) + (treeHeight*100);

        ScrollView.LayoutParams params = new ScrollView.LayoutParams(pageWidth,pageHeight);
        // LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(600,800);
        yourView = new MyViewbtree(this,pageWidth,pageHeight);
        yourView.setLayoutParams(params);
        ss1.addView(yourView);
    }

    /*public void initleafnode(BTreeNode rootleaf)
    {
        if(rootleaf!=null)
        {
            if(rootleaf.height==treeHeight)
            {
                rootleaf.leaf=true;
            }
            else
            {
                rootleaf.leaf=false;
            }
            for(int i=0;i<=rootleaf.n;i++)
            {
                initleafnode(rootleaf.c[i]);
            }
        }

    }*/

    void inittreeheight(BTreeNode rootnode,int hgt)
    {
        if(rootnode!=null)
        {
            rootnode.height=hgt;
            if(rootnode.height>treeHeight)
            {
                treeHeight=rootnode.height;
            }
            for(int i=0;i<=rootnode.n;i++)
            {
                inittreeheight(rootnode.c[i],rootnode.height+1);
            }
        }
    }

    // for implementing tree

    void traverse()
    {
        if(root!=null)
        {
            root.traverse();
        }
    }

    BTreeNode search(int k)
    {
        return (root==null)? null: root.search(k);
    }

    void insert(int k)
    {
        if(root==null)
        {
            root=new BTreeNode(t,true,m);
            root.keys[0]=k;
            root.n=1;
        }
        else
        {
            if(root.n == (m-1))
            {
                BTreeNode s = new BTreeNode(t,false,m);
                s.c[0]=root;
                if(root.c[m-1]==null)
                {
                    root.leaf=true;
                }

                s.splitchild(0, root);

                int i=0;

                if(s.keys[0]<k)
                {
                    i++;
                }
                s.c[i].insertNonFull(k);

                root=s;

            }
            else
            {
                root.insertNonFull(k);
            }
        }
    }


    // end of tree implementing

    public void getbacktodraw(View view)
    {
        Intent intent = new Intent(web_btree.this, btreechooser.class);
        intent.putExtra("degree",btreedegree);
        startActivity(intent);
    }

    public void gethomebtree(View view)
    {
        Intent intent = new Intent(web_btree.this, mainpage.class);
        startActivity(intent);
    }

    public void savecontent(View view)
    {
        System.out.println("TreeHeight --  > "+treeHeight);
    }

    public void insertitem(View view)
    {

        try
        {
            int no=Integer.parseInt(insertion.getText().toString());
            insert(no);
            ss1.removeAllViews();
            if(root==null)
            {
                treeHeight=1;
            }
            else
            {
                root.height=1;
                treeHeight=1;
            }
            inittreeheight(root,1);
            initcompo();
            insertion.setText("");
            Toast.makeText(getApplicationContext(), "Added "+no, Toast.LENGTH_SHORT).show();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Invalid Insertion", Toast.LENGTH_LONG).show();
        }

    }

    public void deleteitem(View view)
    {

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void fillCanvas(Canvas canvas1, int x, int y, BTreeNode cur)
    {
        if(cur!=null) {

            Paint keypaint = new Paint();
            keypaint.setColor(Color.rgb(204, 250, 30));

            Paint pointerpaint = new Paint();
            pointerpaint.setColor(Color.rgb(67,191,181));

            Paint samplepaint = new Paint();
            samplepaint.setColor(Color.rgb(255, 255, 255));
            samplepaint.setStrokeWidth(3);

            Paint textpaint = new Paint();
            textpaint.setColor(Color.rgb(0, 0, 0));
            textpaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
            textpaint.setTextSize(26);

            /*if (cur.left != null) {
                int spaces = 150 * ((int) Math.pow(2, treeHeight - cur.height - 1));
                canvas1.drawLine(x + 50, y + 50, x - spaces + 40, y + 350, samplepaint);
            }
            if (cur.right != null) {
                int spaces = 150 * ((int) Math.pow(2, treeHeight - cur.height - 1));
                canvas1.drawLine(x + 50, y + 50, x + spaces + 40, y + 350, samplepaint);
            }

            if (cur.left != null) {
                int spaces = 150 * ((int) Math.pow(2, treeHeight - cur.height - 1));
                fillCanvas(canvas1, x - spaces, y + 300, cur.left);
            }
            if (cur.right != null) {
                int spaces = 150 * ((int) Math.pow(2, treeHeight - cur.height - 1));
                fillCanvas(canvas1, x + spaces, y + 300, cur.right);
            }

            canvas1.drawOval(x, y, x + 100, y + 100, keypaint);
            canvas1.drawText("" + cur.key, x + 30, y + 55, textpaint);
            */
            canvas1.drawRect(x,y,x+20,y+100,pointerpaint);
            int x1=x+20;
            for(int i=0;i<cur.n;i++)
            {
                canvas1.drawRect(x1,y,x1+100,y+100,keypaint);
                canvas1.drawText(""+cur.keys[i],x1+20,y+60,textpaint);
                x1=x1+100;
                canvas1.drawRect(x1,y,x1+20,y+100,pointerpaint);
                x1=x1+20;
            }

            int xindexchild=x;
            int yindexchild=y;

            for(int i=0;i<=cur.n;i++)
            {
                if(cur.c[i]!=null && cur.leaf==false)
                {
                    int recentxindex= i*(onechildlength*(int)Math.pow( ordertree , treeHeight - (cur.height + 1 ) ) );
                    canvas1.drawLine(xindexchild+10,y+50, x + recentxindex + 10,y+350,samplepaint);
                    fillCanvas(canvas1,x + recentxindex,y+350,cur.c[i]);
                }
                xindexchild+=120;
            }

        }
        return;

    }

    public class MyViewbtree extends View
    {

        int width,height;
        public MyViewbtree(Context context, int w, int h)
        {
            super(context);
            width=w;
            height=h;
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

            subcanvas.drawColor(Color.rgb(38,48,67));

            Paint titlepaint = new Paint();
            titlepaint.setTextAlign(Paint.Align.LEFT);
            titlepaint.setTypeface(Typeface.create(Typeface.DEFAULT,Typeface.BOLD));
            titlepaint.setColor(Color.CYAN);
            titlepaint.setTextSize(35);

            /*subcanvas.drawRect(50,100,50+100,100+100,titlepaint);
            subcanvas.drawRect(170,100,170+100,100+100,titlepaint);
            titlepaint.setColor(Color.YELLOW);
            subcanvas.drawRect(150,100,150+20,100+100,titlepaint);
            subcanvas.drawRect(270,100,270+20,100+100,titlepaint);
*/
            BTreeNode cur=root;
            int y=100;
            int x=150;
            fillCanvas(subcanvas,x,y,cur);

        }
    }

}
