package ru.startandroid.management;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import ru.startandroid.management.export_database.ExportDatabase;

public class Reports extends AppCompatActivity {
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports);
        GridLayout mainGrid = (GridLayout) findViewById(R.id.mainGrid);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setSingleEvent(mainGrid);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        verifyStoragePermissions(this);
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
                        ExportDatabaseCSVTask mt = new ExportDatabaseCSVTask();
                        mt.execute(0);
                    }
                    else if (finalI==1) {
                        ExportDatabaseCSVTask mt = new ExportDatabaseCSVTask();
                        mt.execute(1);
                    }
                    else if (finalI==2) {
                        ExportDatabaseCSVTask mt = new ExportDatabaseCSVTask();
                        mt.execute(2);
                    }
                    else if (finalI==3) {
                        ExportDatabaseCSVTask mt = new ExportDatabaseCSVTask();
                        mt.execute(3);
                    }

                }
            });
        }

    }
    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }
    public class ExportDatabaseCSVTask extends AsyncTask<Integer, Void, Boolean> {
        private final ProgressDialog dialog = new ProgressDialog(Reports.this);
        @Override
        protected void onPreExecute() {
            this.dialog.setMessage("Exporting database...");
            this.dialog.show();
        }
        protected Boolean doInBackground(final Integer... args) {
            if (args[0] == 0) {
                ExportDatabase exportDatabase = new ExportDatabase(Reports.this);
                exportDatabase.exportDataBaseIntoCSV();
            }else if(args[0] == 1){
                ExportDatabase exportDatabase = new ExportDatabase(Reports.this);
                exportDatabase.exportByEmployee();
            }else if(args[0] == 2){
                ExportDatabase exportDatabase = new ExportDatabase(Reports.this);
                exportDatabase.exportByCategory();
            }else if(args[0] == 3){
                ExportDatabase exportDatabase = new ExportDatabase(Reports.this);
                exportDatabase.exportByCabinet();
            }
            return true;
        }
        protected void onPostExecute(final Boolean success) {
            if (this.dialog.isShowing()) {
                this.dialog.dismiss();
            }
            if (success) {
                Toast.makeText(Reports.this, "Export succeed", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(Reports.this, "Export failed", Toast.LENGTH_SHORT).show();
            }
        }}
}

