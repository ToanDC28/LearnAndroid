package com.example.lab3;

import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class Bai2 extends AppCompatActivity {

    private ListView listView;

    private String[] fruits = {"Apple", "Banana", "Orange", "Water Melon"};
    private String[] descriptions = {"Apple Description", "Banana Description", "Orange Description", "Water Melon Description"};

    private List<Info> list;
    private int[] images = {R.drawable.apple, R.drawable.bananas, R.drawable.orange, R.drawable.watermelon};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bai_2);

        listView = (ListView) findViewById(R.id.list);
        list = new ArrayList<>();
        for (int i = 0; i < fruits.length; i++){
            list.add(new Info(images[i], fruits[i], descriptions[i]));
        }
        ProgramAdapter adapter = new ProgramAdapter(this, R.layout.listview, list);
        listView.setAdapter(adapter);

    }
}