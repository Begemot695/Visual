package com.tkachuksm.visual;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class Registration extends AppCompatActivity {

    EditText userName,userPass,confirmPass;
    SharedPreferences shfp;
    final int DIALOG = 1;
    AlertDialog.Builder ad;
    private SQLiteDatabase db;
    private Cursor cursor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);



    }

    public void btnRegIn (View view){
        userName = (EditText)findViewById(R.id.userName);
        userPass = (EditText)findViewById(R.id.userPassword);
        confirmPass = (EditText)findViewById(R.id.confirmPassword);
        String userText = userName.getText().toString();
        String passText = userPass.getText().toString();
        String confirmText = confirmPass.getText().toString();
        if (userText.equals("")|passText.equals("")|confirmText.equals("")){
            Toast toast = Toast.makeText(getApplicationContext(),"Не заполненые поля",Toast.LENGTH_SHORT);
            toast.show();
        }else if (passText.equals(confirmText)){
            byte[] data = null;
            data = passText.getBytes();
            String PwdText = Base64.encodeToString(data,Base64.DEFAULT);
           // saved(userText,PwdText);
            writeRecvizit(userText,PwdText);
            showDialog(DIALOG);

        }else {
            Toast toast = Toast.makeText(getApplicationContext(),"Пароли не совпадают",Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void btnRegOut(View view){
        userName = (EditText)findViewById(R.id.userName);
        userPass = (EditText)findViewById(R.id.userPassword);
        confirmPass = (EditText)findViewById(R.id.confirmPassword);
        userName.setText("");
        userPass.setText("");
        confirmPass.setText("");
    }

    void saved (String Name,String password){
        shfp = getSharedPreferences("visual",MODE_PRIVATE);
        SharedPreferences.Editor editor = shfp.edit();
        editor.putString("Name",Name);
        editor.putString("Pwd",password);
        editor.apply();
    }

    protected Dialog onCreateDialog(int id){
        ad = new AlertDialog.Builder(this);
        ad.setTitle("Регистрация");
        ad.setMessage("Регистрация прошла успешно!");
        ad.setIcon(android.R.drawable.ic_dialog_alert);
        ad.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Registration.this,LogIn.class);
                startActivity(intent);
            }
        });
        return ad.create();
    }

    public void writeRecvizit(String name,String pwd){
        ContentValues drinkValues = new ContentValues();
        drinkValues.put("NAME",name );
        drinkValues.put("PASSWORD", pwd);

        SQLiteOpenHelper VDbHelper = new VisualDatabaseHelper(this);
        try {
            SQLiteDatabase db = VDbHelper.getReadableDatabase();
            db.insert("USER",null,drinkValues);
            db.close();
        } catch(SQLiteException e) {
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }

    }
}
