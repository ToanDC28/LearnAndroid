package com.example.lab2;

import android.os.Bundle;
import android.text.TextUtils;
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

public class Bai2 extends AppCompatActivity {

    private Button cong;
    private Button tru;
    private Button nhan;
    private Button chia;
    private TextView result;
    private EditText first;
    private EditText second;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bai2);
        cong = (Button) findViewById(R.id.btnCong);
        tru = (Button) findViewById(R.id.btnTru);
        nhan = (Button) findViewById(R.id.btnNhan);
        chia = (Button) findViewById(R.id.btnChia);
        cong.setOnClickListener((View.OnClickListener) (new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        first = (EditText) findViewById(R.id.firstnum);
                        second = (EditText) findViewById(R.id.secondnum);
                        if(CheckEmpty()){
                            result = (TextView) findViewById(R.id.txtResult);
                            result.setText(Cong(Integer.parseInt(first.getText().toString()), Integer.parseInt(second.getText().toString())));
                        }
                    }
                })

        );
        tru.setOnClickListener((View.OnClickListener) (new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        first = (EditText) findViewById(R.id.firstnum);
                        second = (EditText) findViewById(R.id.secondnum);
                        if(CheckEmpty()){
                            result = (TextView) findViewById(R.id.txtResult);
                            result.setText(Tru(Integer.parseInt(first.getText().toString()), Integer.parseInt(second.getText().toString())));
                        }
                    }
                })

        );
        nhan.setOnClickListener((View.OnClickListener) (new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        first = (EditText) findViewById(R.id.firstnum);
                        second = (EditText) findViewById(R.id.secondnum);
                        if(CheckEmpty()){
                            result = (TextView) findViewById(R.id.txtResult);
                            result.setText(Nhan(Integer.parseInt(first.getText().toString()), Integer.parseInt(second.getText().toString())));
                        }
                    }
                })

        );
        chia.setOnClickListener((View.OnClickListener) (new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        first = (EditText) findViewById(R.id.firstnum);
                        second = (EditText) findViewById(R.id.secondnum);
                        if(CheckEmpty()){
                            result = (TextView) findViewById(R.id.txtResult);
                            result.setText(Chia(Integer.parseInt(first.getText().toString()), Integer.parseInt(second.getText().toString())));
                        }
                    }
                })

        );

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    private boolean CheckEmpty(){
        if(TextUtils.isEmpty(first.getText().toString())){
            first.setError("Required");
            return false;
        }
        else if(TextUtils.isEmpty(second.getText().toString())){
            second.setError("Required");
            return false;
        }
        return true;
    }
    private String Cong(int n, int m){
        return String.valueOf((n + m));
    }
    private String Tru(int n, int m){
        return String.valueOf((n - m));
    }
    private String Nhan(int n, int m){
        return String.valueOf((n * m));
    }
    private String Chia(int n, int m){
        return String.valueOf((n / m));
    }
}