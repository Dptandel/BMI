package com.app.bmi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView txtResult;
    EditText edtWeight,edtHeightFt,edtHeightIn;
    Button btnCalculate;
    LinearLayout llmain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Window window = MainActivity.this.getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(MainActivity.this, R.color.black));
        }

        edtWeight = findViewById(R.id.edtWeight);
        edtHeightFt = findViewById(R.id.edtHeightFt);
        edtHeightIn = findViewById(R.id.edtHeightIn);
        btnCalculate = findViewById(R.id.btnCalculate);
        txtResult = findViewById(R.id.txtResult);
        llmain = findViewById(R.id.llmain);

        txtResult.setVisibility(View.GONE);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int wt = Integer.parseInt(edtWeight.getText().toString());
                int ft = Integer.parseInt(edtHeightFt.getText().toString());
                int in = Integer.parseInt(edtHeightIn.getText().toString());

                int totalIn = ft * 12 + in;
                double totalCm = totalIn * 2.53;
                double totalM = totalCm / 100;

                double bmi = wt / (totalM * totalM);

                if(bmi > 25) {
                    txtResult.setText("You're Overweight...!");
                    llmain.setBackgroundColor(getResources().getColor(R.color.colorOw));
                    txtResult.setVisibility(View.VISIBLE);
                } else if(bmi < 18.5) {
                    txtResult.setText("You're Underweight...!");
                    llmain.setBackgroundColor(getResources().getColor(R.color.colorUw));
                    txtResult.setVisibility(View.VISIBLE);
                } else {
                    txtResult.setText("You're Healthy...!");
                    llmain.setBackgroundColor(getResources().getColor(R.color.colorH));
                    txtResult.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        finish();
    }
}