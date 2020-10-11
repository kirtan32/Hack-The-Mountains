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


class Nodeavl {
    int key;
    int height;
    int heightkey;
    Nodeavl left, right;

    public Nodeavl(int item) {
        key = item;
        heightkey=1;
        height=1;
        left = right = null;
    }
}

public class web_avl extends AppCompatActivity
{

    String cont;
    int arrayno[];
    Bitmap bmp,scaledbmp;
    Nodeavl root;
    int treeHight;
    PdfDocument mypdf;
    EditText savetext;
    String filena;
    Canvas canvas;
    MyViewavl yourView;
    EditText insertion,deletion;
    int ll,rr,lr,rl;
    ScrollView ss1;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_web_avl);


        ll=0;
        rr=0;
        lr=0;
        rl=0;

        savetext = (EditText)findViewById(R.id.editTextavl);
        insertion=(EditText)findViewById(R.id.insertavl);
        deletion=(EditText)findViewById(R.id.deleteavl);

        Intent intent=getIntent();
        cont=intent.getStringExtra("content");

        root=null;
        treeHight=0;

        solveavlsol();

        ss1=(ScrollView)findViewById(R.id.scrollavl);

        if(root==null)
        {
            treeHight=1;
        }
        else
        {
            root.heightkey=1;
            treeHight=1;
        }
        traverse(root);

        initcompo();

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void solveavlsol() {
        String strarray[] = cont.split(",");
        try {
            arrayno = new int[strarray.length];
            for (int i = 0; i < strarray.length; i++) {
                arrayno[i] = Integer.parseInt(strarray[i]);
                root=insert(root,arrayno[i]);
            }

            initBitmap();

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Something wrong", Toast.LENGTH_SHORT).show();
        }
    }

    int height(Nodeavl N) {
        if (N == null)
            return 0;

        return N.height;
    }

    // A utility function to get maximum of two integers
    int max(int a, int b) {
        return (a > b) ? a : b;
    }

    Nodeavl rightRotate(Nodeavl y) {
        Nodeavl x = y.left;
        Nodeavl T2 = x.right;

        // Perform rotation
        x.right = y;
        y.left = T2;

        // Update heights
        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;

        // Return new root
        return x;
    }

    // A utility function to left rotate subtree rooted with x
    // See the diagram given above.
    Nodeavl leftRotate(Nodeavl x) {
        Nodeavl y = x.right;
        Nodeavl T2 = y.left;

        // Perform rotation
        y.left = x;
        x.right = T2;

        //  Update heights
        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;

        // Return new root
        return y;
    }

    // Get Balance factor of node N
    int getBalance(Nodeavl N) {
        if (N == null)
            return 0;

        return height(N.left) - height(N.right);
    }

    Nodeavl insert(Nodeavl node, int key) {

        /* 1.  Perform the normal BST insertion */
        if (node == null)
            return (new Nodeavl(key));

        if (key < node.key)
            node.left = insert(node.left, key);
        else if (key > node.key)
            node.right = insert(node.right, key);
        else // Duplicate keys not allowed
            return node;

        /* 2. Update height of this ancestor node */
        node.height = 1 + max(height(node.left),
                height(node.right));

        /* 3. Get the balance factor of this ancestor
              node to check whether this node became
              unbalanced */
        int balance = getBalance(node);

        // If this node becomes unbalanced, then there
        // are 4 cases Left Left Case
        if (balance > 1 && key < node.left.key)
        {
            ll++;
            return rightRotate(node);
        }

        // Right Right Case
        if (balance < -1 && key > node.right.key)
        {
            rr++;
            return leftRotate(node);
        }

        // Left Right Case
        if (balance > 1 && key > node.left.key) {
            node.left = leftRotate(node.left);
            lr++;
            return rightRotate(node);
        }

        // Right Left Case
        if (balance < -1 && key < node.right.key) {
            node.right = rightRotate(node.right);
            rl++;
            return leftRotate(node);
        }

        /* return the (unchanged) node pointer */
        return node;
    }

    Nodeavl minValueNode(Nodeavl node)
    {
        Nodeavl current = node;

        while (current.left != null)
            current = current.left;

        return current;
    }

    Nodeavl deleteNode(Nodeavl root, int key)
    {
        // STEP 1: PERFORM STANDARD BST DELETE
        if (root == null)
            return root;

        // If the key to be deleted is smaller than
        // the root's key, then it lies in left subtree
        if (key < root.key)
            root.left = deleteNode(root.left, key);

            // If the key to be deleted is greater than the
            // root's key, then it lies in right subtree
        else if (key > root.key)
            root.right = deleteNode(root.right, key);

            // if key is same as root's key, then this is the node
            // to be deleted
        else
        {

            // node with only one child or no child
            if ((root.left == null) || (root.right == null))
            {
                Nodeavl temp = null;
                if (temp == root.left)
                    temp = root.right;
                else
                    temp = root.left;

                // No child case
                if (temp == null)
                {
                    temp = root;
                    root = null;
                }
                else // One child case
                    root = temp; // Copy the contents of
                // the non-empty child
            }
            else
            {

                // node with two children: Get the inorder
                // successor (smallest in the right subtree)
                Nodeavl temp = minValueNode(root.right);

                // Copy the inorder successor's data to this node
                root.key = temp.key;

                // Delete the inorder successor
                root.right = deleteNode(root.right, temp.key);
            }
        }

        // If the tree had only one node then return
        if (root == null)
            return root;

        // STEP 2: UPDATE HEIGHT OF THE CURRENT NODE
        root.height = max(height(root.left), height(root.right)) + 1;

        // STEP 3: GET THE BALANCE FACTOR OF THIS NODE (to check whether
        // this node became unbalanced)
        int balance = getBalance(root);

        // If this node becomes unbalanced, then there are 4 cases
        // Left Left Case
        if (balance > 1 && getBalance(root.left) >= 0)
            return rightRotate(root);

        // Left Right Case
        if (balance > 1 && getBalance(root.left) < 0)
        {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        // Right Right Case
        if (balance < -1 && getBalance(root.right) <= 0)
            return leftRotate(root);

        // Right Left Case
        if (balance < -1 && getBalance(root.right) > 0)
        {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
    }

    public void initBitmap()
    {
        bmp= BitmapFactory.decodeResource(getResources(),R.drawable.logo);
        scaledbmp = Bitmap.createScaledBitmap(bmp,200,200,false);
    }

    public void initcompo()
    {
        int pageWidth=200 + 300*(int)Math.pow(2,treeHight - 1);
        int pageHeight=600 + 300*(treeHight-1);

        ScrollView.LayoutParams params = new ScrollView.LayoutParams(pageWidth,pageHeight);
        // LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(600,800);
        yourView = new MyViewavl(this,pageWidth,pageHeight);
        yourView.setLayoutParams(params);
        ss1.addView(yourView);
    }

    public void getbacktodraw(View view)
    {
        Intent intent = new Intent(web_avl.this, treevisualizer.class);
        intent.putExtra("idcont","1");
        intent.putExtra("content",cont);
        startActivity(intent);
    }

    public void gethomebinary(View view)
    {
        Intent intent = new Intent(web_avl.this, mainpage.class);
        startActivity(intent);
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
            root=insert(root,no);
            ss1.removeAllViews();
            if(root==null)
            {
                treeHight=1;
            }
            else
            {
                root.heightkey=1;
                treeHight=1;
            }
            traverse(root);
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
        try
        {
            int no=Integer.parseInt(deletion.getText().toString());
            root=deleteNode(root,no);
            if(root==null)
            {
                treeHight=1;
            }
            else
            {
                root.heightkey=1;
                treeHight=1;
            }
            traverse(root);
            ss1.removeAllViews();
            initcompo();

            deletion.setText("");
            Toast.makeText(getApplicationContext(), "Delete "+no, Toast.LENGTH_SHORT).show();

        }
        catch (Exception e)
        {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Invalid Deletion", Toast.LENGTH_LONG).show();
        }
    }

    public void traverse(Nodeavl rootnode)
    {
        if(rootnode!=null) {
            if (rootnode.left != null) {
                rootnode.left.heightkey = rootnode.heightkey + 1;
                if (treeHight < rootnode.left.heightkey) {
                    treeHight = rootnode.left.heightkey;
                }
                traverse(rootnode.left);
            }
            if (rootnode.right != null) {
                rootnode.right.heightkey = rootnode.heightkey + 1;
                if (treeHight < rootnode.right.heightkey) {
                    treeHight = rootnode.right.heightkey;
                }
                traverse(rootnode.right);
            }
        }
        return;
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

        canvas.drawText("AVL Tree Visualizer",  210,130,titlepaint);

        titlepaint.setColor(Color.rgb(0,215,221));
        samplepaint.setColor(Color.rgb(255,255,255));
        samplepaint.setTextSize(20);

        Paint textpaint = new Paint();
        textpaint.setColor(Color.rgb(0,0,0));
        textpaint.setTypeface(Typeface.create(Typeface.DEFAULT,Typeface.BOLD));
        textpaint.setTextSize(25);

        Nodeavl cur=root;
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
    public void fillCanvas(Canvas canvas1, int x, int y, Nodeavl cur)
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
                int spaces = 150 * ((int) Math.pow(2, treeHight - cur.heightkey - 1));
                canvas1.drawLine(x + 50, y + 50, x - spaces + 40, y + 350, samplepaint);
            }
            if (cur.right != null) {
                int spaces = 150 * ((int) Math.pow(2, treeHight - cur.heightkey - 1));
                canvas1.drawLine(x + 50, y + 50, x + spaces + 40, y + 350, samplepaint);
            }

            if (cur.left != null) {
                int spaces = 150 * ((int) Math.pow(2, treeHight - cur.heightkey - 1));
                fillCanvas(canvas1, x - spaces, y + 300, cur.left);
            }
            if (cur.right != null) {
                int spaces = 150 * ((int) Math.pow(2, treeHight - cur.heightkey - 1));
                fillCanvas(canvas1, x + spaces, y + 300, cur.right);
            }

            canvas1.drawOval(x, y, x + 100, y + 100, titlepaint);
            canvas1.drawText("" + cur.key, x + 30, y + 55, textpaint);

        }
        return;

    }



    public class MyViewavl extends View
    {
        int width,height;
        public MyViewavl(Context context, int w, int h)
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
            titlepaint.setColor(Color.RED);
            titlepaint.setTextSize(35);

            subcanvas.drawText("Operations count: ",20,35,titlepaint);
            titlepaint.setTypeface(Typeface.create(Typeface.DEFAULT,Typeface.NORMAL));
            titlepaint.setColor(Color.WHITE);
            titlepaint.setTextSize(28);

            subcanvas.drawText("Left Left:  "+ll,20,85,titlepaint);
            subcanvas.drawText("Right Right: "+rr,20,115,titlepaint);
            subcanvas.drawText("Left Right:  "+lr,20,145,titlepaint);
            subcanvas.drawText("Right Left:  "+rl,20,175,titlepaint);

            Nodeavl cur=root;
            int y=200;
            int x=200 + 150*((int)Math.pow(2,treeHight - 1) - 1);
            fillCanvas(subcanvas,x,y,cur);

            System.out.println(subcanvas.getWidth());
            System.out.println(subcanvas.getHeight());

        }
    }


}
