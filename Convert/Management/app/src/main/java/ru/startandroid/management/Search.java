package ru.startandroid.management;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class Search extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        GridLayout mainGrid = (GridLayout) findViewById(R.id.mainGrid);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setSingleEvent(mainGrid);
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
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
                        Intent intent = new Intent(Search.this,Number.class);
                        startActivity(intent);
                    }
                    else if (finalI==1) {
                        Intent intent = new Intent(Search.this,Room.class);
                        startActivity(intent);
                    }
                    else if (finalI==2) {
                        Intent intent = new Intent(Search.this,Category.class);
                        startActivity(intent);
                    }
                    else if (finalI==3) {
                        Intent intent = new Intent(Search.this,Employee.class);
                        startActivity(intent);
                    }

                }
            });
        }

    }
}