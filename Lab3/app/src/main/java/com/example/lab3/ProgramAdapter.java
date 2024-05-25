package com.example.lab3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ProgramAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<Info> infos;

    public ProgramAdapter(Context context, int layout, List<Info> infos) {
        this.context = context;
        this.layout = layout;
        this.infos = infos;
    }

    @Override
    public int getCount() {
        return infos.size();
    }

    @Override
    public Object getItem(int position) {
        return infos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(layout, null);

        TextView tvFullName = view.findViewById(R.id.tvName);
        TextView tvDes = view.findViewById(R.id.tvDes);
        ImageView imageResource = view.findViewById(R.id.imageResource);

        Info legend = infos.get(position);
        tvFullName.setText(legend.getName());
        tvDes.setText(legend.getDescription());
        imageResource.setImageResource(legend.getImages());
        return view;
    }
}
