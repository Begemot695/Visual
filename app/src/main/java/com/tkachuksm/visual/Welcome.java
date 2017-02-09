package com.tkachuksm.visual;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Welcome extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    final int DIALOG = 1;
    final List<String> myDataset =  new ArrayList<>();
    AlertDialog.Builder ad;
    LinearLayout view;
    private SQLiteDatabase db;
    private Cursor cursor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);


        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // если мы уверены, что изменения в контенте не изменят размер layout-а RecyclerView
        // передаем параметр true - это увеличивает производительность
        mRecyclerView.setHasFixedSize(true);

        // используем linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        // создаем адаптер
        mAdapter = new RecyclerAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);

        readRecvizit();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DIALOG);
              //  myDataset.add("item"+myDataset.size());
              //  mAdapter.notifyDataSetChanged();
            }
        });

    }
    protected Dialog onCreateDialog(int id){
        ad = new AlertDialog.Builder(this);
        ad.setTitle("Новая позиция");
        ad.setIcon(android.R.drawable.ic_dialog_alert);
        view = (LinearLayout) getLayoutInflater().inflate(R.layout.dialog, null);
        ad.setView(view);

        ad.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               EditText editText = (EditText) view.findViewById(R.id.textItem);
                String Items = editText.getText().toString();
                writeRecvizit(Items);
                readRecvizit();
                //myDataset.add(Items);
                mAdapter.notifyDataSetChanged();
                editText.setText("");
            }
        });
        return ad.create();
    }

    public void readRecvizit(){
        try {
            SQLiteOpenHelper VDbHelper = new VisualDatabaseHelper(this);
            SQLiteDatabase db = VDbHelper.getReadableDatabase();
            Cursor cursor = db.query("FRUITS",new String[]{"_id","FRUIT"},null,null,null,null,null);
            if (cursor.moveToFirst()){
                do {
                    myDataset.add(cursor.getString(1));
                }while (cursor.moveToNext());
            }
            cursor.close();
            db.close();
        }
        catch (SQLException e){
            Toast toast = Toast.makeText(this, "Database unavailable Fruit", Toast.LENGTH_SHORT);
            toast.show();
        }

    }
    public void writeRecvizit(String fruit) {
        ContentValues drinkValues = new ContentValues();
        drinkValues.put("FRUIT",fruit );


        SQLiteOpenHelper VDbHelper = new VisualDatabaseHelper(this);
        try {
            SQLiteDatabase db = VDbHelper.getReadableDatabase();
            db.insert("FRUITS", null, drinkValues);
            db.close();
        } catch (SQLiteException e) {
            Toast toast = Toast.makeText(this, "Database unavailable Fruit", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

}
