package com.example.pedemo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
import java.util.List;

public class ToDoList extends AppCompatActivity {

    private EditText editTitle, editContent, editDate, editType;
    private Button btnAdd, btnUpdate, btnDelete;
    private ListView lvList;
    private List<Information> myList;
    private TaskAdapter myAdapter;
    private SQLiteDatabase mydb;
    private int id = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_to_do_list);
        editContent = (EditText) findViewById(R.id.edtContent);
        editDate = (EditText) findViewById(R.id.edtDate);
        editTitle = (EditText) findViewById(R.id.edtTitle);
        editType = (EditText) findViewById(R.id.edtType);
        lvList = (ListView) findViewById(R.id.lvList);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        lvList.setOnItemClickListener((parent, view, position, id) -> showRecord(position));
        myList = new ArrayList<>();
        myAdapter = new TaskAdapter(this, R.layout.task_item, myList);
        lvList.setAdapter(myAdapter);
        CreateData();
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addRecord();
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateRecord();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteRecord();
            }
        });
        insertSampleData();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    private void insertSampleData() {
        try {
            String sql = "INSERT INTO tblList (title, content, date, type) VALUES (?, ?, ?, ?)";
            mydb.execSQL(sql, new Object[]{"Grocery Shopping", "Buy fruits and vegetables", "2024-07-10", "Personal"});
            mydb.execSQL(sql, new Object[]{"Meeting with Bob", "Discuss project details", "2024-07-11", "Work"});
            mydb.execSQL(sql, new Object[]{"Gym", "Leg day workout", "2024-07-12", "Personal"});
            mydb.execSQL(sql, new Object[]{"Doctor Appointment", "Annual check-up", "2024-07-13", "Health"});
            mydb.execSQL(sql, new Object[]{"Client Presentation", "Present the new design", "2024-07-14", "Work"});

            refreshListView();
        } catch (Exception ex) {
            Log.e("Error", "Error inserting sample data", ex);
        }
    }

    private void addRecord() {
        String title = editTitle.getText().toString();
        String content = editContent.getText().toString();
        String date = editDate.getText().toString();
        String type = editType.getText().toString();
        clearEditTexts();
        try {
            String sql = "INSERT INTO tblList (title, content, date, type) VALUES (?, ?, ?, ?)";
            mydb.execSQL(sql, new Object[]{title, content, date, type});
            refreshListView();
        } catch (Exception ex) {
            Log.e("Error", "Error inserting record", ex);
        }
    }

    private void updateRecord() {
        if (id == -1) {
            Log.e("Error", "No record selected for update");
            return;
        }

        String title = editTitle.getText().toString();
        String content = editContent.getText().toString();
        String date = editDate.getText().toString();
        String type = editType.getText().toString();

        try {
            String sql = "UPDATE tblList SET title = ?, content = ?, date = ?, type = ? WHERE id = ?";
            mydb.execSQL(sql, new Object[]{title, content, date, type, id});
            refreshListView();
            id = -1;
            clearEditTexts();
        } catch (Exception ex) {
            Log.e("Error", "Error updating record", ex);
        }
    }
    private void deleteRecord() {
        if (id == -1) {
            Log.e("Error", "No record selected for delete");
            return;
        }

        try {
            String sql = "DELETE FROM tblList WHERE id = ?";
            mydb.execSQL(sql, new Object[]{id});
            refreshListView();
            id = -1;
            clearEditTexts();
        } catch (Exception ex) {
            Log.e("Error", "Error deleting record", ex);
        }
    }

    private void refreshListView() {
        myList.clear();
        try {
            String sql = "SELECT id, title, content, date, type FROM tblList";
            Cursor cursor = mydb.rawQuery(sql, null);
            if (cursor.moveToFirst()) {
                do {
                    int idIndex = cursor.getColumnIndex("id");
                    int titleIndex = cursor.getColumnIndex("title");
                    int contentIndex = cursor.getColumnIndex("content");
                    int dateIndex = cursor.getColumnIndex("date");
                    int typeIndex = cursor.getColumnIndex("type");

                    if (idIndex != -1 && titleIndex != -1 && contentIndex != -1 && dateIndex != -1 && typeIndex != -1) {
                        Information info = new Information(
                                cursor.getString(titleIndex),
                                cursor.getString(contentIndex),
                                cursor.getString(dateIndex)
                        );
                        myList.add(info);
                    }
                } while (cursor.moveToNext());
            }
            cursor.close();
            myAdapter.notifyDataSetChanged();
        } catch (Exception ex) {
            Log.e("Error", "Error refreshing list view", ex);
        }
    }

    private void showRecord(int position) {
        Information info = myList.get(position);
        try {
            String sql = "SELECT * FROM tblList WHERE title = ?";
            Cursor cursor = mydb.rawQuery(sql, new String[]{info.getTitle()});
            if (cursor.moveToFirst()) {
                int idIndex = cursor.getColumnIndex("id");
                int titleIndex = cursor.getColumnIndex("title");
                int contentIndex = cursor.getColumnIndex("content");
                int dateIndex = cursor.getColumnIndex("date");
                int typeIndex = cursor.getColumnIndex("type");

                if (idIndex != -1 && titleIndex != -1 && contentIndex != -1 && dateIndex != -1 && typeIndex != -1) {
                    id = cursor.getInt(idIndex);
                    editTitle.setText(cursor.getString(titleIndex));
                    editContent.setText(cursor.getString(contentIndex));
                    editDate.setText(cursor.getString(dateIndex));
                    editType.setText(cursor.getString(typeIndex));
                } else {
                    Log.e("Error", "Column not found");
                }
            }
            cursor.close();
        } catch (Exception ex) {
            Log.e("Error", "Error showing record", ex);
        }
    }
    private void clearEditTexts() {
        editTitle.setText("");
        editContent.setText("");
        editDate.setText("");
        editType.setText("");
    }
    private void CreateData(){
        mydb = openOrCreateDatabase("sqlDataDemo.db", MODE_PRIVATE, null);
        try {
            String sql = "CREATE TABLE IF NOT EXISTS tblList (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "title TEXT, " +
                    "content TEXT, " +
                    "date TEXT, " +
                    "type TEXT)";
            mydb.execSQL(sql);
        } catch (Exception ex) {
            Log.e("Error", "Error creating table", ex);
        }
    }
}