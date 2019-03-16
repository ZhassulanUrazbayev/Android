package ru.startandroid.management;

import android.database.Cursor;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.FilterQueryProvider;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;
import java.util.List;

import ru.startandroid.management.Classes.Invent;
import ru.startandroid.management.adapters.InventoryAdapter;

public class Number extends AppCompatActivity {
    RecyclerView userList;
    DatabaseHelper2 sqlHelper;
    EditText userFilter;
    Cursor userCursor;
    InventoryAdapter userAdapter;
    List<Invent> items = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        userList = (RecyclerView) findViewById(R.id.userList);
        userFilter =(EditText) findViewById(R.id.userFilter);
        sqlHelper = new DatabaseHelper2(getApplicationContext());
        try {
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

            userFilter.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    searchFunc(userFilter.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });

            userList.setAdapter(userAdapter);
        }
        catch (java.sql.SQLException ex){}
    }
    private void searchFunc(String text){
        ArrayList<Invent> asd = new ArrayList<>();
        for (Invent s : items) {
            int i = s.getSerial_number().toLowerCase().indexOf(text);
            if (i >= 0) {
                asd.add(s);
            }
        }
        userAdapter = new InventoryAdapter(this,asd);
        userList.setAdapter(userAdapter);
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }



    @Override
    public void onResume(){
        super.onResume();

    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        sqlHelper.database.close();
        userCursor.close();
    }


}
