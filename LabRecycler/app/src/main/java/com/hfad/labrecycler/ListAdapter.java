package com.hfad.labrecycler;

import android.content.Context;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder>  {

    Uri uri;
    String name;
    Long lon;
    Context context;
    ArrayList<Data> data;

    public ListAdapter(ArrayList<Data> data, Context context) {

        this.data = data;
        this.context = context;

    }

    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.activity_list_adapter,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListAdapter.ViewHolder holder, int position) {

        holder.TextViewName.setText(data.get(position).name);
        holder.TextViewAge.setText(data.get(position).lon.toString());
        holder.ImageView.setImageURI(data.get(position).uri);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView TextViewName;
        TextView TextViewAge;
        ImageView ImageView;

        public ViewHolder(View itemView) {
            super(itemView);

            TextViewName = itemView.findViewById(R.id.TextViewName);
            TextViewAge = itemView.findViewById(R.id.TextViewAge);
            ImageView = itemView.findViewById(R.id.ImageView);

        }
    }
}
