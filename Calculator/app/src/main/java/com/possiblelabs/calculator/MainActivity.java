package com.possiblelabs.calculator;

import android.app.Activity;
import android.media.Image;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;


public class MainActivity extends Activity implements View.OnClickListener {

    private final static int BUTTONS_SIZE = 11;
    private Button[] btnNumbers;

    private TextView txtResult;
    private TextView txtOperation;
    private Button btnClear;
    private Button btnPlus;
    private Button btnMinus;
    private Button btnMultiply;
    private Button btnDivide;
    private Button btnEquals;
    private Button btnBack;
    private Button btnNeg;

    private double op1 = -1;
    private boolean op1_set = false;
    private double op2 = -1;
    private int result = 0;
    private boolean plus = false;
    private boolean minus = false;
    private boolean multiply = false;
    private  boolean divide = false;
    private boolean neg = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNumbers = new Button[BUTTONS_SIZE];
        txtResult = (TextView) findViewById(R.id.txt_result);
        txtOperation = (TextView) findViewById(R.id.txt_operation);
        btnClear = (Button) findViewById(R.id.btn_clear);
        btnPlus = (Button) findViewById(R.id.btn_plus);
        btnMinus = (Button) findViewById(R.id.btn_minus);
        btnMultiply = (Button) findViewById(R.id.btn_multiply);
        btnDivide = (Button) findViewById(R.id.btn_divide);
        btnEquals = (Button) findViewById(R.id.btn_equals);
        btnBack = (Button) findViewById(R.id.btn_back);
        btnNeg = (Button) findViewById(R.id.btn_neg);

        btnNumbers[0] = (Button) findViewById(R.id.btn_0);
        btnNumbers[1] = (Button) findViewById(R.id.btn_1);
        btnNumbers[2] = (Button) findViewById(R.id.btn_2);
        btnNumbers[3] = (Button) findViewById(R.id.btn_3);
        btnNumbers[4] = (Button) findViewById(R.id.btn_4);
        btnNumbers[5] = (Button) findViewById(R.id.btn_5);
        btnNumbers[6] = (Button) findViewById(R.id.btn_6);
        btnNumbers[7] = (Button) findViewById(R.id.btn_7);
        btnNumbers[8] = (Button) findViewById(R.id.btn_8);
        btnNumbers[9] = (Button) findViewById(R.id.btn_9);
        btnNumbers[10] = (Button) findViewById(R.id.btn_dot);

        for (Button btn : btnNumbers) {
            btn.setOnClickListener(this);
        }

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                op1 = -1;
                op1_set = false;
                op2 = -1;
                plus = false;
                minus = false;
                multiply = false;
                divide = false;
                txtOperation.setText("");
                txtResult.setText("0");
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String texto = txtResult.getText().toString();
                int tam = texto.length();
                if(!op1_set)
                    if(tam>0){
                        if(tam==1) {
                            txtResult.setText("0");
                        }else{
                            if(tam==2){
                                texto = texto.charAt(0)+"";
                                txtResult.setText(texto);
                            }else{
                                texto = texto.substring(0, tam-2);
                                txtResult.setText(texto);
                        }
                    }
                }
            }
        });

        btnNeg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String texto = txtResult.getText().toString();
                int tam = texto.length();
                if(op1_set){
                    texto = "-";
                    neg=true;
                }else{
                    if(tam==1){
                        if(texto.equals("-")) {
                            texto = "0";
                            neg = false;
                        }else{
                            texto = "-" +texto;
                            neg = true;
                        }
                    }else{
                        if(tam>1)
                            if(texto.charAt(0)=='-') {
                                texto = texto.substring(1);
                                neg = false;
                            }else {
                                texto = "-" + texto;
                                neg = true;
                            }
                    }
                }
                txtResult.setText(texto);
            }
        });

        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                plus = true;
                txtOperation.setText("+");
                op1 = Double.parseDouble(txtResult.getText().toString());
                op1_set = true;
                neg=false;
            }
        });

        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                minus = true;
                txtOperation.setText("-");
                op1 = Double.parseDouble(txtResult.getText().toString());
                op1_set = true;
                neg=false;
            }
        });

        btnMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                multiply = true;
                txtOperation.setText("*");
                op1 = Double.parseDouble(txtResult.getText().toString());
                op1_set = true;
                neg=false;
            }
        });

        btnDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                divide = true;
                txtOperation.setText("/");
                op1 = Double.parseDouble(txtResult.getText().toString());
                op1_set = true;
                neg=false;
            }
        });


        btnEquals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(plus) {
                    op2 = op1 + Double.parseDouble(txtResult.getText().toString());
                    plus = false;
                }else{
                    if(minus){
                        op2 = op1 - Double.parseDouble(txtResult.getText().toString());
                        minus = false;
                    }else{
                        if(multiply){
                            op2 = op1 * Double.parseDouble(txtResult.getText().toString());
                            multiply = false;
                        }else{
                            op2 = op1 / Double.parseDouble(txtResult.getText().toString());
                            divide = false;
                        }
                    }
                }
                txtResult.setText(op2 + "");
                txtOperation.setText("");
                op1_set=false;
                neg=false;
            }
        });

    }

    @Override
    public void onClick(View view) {
        Button pressed = (Button) view;
        for (Button btn : btnNumbers) {
            if (pressed == btn) {

                if (op1_set) {
                    if(!neg)
                    txtResult.setText("");
                    op1_set = false;
                }

                String v = btn.getText().toString();
                txtResult.append(v);
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
