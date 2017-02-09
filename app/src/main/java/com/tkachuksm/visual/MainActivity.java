package com.tkachuksm.visual;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnLogin = (Button) findViewById(R.id.btnUserLogIn);
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
       Intent intent = new Intent(this,LogIn.class);
        startActivity(intent);
    }

    public void userLogIn(View v){
        Intent intent = new Intent(this,Registration.class);
        startActivity(intent);
    }
}
