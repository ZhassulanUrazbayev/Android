package com.hfad.urazbayevzhassulanfirstmidtermtask;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static java.lang.Integer.parseInt;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    ArrayList<Data> data;
    Context context;
    String str;
    public CustomAdapter(ArrayList<Data> data, Context context) {

        this.data = data;
        this.context = context;

    }
    @NonNull
    @Override
    public CustomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_custom_adapter,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.ViewHolder viewHolder, final int position) {

        str = data.get(position).hour+":"+data.get(position).minute;
        viewHolder.time.setText(str);
        viewHolder.description.setText(data.get(position).description);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                AlertDialog.Builder myAlertBuilder = new
//                        AlertDialog.Builder(context);
//
//                myAlertBuilder.setTitle("Alert");
//                myAlertBuilder.setMessage("24 Hours left");
//
//                myAlertBuilder.setPositiveButton("Yes", new
//                        DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int which) {
//                                Нажатие на ДА
//                            }
//                        });
//                myAlertBuilder.setNegativeButton("No", new
//                        DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int which) {
//                                Нажатие на Нет
//                            }
//                        });
//                myAlertBuilder.show();

                int currentTime = Calendar.getInstance().getTime().getHours() - parseInt(data.get(position).hour);

                Toast toast = Toast.makeText(context, "24 hours Left", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder myAlertBuilder = new
                        AlertDialog.Builder(context);

                myAlertBuilder.setTitle("Alert");
                myAlertBuilder.setMessage("Do you really want to delete this alarm?");

                myAlertBuilder.setPositiveButton("Yes", new
                        DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                //Нажатие на ДА
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
        TextView time;
        TextView description;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            time = itemView.findViewById(R.id.Time);
            description = itemView.findViewById(R.id.Description);
        }
    }
}