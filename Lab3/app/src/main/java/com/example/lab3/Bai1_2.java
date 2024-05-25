package com.example.lab3;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Bai1_2 extends AppCompatActivity {

    private int item_action = -1;
    private ListView listView;
    private EditText editText;
    private Button btnAdd;
    private Button btnDelete;
    private Button btnUpdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.bai1_2);
        listView = (ListView) findViewById(R.id.items);
        editText = (EditText) findViewById(R.id.item);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnUpdate= (Button) findViewById(R.id.btnUpdate);
        String[] items = new String[] {"ANDROID", "IOS", "PHP", "UNITY"};
        List<String> list = new ArrayList<String>(Arrays.asList(items));
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selected = (String) parent.getItemAtPosition(position);
                editText.setText(selected);
                item_action = position;
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(editText.getText().toString())){
                    item_action = -1;
                    arrayAdapter.add(editText.getText().toString());
                    listView.setAdapter(arrayAdapter);
                    editText.setText("");
                }

            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(editText.getText().toString())){
                    if(item_action != -1){
                        String selected_item = arrayAdapter.getItem(item_action);
                        arrayAdapter.remove(selected_item);
                        listView.setAdapter(arrayAdapter);
                        editText.setText("");
                    }
                }

            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(item_action != -1 && !TextUtils.isEmpty(editText.getText().toString())){
                    String selected_item = arrayAdapter.getItem(item_action);
                    arrayAdapter.remove(selected_item);
                    arrayAdapter.insert(editText.getText().toString(), item_action);
                    listView.setAdapter(arrayAdapter);
                    item_action = -1;
                    editText.setText("");
                }

            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}