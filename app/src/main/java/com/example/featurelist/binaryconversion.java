package com.example.featurelist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Bundle;
import android.view.WindowManager;

import static android.text.InputType.TYPE_CLASS_NUMBER;
import static android.text.InputType.TYPE_CLASS_TEXT;
import static android.text.InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS;

public class binaryconversion extends AppCompatActivity
{

    private EditText input;
    private TextView binary, decimal, octal, hexa;
    private Button calculate;
    private Spinner spinConversionSelection;
    public String[] spinConversionItem = new String[]{"Decimal", "Binary", "Octal", "Hexadecimal"};
    private ArrayAdapter<String> spinAdapter;
    private int spinPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_binaryconversion);


        spinPosition = 0;

        input = (EditText) findViewById(R.id.input);
        input.addTextChangedListener(new MyTextWatcher(input));

        binary = (TextView) findViewById(R.id.textBinary);
        decimal = (TextView) findViewById(R.id.textDecimal);
        octal = (TextView) findViewById(R.id.textOctal);
        hexa = (TextView) findViewById(R.id.textHexa);

        calculate = (Button) findViewById(R.id.calculate);

        spinConversionSelection = (Spinner) findViewById(R.id.spinConversionSelection);
        spinAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                spinConversionItem);
        spinConversionSelection.setAdapter(spinAdapter);

        spinConversionSelection.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spinPosition = i;

                input.setText("");
                decimal.setText("");
                binary.setText("");
                octal.setText("");
                hexa.setText("");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculation();
            }
        });


    }

    private void calculation() {


        calculateDecimal();
        calculateBinary();
        calculateOctal();
        calculateHexa();

    }

    private void calculateDecimal() {
        String value = input.getText().toString();

        if (!checkingInputValidation()) {
            switch (spinPosition) {
                case 0:
                    try {
                        decimal.setText(value);
                        decimal.setTextSize(20);
                    } catch (Exception e) {
                        input.setError("Something Wrong");
                        requestFocus(input);
                    }
                    break;
                case 1:
                    try {
                        decimal.setText("" + Long.parseLong(value, 2));
                        decimal.setTextSize(20);
                    } catch (Exception e) {
                        input.setError("Something Wrong");
                        requestFocus(input);
                    }
                    break;
                case 2:
                    try {
                        decimal.setText("" + Long.parseLong(value, 8));
                        decimal.setTextSize(20);
                    } catch (Exception e) {
                        input.setError("Something Wrong");
                        requestFocus(input);
                    }
                    break;
                case 3:
                    try {
                        decimal.setText("" +  Long.parseLong(value, 16));
                        decimal.setTextSize(20);
                    } catch (Exception e) {
                        input.setError("Something Wrong");
                        requestFocus(input);
                    }

                    break;
            }
        }


    }

    private void calculateBinary() {
        String value = input.getText().toString();

        if (!checkingInputValidation()) {
            switch (spinPosition) {
                case 0:
                    try {
                        binary.setText("" + Long.toBinaryString(Long.valueOf(value)));
                        binary.setTextSize(20);
                    } catch (Exception e) {
                        input.setError("Something Wrong");
                        requestFocus(input);
                    }
                    break;
                case 1:
                    try {
                        binary.setText(value);
                        binary.setTextSize(20);
                    } catch (Exception e) {
                        input.setError("Something Wrong");
                        requestFocus(input);
                    }
                    break;
                case 2:
                    try {
                        binary.setText("" + Long.toBinaryString(Long.parseLong(value, 8)));
                        binary.setTextSize(20);
                    } catch (Exception e) {
                        input.setError("Something Wrong 1234");
                        requestFocus(input);
                    }
                    break;
                case 3:
                    try {
                        binary.setText("" + Long.toBinaryString(Long.parseLong(value, 16)));
                        binary.setTextSize(20);
                    } catch (Exception e) {
                        input.setError("Something Wrong");
                        requestFocus(input);
                    }
                    break;
            }
        }
    }

    private void calculateOctal() {
        String value = input.getText().toString();

        if (!checkingInputValidation()) {
            switch (spinPosition) {
                case 0:
                    try {
                        octal.setText("" + Long.toOctalString(Long.valueOf(value)));
                        octal.setTextSize(20);
                    } catch (Exception e) {
                        input.setError("Something Wrong");
                        requestFocus(input);
                    }

                    break;
                case 1:
                    try {
                        long l = Long.parseLong(value, 2);
                        octal.setText("" + Long.toOctalString(l));
                        octal.setTextSize(20);

                    } catch (Exception e) {
                        input.setError("Something Wrong");
                        requestFocus(input);
                    }
                    break;
                case 2:
                    try {
                        octal.setText(value);
                        octal.setTextSize(20);

                    } catch (Exception e) {
                        input.setError("Something Wrong");
                        requestFocus(input);
                    }
                    break;
                case 3:
                    try {
                        octal.setText("" + Long.toOctalString(Long.parseLong(value, 16)));
                        octal.setTextSize(20);

                    } catch (Exception e) {
                        input.setError("Something Wrong");
                        requestFocus(input);
                    }
                    break;
            }
        }
    }

    private void calculateHexa() {
        String value = input.getText().toString();

        if (!checkingInputValidation()) {
            switch (spinPosition) {
                case 0:
                    try {
                        hexa.setText("" + Long.toHexString(Long.valueOf(value)));
                        hexa.setTextSize(20);
                    } catch (Exception e) {
                        input.setError("Something Wrong");
                        requestFocus(input);
                    }

                    break;
                case 1:
                    try {
                        hexa.setText("" + Long.toHexString(Long.parseLong(value, 2)));
                        hexa.setTextSize(20);
                    } catch (Exception e) {
                        input.setError("Something Wrong");
                        requestFocus(input);
                    }

                    break;
                case 2:
                    try {
                        hexa.setText("" + Long.toHexString(Long.parseLong(value, 8)));
                        hexa.setTextSize(20);
                    } catch (Exception e) {
                        input.setError("Something Wrong");
                        requestFocus(input);
                    }
                    break;
                case 3:
                    try {
                        hexa.setText(value);
                        hexa.setTextSize(20);
                    } catch (Exception e) {
                        input.setError("Something Wrong");
                        requestFocus(input);
                    }
                    break;
            }
        }
    }

    private boolean checkingInputValidation() {
        String gettingInput = input.getText().toString();
        if (input.getText().toString().trim().isEmpty()) {
            input.setError("Field is empty");
            requestFocus(input);
            return true;
        } else if (gettingInput.matches(".*[G-Z].*") || gettingInput.matches(".*[g-z].*")) {
            input.setError("Insert Captial Letter for A to F");
            requestFocus(input);
            return true;
        }else if (spinPosition == 2 && gettingInput.matches(".*[8-9].*")) {
            input.setError("Value must be 0 to 7");
            requestFocus(input);
            return true;
        }else if (spinPosition == 1 && gettingInput.matches(".*[2-9].*")) {
            input.setError("Value must be 0 or 1");
            requestFocus(input);
            return true;
        }else if (gettingInput.length() > 15) {
            input.setError("Insertion limited to 6 digit");
            requestFocus(input);
            return true;
        }
        return false;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    public void getbacktomain(View view)
    {
        Intent intent = new Intent(binaryconversion.this,mainpage.class);
        startActivity(intent);
    }


    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }


        @Override
        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.input:
                    validateInput();
                    break;

            }
        }
    }

    private void validateInput() {
        if (spinPosition == 0) {
            input.setInputType(TYPE_CLASS_NUMBER);
        } else if (spinPosition == 1) {
            input.setInputType(TYPE_CLASS_NUMBER);
        } else if (spinPosition == 2) {
            input.setInputType(TYPE_CLASS_NUMBER);
        } else {
            input.setInputType(TYPE_TEXT_FLAG_CAP_CHARACTERS);
        }
    }

}
