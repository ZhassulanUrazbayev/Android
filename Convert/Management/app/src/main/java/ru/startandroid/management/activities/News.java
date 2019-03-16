package ru.startandroid.management.activities;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import ru.startandroid.management.Classes.Invent;
import ru.startandroid.management.Classes.New;
import ru.startandroid.management.DatabaseHelper2;
import ru.startandroid.management.R;
import ru.startandroid.management.adapters.InventoryAdapter;
import ru.startandroid.management.adapters.NewsAdapter;
import ru.startandroid.management.export_database.DatabaseHelper3;

public class News extends AppCompatActivity {
    DatabaseHelper3 sqlHelper;
    RecyclerView userList;
    Cursor userCursor;
    List<New> items = new ArrayList<>();
    NewsAdapter userAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        sqlHelper = new DatabaseHelper3(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setTitle("Объявление");
        userList = (RecyclerView)findViewById(R.id.newsList);
        try {
            sqlHelper.open();
            userCursor = sqlHelper.database.rawQuery("select * from news", null);
            if (userCursor.getCount() > 0)
            {

                userCursor.moveToFirst();
                do {
                    items.add(new New(userCursor.getString(userCursor.getColumnIndexOrThrow("title")),
                            userCursor.getString(userCursor.getColumnIndexOrThrow("body")),
                            userCursor.getString(userCursor.getColumnIndexOrThrow("Datecreated"))));
                } while (userCursor.moveToNext());
                userCursor.close();
            }
            LinearLayoutManager llm = new LinearLayoutManager(this);
            userAdapter = new NewsAdapter(this,items);
            userList.setLayoutManager(llm);
            userList.setAdapter(userAdapter);

        }
        catch (java.sql.SQLException ex){}
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
}
