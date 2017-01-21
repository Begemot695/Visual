package com.bg.visual;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Welcom extends AppCompatActivity {

     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcom);

        Intent intent = getIntent();
        String nameText = intent.getStringExtra("Name");

        TextView message = (TextView) findViewById(R.id.message);
        message.setText("Регистрация прошла успешно !! " + nameText );

    }



}
