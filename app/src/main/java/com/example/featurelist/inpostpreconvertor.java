package com.example.featurelist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Stack;

public class inpostpreconvertor extends AppCompatActivity {

    Spinner fixspin;
    EditText fixedit;
    TextView fixoutput;
    Button fixsubmit;
    TextView fixdiscription;
    String[] fix2dstring;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_inpostpreconvertor);

        fix2dstring=new String[6];
        fix2dstring[0]="Infix to Postfix" ;
        fix2dstring[1]="Infix to Prefix";
        fix2dstring[2]="Postfix to Infix";
        fix2dstring[3]="Postfix to Prefix";
        fix2dstring[4]="Prefix to Postfix";
        fix2dstring[5]="Prefix to Infix";



        fixspin=(Spinner)findViewById(R.id.fixesnames);
        fixedit=(EditText)findViewById(R.id.stringfixconvertor);
        fixsubmit=(Button)findViewById(R.id.convertfix);
        fixoutput=(TextView)findViewById(R.id.fixoutput);
        fixdiscription=(TextView)findViewById(R.id.fixdisc);

        String discription="Instruction\n" +
                "1. Operands allowed are:\n" +
                "\tA-Z\n" +
                "\ta-z\n" +
                "\t0-9\n" +
                "2. Operators allowed with their priority:\n" +
                "   In descending order\n" +
                "\t^ : exponentiation\n" +
                "\t* : Multiplication, / : Division, % : Modulus\n" +
                "\t+ : Addition , - : Subtraction\n" +
                "\t& : Bitwise AND\n" +
                "\t| : Bitwise OR";
        fixdiscription.setText(discription);
    }

    static boolean isOperand(char c)
    {
        return (!(c >= 'a' && c <= 'z') &&
                !(c >= '0' && c <= '9') &&
                !(c >= 'A' && c <= 'Z'));
    }

    static String infixToPrefix(String infix)
    {
        // stack for operators.
        Stack<Character> operators = new Stack<Character>();

        // stack for operands.
        Stack<String> operands = new Stack<String>();

        for (int i = 0; i < infix.length(); i++)
        {

            // If current character is an
            // opening bracket, then
            // push into the operators stack.
            if (infix.charAt(i) == '(')
            {
                operators.push(infix.charAt(i));
            }

            // If current character is a
            // closing bracket, then pop from
            // both stacks and push result
            // in operands stack until
            // matching opening bracket is
            // not found.
            else if (infix.charAt(i) == ')')
            {
                while (!operators.empty() &&
                        operators.peek() != '(')
                {

                    // operand 1
                    String op1 = operands.peek();
                    operands.pop();

                    // operand 2
                    String op2 = operands.peek();
                    operands.pop();

                    // operator
                    char op = operators.peek();
                    operators.pop();

                    // Add operands and operator
                    // in form operator +
                    // operand1 + operand2.
                    String tmp = op + op2 + op1;
                    operands.push(tmp);
                }

                // Pop opening bracket
                // from stack.
                operators.pop();
            }

            // If current character is an
            // operand then push it into
            // operands stack.
            else if (!isOperand(infix.charAt(i)))
            {
                operands.push(infix.charAt(i) + "");
            }

            // If current character is an
            // operator, then push it into
            // operators stack after popping
            // high priority operators from
            // operators stack and pushing
            // result in operands stack.
            else
            {
                while (!operators.empty() &&
                        Prec(infix.charAt(i)) <=
                                Prec(operators.peek()))
                {

                    String op1 = operands.peek();
                    operands.pop();

                    String op2 = operands.peek();
                    operands.pop();

                    char op = operators.peek();
                    operators.pop();

                    String tmp = op + op2 + op1;
                    operands.push(tmp);
                }

                operators.push(infix.charAt(i));
            }
        }

        // Pop operators from operators
        // stack until it is empty and
        // operation in add result of
        // each pop operands stack.
        while (!operators.empty())
        {
            String op1 = operands.peek();
            operands.pop();

            String op2 = operands.peek();
            operands.pop();

            char op = operators.peek();
            operators.pop();

            String tmp = op + op2 + op1;
            operands.push(tmp);
        }

        // Final prefix expression is
        // present in operands stack.
        return operands.peek();
    }


    // Get Infix for a given postfix
// expression
    static String posttoInfix(String exp)
    {
        Stack<String> s = new Stack<String>();

        for (int i = 0; i < exp.length(); i++)
        {
            // Push operands
            if (!isOperand(exp.charAt(i)))
            {
                s.push(exp.charAt(i) + "");
            }

            // We assume that input is
            // a valid postfix and expect
            // an operator.
            else
            {
                String op1 = s.peek();
                s.pop();
                String op2 = s.peek();
                s.pop();
                s.push("(" + op2 + exp.charAt(i) +
                        op1 + ")");
            }
        }

        // There must be a single element
        // in stack now which is the required
        // infix.
        return s.peek();
    }

    static int Prec(char ch)
    {
        switch (ch)
        {
            case '&':
            case '|':
                return 2;
            case '+':
            case '-':
                return 3;

            case '*':
            case '/':
            case '%':
                return 4;

            case '^':
                return 5;
        }
        return -1;
    }

    static boolean isOperator(char x)
    {
        switch (x)
        {
            case '|':
            case '&':
            case '^':
            case '%':
            case '+':
            case '-':
            case '/':
            case '*':
                return true;
        }
        return false;
    }

    static String postToPre(String post_exp)
    {
        Stack<String> s = new Stack<String>();

        // length of expression
        int length = post_exp.length();

        // reading from right to left
        for (int i = 0; i < length; i++) {

            // check if symbol is operator
            if (isOperator(post_exp.charAt(i))) {

                // pop two operands from stack
                String op1 = s.peek();
                s.pop();
                String op2 = s.peek();
                s.pop();

                // concat the operands and operator
                String temp = post_exp.charAt(i) + op2 + op1;

                // Push String temp back to stack
                s.push(temp);
            }

            // if symbol is an operand
            else {

                // push the operand to the stack
                s.push(post_exp.charAt(i) + "");
            }
        }

        // stack[0] contains the Prefix expression
        return s.peek();
    }

    // Convert prefix to Infix expression
    public static String pretoin(String str)
    {
        Stack<String> stack = new Stack<>();

        // Length of expression
        int l = str.length();

        // Reading from right to left
        for(int i = l - 1; i >= 0; i--)
        {
            char c = str.charAt(i);
            if (isOperator(c))
            {
                String op1 = stack.pop();
                String op2 = stack.pop();

                // Concat the operands and operator
                String temp = "(" + op1 + c + op2 + ")";
                stack.push(temp);
            }
            else
            {

                // To make character to string
                stack.push(c + "");
            }
        }
        return stack.pop();
    }

    static String preToPost(String pre_exp)
    {

        Stack<String> s= new Stack<String>();

        // length of expression
        int length = pre_exp.length();

        // reading from right to left
        for (int i = length - 1; i >= 0; i--)
        {

            // check if symbol is operator
            if (isOperator(pre_exp.charAt(i)))
            {

                // pop two operands from stack
                String op1 = s.peek(); s.pop();
                String op2 = s.peek(); s.pop();

                // concat the operands and operator
                String temp = op1 + op2 + pre_exp.charAt(i);

                // Push String temp back to stack
                s.push(temp);
            }

            // if symbol is an operand
            else
            {
                // push the operand to the stack
                s.push( pre_exp.charAt(i)+"");
            }
        }

        // stack contains only the Postfix expression
        return s.peek();
    }

    static String infixToPostfix(String exp)
    {
        // initializing empty String for result
        String result = "";

        // initializing empty stack
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i<exp.length(); ++i)
        {
            char c = exp.charAt(i);

            // If the scanned character is an operand, add it to output.
            if (Character.isLetterOrDigit(c))
                result += c;

                // If the scanned character is an '(', push it to the stack.
            else if (c == '(')
                stack.push(c);

                // If the scanned character is an ')', pop and output from the stack
                // until an '(' is encountered.
            else if (c == ')')
            {
                while (!stack.isEmpty() && stack.peek() != '(')
                    result += stack.pop();

                if (!stack.isEmpty() && stack.peek() != '(')
                    return "Invalid Expression"; // invalid expression
                else
                    stack.pop();
            }
            else // an operator is encountered
            {
                while (!stack.isEmpty() && Prec(c) <= Prec(stack.peek())){
                    if(stack.peek() == '(')
                        return "Invalid Expression";
                    result += stack.pop();
                }
                stack.push(c);
            }

        }

        // pop all the operators from the stack
        while (!stack.isEmpty()){
            if(stack.peek() == '(')
                return "Invalid Expression";
            result += stack.pop();
        }
        System.out.println("-> "+result);
        return result;
    }

    public void gethome(View view)
    {
        Intent intent = new Intent(inpostpreconvertor.this, mainpage.class);
        startActivity(intent);
    }

    public void setoutpur(View view)
    {

        try{
            if(fixedit.getText().toString().equals(""))
            {
                Toast.makeText(getApplicationContext(), "Something wrong", Toast.LENGTH_SHORT).show();
            }
            else
            {
                String getstr=fixspin.getSelectedItem().toString();
                String editstr=fixedit.getText().toString();
                    if(getstr.equals("Infix to Postfix"))
                    {
                        fixoutput.setText(infixToPostfix(editstr));
                    }
                    else if(getstr.equals("Infix to Prefix"))
                    {
                        fixoutput.setText(infixToPrefix(editstr));
                    }
                    else if(getstr.equals("Postfix to Infix"))
                    {
                        fixoutput.setText(posttoInfix(editstr));
                    }
                    else if(getstr.equals("Postfix to Prefix"))
                    {
                        fixoutput.setText(postToPre(editstr));
                    }
                    else if(getstr.equals("Prefix to Postfix"))
                    {
                        fixoutput.setText(preToPost(editstr));
                    }
                    else if(getstr.equals("Prefix to Infix"))
                    {
                        fixoutput.setText(pretoin(editstr));
                    }

            }
        }
        catch(Exception e)
        {
            Toast.makeText(getApplicationContext(), "Something wrong or Error", Toast.LENGTH_SHORT).show();
        }

    }
}
