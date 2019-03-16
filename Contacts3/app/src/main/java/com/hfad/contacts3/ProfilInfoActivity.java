package com.hfad.contacts3;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfilInfoActivity extends AppCompatActivity {

    TextView name;
    TextView number;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_info);

        name = findViewById(R.id.name);
        number = findViewById(R.id.number);
        image = findViewById(R.id.image);

        number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent callintent = new Intent(Intent.ACTION_DIAL,Uri.parse(("tel:"+number.getText().toString())));
                startActivity(callintent);

            }
        });

        if(getIntent().getExtras().getString("name")!=null){
            name.setText(getIntent().getExtras().getString("name"));
            number.setText(getIntent().getExtras().getString("number"));

        }
    }

}
