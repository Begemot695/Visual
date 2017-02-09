package com.tkachuksm.visual;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;

public class LogIn extends AppCompatActivity  {

    EditText userName,userPass;
    String saveName,savePwd;
    SharedPreferences shfp;
    private SQLiteDatabase db;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
    }

    public void btnLogIn(View view) {
        userName = (EditText) findViewById(R.id.userName);
        userPass = (EditText) findViewById(R.id.userPassword);
        String nameText = userName.getText().toString();
        String passText = userPass.getText().toString();

        shfp = getSharedPreferences("visual",MODE_PRIVATE);
       // String saveName = shfp.getString("Name","");
       // String savePwd = shfp.getString("Pwd","");

        if (nameText.equals("") | passText.equals("")) {
            Toast toast = Toast.makeText(getApplicationContext(), "Не заполненые поля", Toast.LENGTH_SHORT);
            toast.show();
        } else {
            readRecvizit(nameText);
            byte[] UName = Base64.decode(savePwd,Base64.DEFAULT);
            String decodedPwd = " ";
            try {
                decodedPwd = new String(UName, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            if (passText.equals(decodedPwd) & nameText.equals(saveName)) {
                Intent intent = new Intent(this, Welcome.class);

                intent.putExtra("Name", nameText);
                startActivity(intent);
            }else{
                Toast toast = Toast.makeText(getApplicationContext(), "Не верно имя или пароль", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    };

    public void readRecvizit(String name){
        try {
            SQLiteOpenHelper VDbHelper = new VisualDatabaseHelper(this);
            SQLiteDatabase db = VDbHelper.getReadableDatabase();
            Cursor cursor = db.query("USER",new String[]{"_id","NAME","PASSWORD"},"NAME = ?",new String[] {name},null,null,null);
            if (cursor.moveToFirst()){
                saveName = cursor.getString(1);
                savePwd = cursor.getString(2);
            }
            cursor.close();
            db.close();
        }
        catch (SQLException e){
            Toast toast = Toast.makeText(this, "Database unavailable User", Toast.LENGTH_SHORT);
            toast.show();
        }

    }

    public void btnLogOut(View view){
        userName = (EditText) findViewById(R.id.userName);
        userPass = (EditText) findViewById(R.id.userPassword);
        userName.setText("");
        userPass.setText("");
    }

}
