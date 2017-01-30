package com.bg.visual;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;

public class Login extends AppCompatActivity {

    SharedPreferences ShPerf;


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
        //чтение сохраненных данных
        ShPerf = getSharedPreferences("test",MODE_PRIVATE);
        String SavedName = ShPerf.getString("Name","");
        String SavedPwd =ShPerf.getString("Pwd","");
        //декодирование сохраненного пароля
        byte[] UName = Base64.decode(SavedPwd,Base64.DEFAULT);
        String decodedPwd = " ";
        try {
            decodedPwd = new String(UName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //проверка на пустое значение полей формы
        if (nameText.equals("")||passwordtext.equals("")) {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Не заполнены поля", Toast.LENGTH_SHORT);
            toast.show();
         //сравнение заполненых полей формы и сохраненых при регистрации
        }else if (nameText.equals(SavedName) && passwordtext.equals(decodedPwd)){
            Intent intent = new Intent(this, Welcom.class);
            intent.putExtra("Name", nameText);
            intent.putExtra("active","Login");
            startActivity(intent);
        }else{
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Не верно Имя или Пароль", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
