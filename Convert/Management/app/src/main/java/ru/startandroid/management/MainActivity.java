package ru.startandroid.management;

import android.Manifest;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import ru.startandroid.management.activities.News;
import ru.startandroid.management.export_database.DatabaseHelper3;

public class MainActivity extends AppCompatActivity {
    public static int isAdmin = 0;
    public static String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        GridLayout mainGrid = (GridLayout) findViewById(R.id.mainGrid);
        DatabaseHelper2 sqlHelper = new DatabaseHelper2(this);
        DatabaseHelper sqlHelper2 = new DatabaseHelper(this);
        DatabaseHelper3 databaseHelper3 = new DatabaseHelper3(this);
        databaseHelper3.create_db();
        sqlHelper.create_db();
        sqlHelper2.createDb();
        setSingleEvent(mainGrid);

    }


    private void setSingleEvent (GridLayout mainGrid) {
        for (int i=0; i<mainGrid.getChildCount();i++)
        {
            CardView cardview = (CardView) mainGrid.getChildAt(i);
            final int finalI=i;
            cardview.setOnClickListener (new View.OnClickListener()
            {
                @Override
                public void onClick(View view) {
                    if (finalI==0) {
                        Intent intent = new Intent(MainActivity.this,Inventory.class);
                        startActivity(intent);
                    }
                    else if (finalI==1) {
                        Intent intent = new Intent(MainActivity.this,Add.class);
                        startActivity(intent);
                    }
                    else if (finalI==2) {
                        Intent intent = new Intent(MainActivity.this,Search.class);
                        startActivity(intent);
                    }
                    else if (finalI==3) {
                        Intent intent = new Intent(MainActivity.this,Reports.class);
                        startActivity(intent);
                    }
                    else if (finalI==4) {
                        Intent intent = new Intent(MainActivity.this,News.class);
                        startActivity(intent);
                    }
                    else if (finalI==5) {
                        MainActivity.isAdmin  = 0;
                        MainActivity.username = "";
                        finish();
                    }
                }
            });
        }

    }
}
