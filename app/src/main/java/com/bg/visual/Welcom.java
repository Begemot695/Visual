package com.bg.visual;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.widget.TextView;


public class Welcom extends AppCompatActivity {

    Fragment1 frag;
    FragmentTransaction fTranc;

     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcom);

        frag = new Fragment1();

        Intent intent = getIntent();
        String nameText = intent.getStringExtra("Name");
        String active = intent.getStringExtra("active");

        TextView message = (TextView) findViewById(R.id.message);
        message.setText("Регистрация прошла успешно !! " + nameText );

         if (active.equals("Login")){
         fTranc = getSupportFragmentManager().beginTransaction();
         fTranc.add(R.id.frmCont,frag);
         fTranc.commit();
         }

    }



}
