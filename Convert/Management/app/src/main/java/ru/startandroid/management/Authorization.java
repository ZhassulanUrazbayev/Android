package ru.startandroid.management;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.webkit.DownloadListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

public class Authorization extends AppCompatActivity {

    Button btnLogin;
    EditText edtUsername;
    EditText edtPassword;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorization);
        btnLogin = (Button) findViewById(R.id.btnlog);
        edtUsername = (EditText) findViewById(R.id.username);
        edtPassword = (EditText) findViewById(R.id.password);
        databaseHelper = new DatabaseHelper(Authorization.this);
        databaseHelper.createDb();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isExist = databaseHelper.checkUserExist(edtUsername.getText().toString(), edtPassword.getText().toString());

                if(isExist){
                    Intent intent = new Intent(Authorization.this, MainActivity.class);
                    if (edtUsername.getText().toString().equals("admin")){
                        MainActivity.isAdmin = 1;
                    }
                    intent.putExtra("username", edtUsername.getText().toString());
                    startActivity(intent);
                } else {
                    edtUsername.setText(null);
                    edtPassword.setText(null);
                    Toast.makeText(Authorization.this, "Login failed. Invalid username or password.", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        edtPassword.setText("");
        edtUsername.setText("");
    }
}