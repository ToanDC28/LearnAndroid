package com.example.lab4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Button btnOrderFood;
    private Button btnOrderDrink;
    private Button btnExit;

    private TextView tvResult;
    private String order = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        btnOrderFood = (Button) findViewById(R.id.btnOrderFood);
        btnOrderDrink = (Button) findViewById(R.id.btnOrderDrink);
        btnExit = (Button) findViewById(R.id.btnExit);
        tvResult = (TextView) findViewById(R.id.tvResult);
        Intent intent = getIntent();
        if(intent != null && intent.hasExtra("order")){

            if(order.isEmpty()){
                order = intent.getStringExtra("order");
            }
            tvResult.setText(order);
        }
        btnOrderFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FoodActivity();
            }
        });
        btnOrderDrink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DrinkActivity();
            }
        });
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Exit();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void FoodActivity(){
        Intent intent = new Intent(this, OrderActivity.class);
        intent.putExtra("choice", "food");
        intent.putExtra("old-order", order);
        startActivity(intent);
    }
    private void DrinkActivity(){
        Intent intent = new Intent(this, OrderActivity.class);
        intent.putExtra("choice", "drink");
        intent.putExtra("old-order", order);
        startActivity(intent);
    }
    private void Exit(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}