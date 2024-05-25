package com.example.lab2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.concurrent.ThreadLocalRandom;

public class Bai1 extends AppCompatActivity {

    private Button btn;
    private TextView result;
    private EditText min;
    private EditText max;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.bai1);
        btn = (Button) findViewById(R.id.btnGenerate);
        if(btn != null){
            btn.setOnClickListener((View.OnClickListener) (new View.OnClickListener(){
                        @Override
                        public void onClick(View v) {
                            min = (EditText) findViewById(R.id.txtMin);
                            max = (EditText) findViewById(R.id.txtMax);
                            int randomNum = ThreadLocalRandom.current().nextInt(Integer.parseInt(min.getText().toString()), Integer.parseInt(max.getText().toString()) + 1);

                            result = (TextView) findViewById(R.id.txtResult);
                            result.setText(String.valueOf(randomNum));
                        }
                    })

            );
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }

}