package com.example.lab5;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private int[] images = {R.drawable.android, R.drawable.android};
    private String[] names = {"List View Trong Android", "Xử Lý Sự Kiện Trong IOS"};
    private String[] dess = {"List View Trong Android", "Xử Lý Sự Kiện Trong IOS"};
    private String[] types = {"Android", "IOS"};
    private ArrayList<Information> list;
    private RecyclerView rvList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        list = new ArrayList<>();
        AddData();
        rvList = (RecyclerView) findViewById(R.id.rvInfor);
        InformationAdapter adapter = new InformationAdapter(list);
        rvList.setAdapter(adapter);
        rvList.setLayoutManager(new LinearLayoutManager(this));
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void AddData(){
        for (int i = 0; i < names.length; i++) {
            list.add(new Information(images[i], names[i], dess[i], types[i]));
        }
    }
}