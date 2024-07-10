package com.example.fpt.edu.vn.se160630;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class InformationAdapter extends ArrayAdapter<Information> {
    private Context context;
    private List<Information> tasks;

    public InformationAdapter(Context context, int resource, List<Information> tasks) {
        super(context, resource, tasks);
        this.context = context;
        this.tasks = tasks;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_list, parent, false);
        }

        Information task = tasks.get(position);

        TextView titleTextView = convertView.findViewById(R.id.edtShowTitle);
        TextView contentTextView = convertView.findViewById(R.id.edtShowContent);
        TextView dateTextView = convertView.findViewById(R.id.edtShowDate);

        titleTextView.setText(task.getTitle());
        contentTextView.setText(task.getContent());
        dateTextView.setText(task.getDate());

        return convertView;
    }
}