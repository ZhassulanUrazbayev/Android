package com.hfad.contacts;

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

public class ListAdapterActivity extends RecyclerView.Adapter<ListAdapterActivity.ViewHolder>  {

    Uri uri;
    String name;
    String number;
    Context context;
    ArrayList<ListActivity.Data> data;

    public ListAdapterActivity(ArrayList<ListActivity.Data> data, Context context) {

        this.data = data;
        this.context = context;

    }

    @Override
    public ListAdapterActivity.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.activity_list_adapter,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListAdapterActivity.ViewHolder holder, int position) {

        holder.TextView.setText(data.get(position).name);
        holder.ImageView.setImageURI(data.get(position).uri);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView TextView;
        ImageView ImageView;

        public ViewHolder(View itemView) {
            super(itemView);

            TextView= itemView.findViewById(R.id.TextView);
            ImageView = itemView.findViewById(R.id.ImageView);

        }
    }
}
