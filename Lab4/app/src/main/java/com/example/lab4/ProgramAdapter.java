package com.example.lab4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ProgramAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Information> informations;

    public ProgramAdapter(Context context, int layout, List<Information> informations) {
        this.context = context;
        this.layout = layout;
        this.informations = informations;
    }


    @Override
    public int getCount() {
        return informations.size();
    }

    @Override
    public Object getItem(int position) {
        return informations.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(layout, null);

        TextView tvFullName = convertView.findViewById(R.id.tvName);
        TextView tvDes = convertView.findViewById(R.id.tvDes);
        ImageView imageResource = convertView.findViewById(R.id.imageResource);

        Information legend = informations.get(position);
        tvFullName.setText(legend.getName());
        tvDes.setText(legend.getDescription());
        imageResource.setImageResource(legend.getImages());
        return convertView;
    }
}
