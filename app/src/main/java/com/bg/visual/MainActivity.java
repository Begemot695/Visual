package com.bg.visual;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnUserLogIn = (Button) findViewById(R.id.btnUserLogIn);

        btnUserLogIn.setOnClickListener(this);
    }

    public void userLogIn (View view){
        Intent intent = new Intent(this,Registration.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this,Login.class);
        startActivity(intent);
    }
}
