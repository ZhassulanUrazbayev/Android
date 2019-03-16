package ru.startandroid.management;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FilterQueryProvider;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ru.startandroid.management.Classes.Invent;
import ru.startandroid.management.adapters.InventoryAdapter;

public class Inventory extends AppCompatActivity {
    RecyclerView userList;
    DatabaseHelper2 sqlHelper;
    Cursor userCursor;
    InventoryAdapter userAdapter;
    List<Invent> items = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Инвентарь");
        userList = (RecyclerView)findViewById(R.id.userList);
        sqlHelper = new DatabaseHelper2(getApplicationContext());
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onResume(){
        super.onResume();
        try {
            items = new ArrayList<>();
            sqlHelper.open();
            userCursor = sqlHelper.database.rawQuery("select * from " + DatabaseHelper2.TABLE, null);
            if (userCursor.getCount() > 0)
            {

                userCursor.moveToFirst();
                do {
                    items.add(new Invent(userCursor.getInt(userCursor.getColumnIndexOrThrow("_id")),
                            userCursor.getString(userCursor.getColumnIndexOrThrow("serial_numb")),
                            userCursor.getString(userCursor.getColumnIndexOrThrow("fullname"))));
                } while (userCursor.moveToNext());
                userCursor.close();
            }
            LinearLayoutManager llm = new LinearLayoutManager(this);
            userAdapter = new InventoryAdapter(this,items);
            userList.setLayoutManager(llm);
            userList.setAdapter(userAdapter);

        }
        catch (java.sql.SQLException ex){}
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        // Закрываем подключения
        sqlHelper.database.close();
        userCursor.close();
    }


}
