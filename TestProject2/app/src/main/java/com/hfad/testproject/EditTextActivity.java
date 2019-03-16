package com.hfad.testproject;

import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;

import static android.R.id.edit;

public class EditTextActivity extends AppCompatActivity {
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text);

        editText = (EditText)findViewById(R.id.editText);

    }

    @Override
    protected void onPause() {
        super.onPause();


    }

    public void onClick(View view){
        Intent intent = new Intent();
        intent.putExtra("text", editText.getText().toString());
        setResult(RESULT_OK, intent);
        finish();
    }

}





