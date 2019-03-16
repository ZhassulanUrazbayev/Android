package ru.startandroid.management.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.QuickContactBadge;
import android.widget.TextView;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import ru.startandroid.management.Add;
import ru.startandroid.management.Authorization;
import ru.startandroid.management.Classes.Invent;
import ru.startandroid.management.MainActivity;
import ru.startandroid.management.R;
import ru.startandroid.management.Update;
import ru.startandroid.management.activities.InventoryInfo;

public class InventoryAdapter extends RecyclerView.Adapter<InventoryHolder>{
    Context context;
    List<Invent> inventories;
    public InventoryAdapter(Context context,List<Invent> list){
        this.inventories = list;
        this.context = context;
    }

    @Override
    public InventoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.inventary_item, parent, false);
        InventoryHolder pvh = new InventoryHolder(context,v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(InventoryHolder holder, int position) {
        holder.setHolder(inventories.get(position).getFullname(),inventories.get(position).getSerial_number(),inventories.get(position).getId());

    }

    @Override
    public int getItemCount() {
        return inventories.size();
    }
}
class InventoryHolder extends RecyclerView.ViewHolder{
    TextView title;
    TextView number;
    ImageButton edit;
    View view;
    Context context;
    public InventoryHolder(Context context, View itemView) {
        super(itemView);
        this.view = itemView;
        this.context = context;
        title = (TextView)itemView.findViewById(R.id.inventory_title);
        number = (TextView)itemView.findViewById(R.id.inventory_number);
        edit = (ImageButton) itemView.findViewById(R.id.inventory_edit);


    }
    public void setHolder(String t,String s,final int id){
        title.setText(t);
        number.setText(s);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, InventoryInfo.class);
                intent.putExtra("inventId",id);
                context.startActivity(intent);
            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Update.class);
                intent.putExtra("inventId",id);
                context.startActivity(intent);
            }
        });

    }
}
