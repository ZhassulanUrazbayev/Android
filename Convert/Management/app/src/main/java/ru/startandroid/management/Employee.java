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
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ru.startandroid.management.Classes.Invent;
import ru.startandroid.management.adapters.InventoryAdapter;

public class Employee extends AppCompatActivity {
    RecyclerView userList;
    DatabaseHelper2 sqlHelper;
    Cursor userCursor;
    InventoryAdapter userAdapter;
    List<Invent> items = new ArrayList<>();
    AutoCompleteTextView search_object;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        userList = (RecyclerView) findViewById(R.id.userList);
        search_object = (AutoCompleteTextView)findViewById(R.id.search_emp);
        sqlHelper = new DatabaseHelper2(getApplicationContext());
        ArrayAdapter<String> cabinetAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, addItems(" firstname,lastname ","employees"));
        configureAutoComplete(cabinetAdapter,search_object);
        try {
            sqlHelper.open();
            userCursor = sqlHelper.database.rawQuery("select _id,cab_number,fullname,firstname,lastname from inventories " +
                    "inner join cabinet on inventories.room_id = cabinet.cab_id " +
                    "inner join employees on cabinet.emp_id = employees.emp_id;", null);
            if (userCursor.getCount() > 0)
            {

                userCursor.moveToFirst();
                do {
                    items.add(new Invent(userCursor.getInt(userCursor.getColumnIndexOrThrow("_id")),
                            "Сотрудник: " + userCursor.getString(userCursor.getColumnIndexOrThrow("firstname")) +
                            " " +  userCursor.getString(userCursor.getColumnIndexOrThrow("lastname"))+ "\nКабинет: " +
                                    userCursor.getString(userCursor.getColumnIndexOrThrow("cab_number")),
                            userCursor.getString(userCursor.getColumnIndexOrThrow("fullname"))));
                } while (userCursor.moveToNext());
                userCursor.close();
            }
            LinearLayoutManager llm = new LinearLayoutManager(this);
            userAdapter = new InventoryAdapter(this,items);
            userList.setLayoutManager(llm);

            search_object.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    searchFunc(search_object.getText().toString());
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
            int i = s.getSerial_number().toLowerCase().indexOf(text.toLowerCase());
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
    public void configureAutoComplete(ArrayAdapter<String> adapter, final AutoCompleteTextView textView){
        textView.setAdapter(adapter);
        textView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b) {
                    String str = textView.getText().toString();
                    ListAdapter listAdapter = textView.getAdapter();
                    for(int i = 0; i < listAdapter.getCount(); i++) {
                        String temp = listAdapter.getItem(i).toString();
                        if(str.compareTo(temp) == 0) {
                            return;
                        }
                    }
                    textView.setText("");

                }
            }
        });
    }
    public ArrayList<String> addItems(String name, String table){

        ArrayList<String> object_items = new ArrayList<>();
        try {
            sqlHelper.open();
            userCursor = sqlHelper.database.rawQuery("select " + name + " from  "  + table +  ";", null);
            if (userCursor.getCount() > 0)
            {
                userCursor.moveToFirst();
                do {
                    object_items.add( userCursor.getString(userCursor.getColumnIndexOrThrow("firstname")) + " " +
                            userCursor.getString(userCursor.getColumnIndexOrThrow("lastname")));
                } while (userCursor.moveToNext());
                userCursor.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  object_items;

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