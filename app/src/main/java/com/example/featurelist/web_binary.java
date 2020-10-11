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
import android.graphics.pdf.PdfDocument;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

class Node {
    int key;
    int height;
    int design;
    Node left, right,parent;

    public Node(int item,Node parental,int h) {
        key = item;
        height=h;
        design=0;
        parent=parental;
        left = right = null;
    }
}


public class web_binary extends AppCompatActivity
{

    String cont;
    int arrayno[];
    Bitmap bmp,scaledbmp;
    Node root;
    int treeHight;
    PdfDocument mypdf;
    EditText savetext;
    String filena;
    Canvas canvas;
    MyView yourView;
    EditText insertion,deletion;
    ScrollView ss1;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_web_binary);

        savetext = (EditText)findViewById(R.id.editTextbinary);
        insertion=(EditText)findViewById(R.id.insertbinary);
        deletion=(EditText)findViewById(R.id.deletebinary);

        Intent intent=getIntent();
        cont=intent.getStringExtra("content");

        root=null;
        treeHight=0;

        solvebinarysol();

        ss1=(ScrollView)findViewById(R.id.scrollbinary);

        initcompo();

    }
    public void initcompo()
    {
        int pageWidth=200 + 300*(int)Math.pow(2,treeHight - 1);
        int pageHeight=600 + 300*(treeHight-1);


        ScrollView.LayoutParams params = new ScrollView.LayoutParams(pageWidth,pageHeight);
        // LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(600,800);
        yourView = new MyView(this,pageWidth,pageHeight);
        yourView.setLayoutParams(params);
        ss1.addView(yourView);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void solvebinarysol() {
        String strarray[] = cont.split(",");
        try {
            arrayno = new int[strarray.length];
            for (int i = 0; i < strarray.length; i++) {
                arrayno[i] = Integer.parseInt(strarray[i]);
                insert(arrayno[i]);
            }

            initBitmap();
            //createfilehere();

            //createfile2();

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Something wrong", Toast.LENGTH_SHORT).show();
        }
    }

    public void initBitmap()
    {
        bmp= BitmapFactory.decodeResource(getResources(),R.drawable.logo);
        scaledbmp = Bitmap.createScaledBitmap(bmp,200,200,false);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void createfile2()
    {
        mypdf = new PdfDocument();
        Paint mypaint = new Paint();

        int treeheight=treeHight-1;

        int pageWidth=800 + 300*(int)Math.pow(2,treeheight);
        int pageHeight=800 + 300*treeheight;

        System.out.println("----------> Width "+pageWidth+"    Height "+pageHeight);

        PdfDocument.PageInfo mypageinfo=new PdfDocument.PageInfo.Builder(pageWidth,pageHeight,1).create();

        PdfDocument.Page mypage=mypdf.startPage(mypageinfo);

        Paint titlepaint=new Paint();
        Paint samplepaint=new Paint();

        canvas = mypage.getCanvas();

        titlepaint.setColor(Color.rgb(32,32,32));
        titlepaint.setTextSize(70);

        canvas.drawRect(0,0,pageWidth,pageHeight,titlepaint);

        canvas.drawBitmap(scaledbmp,20,20,mypaint);

        titlepaint.setTextAlign(Paint.Align.LEFT);
        titlepaint.setTypeface(Typeface.create(Typeface.DEFAULT,Typeface.BOLD));
        titlepaint.setColor(Color.rgb(255,69,0));
        titlepaint.setTextSize(70);

        canvas.drawText("BST Tree Visualizer",  210,130,titlepaint);

        titlepaint.setColor(Color.rgb(0,215,221));
        samplepaint.setColor(Color.rgb(255,255,255));
        samplepaint.setTextSize(20);

        Paint textpaint = new Paint();
        textpaint.setColor(Color.rgb(0,0,0));
        textpaint.setTypeface(Typeface.create(Typeface.DEFAULT,Typeface.BOLD));
        textpaint.setTextSize(25);

        Node cur=root;
        //int x1=400 + 150*(int)Math.pow(2,treeheight);
        int y=400;
        int x=400 + 150*((int)Math.pow(2,treeheight) - 1);

        fillCanvas(canvas,x,y,cur);

        mypdf.finishPage(mypage);

        try
        {
            try {
                File directory = new File(Environment.getExternalStorageDirectory()+File.separator+"Featurelist");
                if(directory.exists())
                {
                    Toast.makeText(getApplicationContext(), "Directory Exits", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    directory.mkdirs();
                    System.out.println("File is Created");
                    Toast.makeText(getApplicationContext(), "Directory is created ", Toast.LENGTH_SHORT).show();
                }
            }
            catch (Exception e)
            {
                Toast.makeText(getApplicationContext(), "Directory Already Exits", Toast.LENGTH_SHORT).show();
            }

            File file=new File(Environment.getExternalStorageDirectory(),"/AdExTreeTool/"+filena+".pdf");

            mypdf.writeTo(new FileOutputStream(file));
            System.out.println("File Written Successfully");
            Toast.makeText(getApplicationContext(), "Your File is downloaded \n as "+filena+".pdf", Toast.LENGTH_LONG).show();
        }
        catch (Exception e)
        {
            System.out.println("Error in File writing");
            e.printStackTrace();
            System.out.println("# --> "+e.getMessage());

            Toast.makeText(getApplicationContext(), "Error In File Writing", Toast.LENGTH_LONG).show();
        }
        mypdf.close();

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void fillCanvas(Canvas canvas1, int x, int y, Node cur)
    {
        if(cur!=null) {

            Paint titlepaint = new Paint();
            Paint samplepaint = new Paint();
            samplepaint.setColor(Color.rgb(255, 255, 255));
            titlepaint.setColor(Color.rgb(0, 215, 221));

            Paint textpaint = new Paint();
            textpaint.setColor(Color.rgb(0, 0, 0));
            textpaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
            textpaint.setTextSize(25);

            if (cur.left != null) {
                int spaces = 150 * ((int) Math.pow(2, treeHight - cur.height - 1));
                canvas1.drawLine(x + 50, y + 50, x - spaces + 40, y + 350, samplepaint);
            }
            if (cur.right != null) {
                int spaces = 150 * ((int) Math.pow(2, treeHight - cur.height - 1));
                canvas1.drawLine(x + 50, y + 50, x + spaces + 40, y + 350, samplepaint);
            }

            if (cur.left != null) {
                int spaces = 150 * ((int) Math.pow(2, treeHight - cur.height - 1));
                fillCanvas(canvas1, x - spaces, y + 300, cur.left);
            }
            if (cur.right != null) {
                int spaces = 150 * ((int) Math.pow(2, treeHight - cur.height - 1));
                fillCanvas(canvas1, x + spaces, y + 300, cur.right);
            }

            canvas1.drawOval(x, y, x + 100, y + 100, titlepaint);
            canvas1.drawText("" + cur.key, x + 30, y + 55, textpaint);

        }
        return;

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void savecontent(View view)
    {

        if(savetext.getText().toString().equals(""))
        {
            Toast.makeText(getApplicationContext(), "Please Enter valid filename", Toast.LENGTH_LONG).show();
        }
        else
        {
            filena=savetext.getText().toString();
            createfile2();
            savetext.setText("");
        }

    }

    public void insertitem(View view)
    {
        try
        {
            int no=Integer.parseInt(insertion.getText().toString());
            insert(no);

            ss1.removeAllViews();
            initcompo();

            insertion.setText("");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Invalid Insertion", Toast.LENGTH_LONG).show();
        }
    }

    public void deleteitem(View view)
    {
        try
        {
            int no=Integer.parseInt(deletion.getText().toString());
            deleteKey(no);
            if(root==null)
            {
                treeHight=1;
            }
            else
            {
                root.height=1;
                treeHight=1;
            }
            traverse(root);
            ss1.removeAllViews();
            initcompo();

            deletion.setText("");

        }
        catch (Exception e)
        {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Invalid Deletion", Toast.LENGTH_LONG).show();
        }
    }

    public void traverse(Node rootnode)
    {
        if(rootnode!=null) {
            if (rootnode.left != null) {
                rootnode.left.height = rootnode.height + 1;
                if (treeHight < rootnode.left.height) {
                    treeHight = rootnode.left.height;
                }
                traverse(rootnode.left);
            }
            if (rootnode.right != null) {
                rootnode.right.height = rootnode.height + 1;
                if (treeHight < rootnode.right.height) {
                    treeHight = rootnode.right.height;
                }
                traverse(rootnode.right);
            }
        }
        return;
    }

    void deleteKey(int key)
    {
        root = deleteRec(root, key);
    }

    /* A recursive function to insert a new key in BST */
    Node deleteRec(Node root, int key)
    {
        /* Base Case: If the tree is empty */
        if (root == null)  return root;

        /* Otherwise, recur down the tree */
        if (key < root.key)
            root.left = deleteRec(root.left, key);
        else if (key > root.key)
            root.right = deleteRec(root.right, key);

            // if key is same as root's key, then This is the node
            // to be deleted
        else
        {
            // node with only one child or no child
            if (root.left == null)
            {
                return root.right;
            }
            else if (root.right == null)
            {
                return root.left;
            }

            root.key = minValue(root.right);

            root.right = deleteRec(root.right, root.key);
        }

        return root;
    }

    int minValue(Node root)
    {
        int minv = root.key;
        while (root.left != null)
        {
            minv = root.left.key;
            root = root.left;
        }
        return minv;
    }

    public void getbacktodraw(View view)
    {
        Intent intent = new Intent(web_binary.this, binarytree.class);
        intent.putExtra("idcont","1");
        intent.putExtra("content",cont);
        startActivity(intent);
    }

    public void gethomebinary(View view)
    {
        Intent intent = new Intent(web_binary.this, mainpage.class);
        startActivity(intent);
    }


    public class MyView extends View
    {
        int width,height;
        public MyView(Context context, int w, int h)
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

            /*Paint titlepaint = new Paint();
            titlepaint.setTextAlign(Paint.Align.LEFT);
            titlepaint.setTypeface(Typeface.create(Typeface.DEFAULT,Typeface.BOLD));
            titlepaint.setColor(Color.rgb(255,69,0));
            titlepaint.setTextSize(70);
            canvas.drawText("AdEx Tree Tool",  210,130,titlepaint);
            */
            Node cur=root;
            int y=200;
            int x=200 + 150*((int)Math.pow(2,treeHight - 1) - 1);
            fillCanvas(subcanvas,x,y,cur);

            System.out.println(subcanvas.getWidth());
            System.out.println(subcanvas.getHeight());

        }
    }
    void insert(int key) {
        root = insertRec(root, key);
    }

    /* A recursive function to insert a new key in BST */
    Node insertRec(Node root, int key) {

        /* If the tree is empty, return a new node */
        if (root == null) {
            root = new Node(key,null,1);
            treeHight=1;
            return root;
        }

        /* Otherwise, recur down the tree */
        if (key < root.key)
        {
            if(root.left==null)
            {
                root.left=new Node(key,root,root.height + 1);
                if(treeHight<(root.height + 1))
                {
                    treeHight=root.height + 1;
                }
                return root;
            }
            root.left = insertRec(root.left, key);
        }

        else if (key >= root.key)
        {
            if(root.right==null)
            {
                root.right=new Node(key,root,root.height + 1);
                if(treeHight<(root.height + 1))
                {
                    treeHight=root.height + 1;
                }
                return root;
            }
            root.right = insertRec(root.right, key);
        }

        /* return the (unchanged) node pointer */
        return root;
    }
}
