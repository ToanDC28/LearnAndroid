package com.example.lab5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class InformationAdapter extends RecyclerView.Adapter<InformationAdapter.ViewHolder> {

    private ArrayList<Information> informationList;

    public InformationAdapter(ArrayList<Information> informationList) {
        this.informationList = informationList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.row_inforlist,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Information information = informationList.get(position);
        holder.tvName.setText(information.getName());
        holder.imageSource.setImageResource(information.getImage());
        holder.tvDes.setText(information.getDescription());
        holder.tvType.setText(information.getType());
    }

    @Override
    public int getItemCount() {
        return informationList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
         ImageView imageSource;
         TextView tvName;
         TextView tvDes;
         TextView tvType;

    public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageSource = itemView.findViewById(R.id.imageResource);
            tvName = itemView.findViewById(R.id.tvName);
            tvDes = itemView.findViewById(R.id.tvDes);
            tvType = itemView.findViewById(R.id.tvType);
        }
    }
}
