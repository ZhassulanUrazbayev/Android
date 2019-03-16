package com.hfad.contacts3;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder>   {

    String name;
    String number;
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
    public void onBindViewHolder(ListAdapter.ViewHolder holder,final int position) {

        holder.name.setText(data.get(position).name);
        holder.age.setText(data.get(position).number);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, ProfilInfoActivity.class);

                intent.putExtra("name",data.get(position).name);
                intent.putExtra("number",data.get(position).number);
                context.startActivity(intent);

            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder myAlertBuilder = new
                        AlertDialog.Builder(context);

                myAlertBuilder.setTitle("Alert");
                myAlertBuilder.setMessage("Do you really want to delete this contact?");

                myAlertBuilder.setPositiveButton("Yes", new
                        DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                //Нажатие на ДА
                                data.remove(position);
                                saveArrayList(data,"data");
                                notifyDataSetChanged();
                            }
                        });
                myAlertBuilder.setNegativeButton("No", new
                        DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                //Нажатие на Нет
                            }
                        });
                myAlertBuilder.show();


                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView age;
        ImageView ImageView;

        public ViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.TextView);
            age = itemView.findViewById(R.id.age);
            ImageView = itemView.findViewById(R.id.ImageView);

        }
    }

    public void saveArrayList(ArrayList<Data> list, String key) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString(key, json);
        editor.apply();
    }

}
