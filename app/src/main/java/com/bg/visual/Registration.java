package com.bg.visual;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Registration extends AppCompatActivity {

    SharedPreferences shPref;

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
            //проверка на пустые поля
        if(NameText.equals("")&&MailText.equals("")&&PasswordText.equals("")&&ConfirmPasswordText.equals("")) {
            Toast toast = Toast.makeText(this,"Не заполнены поля ", Toast.LENGTH_SHORT);
            toast.show();
            //проверка на совпадение паролей
            }else if (!(PasswordText.equals(ConfirmPasswordText))) {
                Toast toast = Toast.makeText(this,"Пароли не совпадают", Toast.LENGTH_SHORT);
                toast.show();
                } else {
                //шифрование пароля
                byte[] data = null;
                data = PasswordText.getBytes();
                String PwdText = Base64.encodeToString(data,Base64.DEFAULT);
                // запись данных для дальнейшей проверки
                Save(NameText,MailText,PwdText);
                //отправка данных в другой активити
                Intent intent = new Intent(this,Welcom.class);
                intent.putExtra("Name",NameText);
                intent.putExtra("Mail",MailText);
                startActivity(intent);
                }
    }
        //сохранение данных
    void Save(String Name,String Mail,String Password){
        shPref = getSharedPreferences("test",MODE_PRIVATE);
        SharedPreferences.Editor editor = shPref.edit();
        editor.putString("Name", Name);
        editor.putString("Mail", Mail);
        editor.putString("Pwd", Password);
        editor.apply();
    }
}
