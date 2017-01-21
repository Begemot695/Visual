package com.bg.visual;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Registration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
    }
    public void userReg(View view) {
        EditText userName = (EditText) findViewById(R.id.userName);
        EditText userMail = (EditText) findViewById(R.id.userEmail);
        EditText userPassword = (EditText) findViewById(R.id.userPassword);
        EditText confirmPassword = (EditText) findViewById(R.id.confirmPassword);

        String NameText = userName.getText().toString();
        String MailText = userMail.getText().toString();
        String PasswordText = userPassword.getText().toString();
        String ConfirmPasswordText = confirmPassword.getText().toString();

        if (!(PasswordText.equals(ConfirmPasswordText))) {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Пароли не совпадают", Toast.LENGTH_SHORT);
            toast.show();

        } else {
            Intent intent = new Intent(this,Welcom.class);
            intent.putExtra("Name",NameText);
            intent.putExtra("Mail",MailText);
            startActivity(intent);

        }
    }
}
