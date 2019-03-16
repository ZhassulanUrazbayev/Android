package ru.startandroid.management.activities;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import ru.startandroid.management.Classes.Invent;
import ru.startandroid.management.DatabaseHelper2;
import ru.startandroid.management.MainActivity;
import ru.startandroid.management.R;
import ru.startandroid.management.adapters.InventoryAdapter;

public class InventoryInfo extends AppCompatActivity {
    DatabaseHelper2 sqlHelper;
    Cursor userCursor;
    int inventId;
    TextView fullname;
    TextView number;
    TextView obj_name;
    TextView date;
    TextView status;
    TextView condition;
    TextView cabinet;
    TextView among_data;
    TextView cabinet_manager;
    ImageButton delete_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        inventId = getIntent().getIntExtra("inventId",0);
        delete_button = (ImageButton)findViewById(R.id.delete_invent);
        fullname = (TextView)findViewById(R.id.info_fullname);
        condition = (TextView)findViewById(R.id.condition);
        status = (TextView)findViewById(R.id.status);
        number = (TextView)findViewById(R.id.serial_number);
        date = (TextView)findViewById(R.id.date);
        obj_name = (TextView)findViewById(R.id.object);
        cabinet = (TextView)findViewById(R.id.room);
        among_data = (TextView)findViewById(R.id.among_date);
        cabinet_manager = (TextView)findViewById(R.id.cabinet_manager);
        sqlHelper = new DatabaseHelper2(getApplicationContext());
        if (MainActivity.isAdmin == 1){
            delete_button.setVisibility(View.VISIBLE);
        }

        try {
            sqlHelper.open();
            userCursor = sqlHelper.database.rawQuery("select serial_numb,fullname,obj_name,uchet_date,firstname,lastname,cab_number,amor_gr,\n" +
                    "stat_name,cond_name from inventories\n" +
                    "inner join objects on inventories.obj_id = objects.obj_id\n" +
                    "inner join Status on inventories.stat_id = Status.stat_id \n" +
                    "inner join Condition on inventories.con_id = Condition.cond_id\n" +
                    "inner join cabinet on inventories.room_id = cabinet.cab_id\n" +
                    "inner join employees on cabinet.emp_id = employees.emp_id\n" +
                    "where inventories._id = " + inventId + ";", null);


            if (userCursor.moveToFirst()) {
               fullname.setText(userCursor.getString(userCursor.getColumnIndexOrThrow("fullname")));
               condition.setText(userCursor.getString(userCursor.getColumnIndexOrThrow("cond_name")));
               status.setText(userCursor.getString(userCursor.getColumnIndexOrThrow("stat_name")));
               obj_name.setText(userCursor.getString(userCursor.getColumnIndexOrThrow("obj_name")));
               date.setText(userCursor.getString(userCursor.getColumnIndexOrThrow("uchet_date")));
               number.setText(userCursor.getString(userCursor.getColumnIndexOrThrow("serial_numb")));
               cabinet.setText(userCursor.getString(userCursor.getColumnIndexOrThrow("cab_number")));
               among_data.setText(userCursor.getString(userCursor.getColumnIndexOrThrow("amor_gr")));
               cabinet_manager.setText(userCursor.getString(userCursor.getColumnIndexOrThrow("firstname")) + " " + userCursor.getString(userCursor.getColumnIndexOrThrow("lastname")));

            }

        }
        catch (java.sql.SQLException ex){}
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqlHelper.database.delete("inventories","_id=?",new String[]{inventId+""});
                InventoryInfo.this.onBackPressed();
            }
        });

    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
