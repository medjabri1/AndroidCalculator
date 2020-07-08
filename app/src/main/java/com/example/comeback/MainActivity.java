package com.example.comeback;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.calcTV);
        textView.setText("");

    }

    public void num(View v) {
        Button btn = (Button) v;
        String currentText = textView.getText().toString();
        String btnText = btn.getText().toString();

        if(btnText.equals("+") || btnText.equals("*") || btnText.equals("-") || btnText.equals("/") || btnText.equals("%")) {

            if(!currentText.equals("")) {

                if(!currentText.contains("+") && !currentText.contains("*") && !currentText.contains("-") && !currentText.contains("/") && !currentText.contains("%")) {
                    if(Float.parseFloat(currentText) != 0) {
                        textView.setText(currentText + btnText);
                    }
                } else {
                    calc(v);
                    textView.setText(textView.getText().toString() + btnText);
                }
            }
        } else {

            //Comma
            if(btnText.equals(".")) {
                if(currentText.contains(".")) {
                    int posPoint = currentText.indexOf("."), posOperator;
                    if(!currentText.contains("+") && !currentText.contains("*") && !currentText.contains("-") && !currentText.contains("/") && !currentText.contains("%")) {
                        return;
                    } else {
                        if(currentText.charAt(currentText.length() -1) == '0') return;
                    }
                }
            }

            //Numbers
            if(!btnText.equals("0") || !currentText.equals("")) {
                textView.setText(currentText + btnText);
            }
        }
    }

    public void calc(View v) {
        String currentText = textView.getText().toString();
        String num1 = "", num2 = "", operation = "";
        float nbr1 = 0, nbr2 = 0, result = 0;



        for(int i=0; i < currentText.length(); i++) {
            if(currentText.charAt(i) != '+' && currentText.charAt(i) != '*' && currentText.charAt(i) != '-' && currentText.charAt(i) != '/' && currentText.charAt(i) != '%') {
                if(operation.equals("")) {
                    num1 += currentText.charAt(i);
                } else {
                    num2 += currentText.charAt(i);
                }
            } else {
                operation += currentText.charAt(i);
            }
        }

        if(!num1.equals("")) {
            nbr1 = Float.parseFloat(num1);
        }

        if(!num2.equals("")) {
            nbr2 = Float.parseFloat(num2);
        }

        if(operation.equals("")) operation = "+";

        if(operation.equals("+")) {
            result = nbr1 + nbr2;
        } else if(operation.equals("*")) {

            if(num1.equals("")) {
                nbr1 = 1;
            }

            if(num2.equals("")) {
                nbr2 = 1;
            }

            result = nbr1 * nbr2;
        } else if(operation.equals("-")) {

            result = nbr1 - nbr2;

        } else if(operation.equals("/")) {

            if(nbr1 == 0) return;
            if(nbr2 == 0) {
                textView.setText(textView.getText().toString().substring(0, textView.getText().toString().length() - 1));
                return;
            }

            result = nbr1 / nbr2;

        } else if(operation.equals("%")) {

            if(nbr1 == 0) {
                textView.setText(textView.getText().toString().substring(0, textView.getText().toString().length() - 1));
                return;
            }
            if(nbr2 == 0) nbr2 = nbr1;
            result = nbr1 % nbr2;

        }

        String resStr = String.valueOf(result);

        int resInt = (int) result;

        if(resInt == result) {
            resStr = String.valueOf(resInt);
        }

        textView.setText(resStr);
    }

    public void delete(View v) {
        if(textView.getText().toString().length() < 1) return;
        textView.setText(textView.getText().toString().substring(0, textView.getText().toString().length() - 1));
    }

    public void clear(View v) {
        textView.setText("");
    }
}
