package com.example.lab4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class OrderActivity extends AppCompatActivity {

    private String order = "";
    private String orders = "";
    private ListView listView;
    private Button btnOrder;

    private String[] fruits = {"Apple", "Banana", "Orange", "Water Melon"};
    private String[] descriptions = {"Apple Description", "Banana Description", "Orange Description", "Water Melon Description"};
    private List<Information> list;
    private String[] foods = {"Phở Bò Hà Nội", "Bún Bò Huế", "Mì Quảng", "Hủ Tiếu Sài Gòn"};
    private String[] desFood = {"Pho Bo Ha Noi", "Bun Bo Hue", "Mi Quang", "Hu Tieu Sai Gon"};
    private int[] imageFoods = {R.drawable.android, R.drawable.android, R.drawable.android, R.drawable.android};
    private int[] images = {R.drawable.apple, R.drawable.bananas, R.drawable.orange, R.drawable.watermelon};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_order);
        listView = (ListView) findViewById(R.id.list);
        btnOrder = (Button) findViewById(R.id.btnOrder);
        Intent intent = getIntent();
        orders = intent.getStringExtra("old-order");
        String choice = intent.getStringExtra("choice");
        if(choice != null){
            if(choice.equals("food")){
                AddFood();
            } else if (choice.equals("drink")) {
                AddFruit();
            }
            ProgramAdapter adapter = new ProgramAdapter(this, R.layout.listview, list);
            listView.setAdapter(adapter);
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Information i = (Information) listView.getItemAtPosition(position);
                if (i != null){
                    order = i.getName();
                }
            }
        });
        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Order();
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    private void AddFood(){
        list = new ArrayList<>();
        for (int i = 0; i < foods.length; i++){
            list.add(new Information(imageFoods[i], foods[i], desFood[i]));
        }
    }
    private void Order(){
        Intent i = new Intent(this, MainActivity.class);
        if(orders.equals("")){
            i.putExtra("order", order);
        }
        else {
            orders += " - " + order;
            i.putExtra("order", orders);
        }
        startActivity(i);
        finish();
    }

    private void AddFruit(){
        list = new ArrayList<>();
        for (int i = 0; i < fruits.length; i++){
            list.add(new Information(images[i], fruits[i], descriptions[i]));
        }
    }
}