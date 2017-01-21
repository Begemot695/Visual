package com.bg.visual;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void LogIn(View view){
        EditText userName = (EditText) findViewById(R.id.userName);
        EditText userPassword = (EditText) findViewById(R.id.userPassword);
        String nameText = userName.getText().toString();
        String passwordtext = userPassword.getText().toString();

        if (nameText.equals("")||passwordtext.equals("")) {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Не заполнены поля", Toast.LENGTH_SHORT);
            toast.show();
        }else if (nameText.equals("Ivan") && passwordtext.equals("123")){
            Intent intent = new Intent(this, Welcom.class);
            intent.putExtra("Name", nameText);
            intent.putExtra("Password", passwordtext);
            startActivity(intent);
        }
    }
}
