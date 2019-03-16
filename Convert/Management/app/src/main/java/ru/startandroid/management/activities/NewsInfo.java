package ru.startandroid.management.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import ru.startandroid.management.R;

public class NewsInfo extends AppCompatActivity {
    TextView title;
    TextView description;
    TextView date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        title = (TextView)findViewById(R.id.newsinfo_title);
        description = (TextView)findViewById(R.id.newsinfo_description);
        date = (TextView) findViewById(R.id.newsinfo_date);
        title.setText(getIntent().getStringExtra("title"));
        description.setText(getIntent().getStringExtra("des"));
        date.setText(getIntent().getStringExtra("date"));
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
