package com.tkachuksm.visual;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    List<String> mDataset;
    private SQLiteDatabase db;
    Context context;




    // класс view holder-а с помощью которого мы получаем ссылку на каждый элемент
    // отдельного пункта списка
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // наш пункт состоит только из одного TextView
        public TextView mTextView;

        public ViewHolder(View v) {
            super(v);
            mTextView = (TextView) v.findViewById(R.id.tv_recycler_item);
            mTextView.setOnClickListener(this);
            context =v.getContext();
        }

        @Override
        public void onClick(View v) {
            delete(getPosition());
        }
    }

    public  void delete(int position){
        SQLiteOpenHelper VDbHelper = new VisualDatabaseHelper(context);
        try {
            SQLiteDatabase db = VDbHelper.getReadableDatabase();
            db.delete("FRUITS","_id=?",new String[]{String.valueOf(position)});
            db.close();
        } catch (SQLiteException e) {
            Toast toast = Toast.makeText(context, "Database unavailable Fruit", Toast.LENGTH_SHORT);
            toast.show();
        }
        mDataset.remove(position);
        notifyItemRemoved(position);
    }

    // Конструктор
    public RecyclerAdapter(List<String> dataset) {
        mDataset = dataset;
    }

    // Создает новые views (вызывается layout manager-ом)
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item, parent, false);

        // тут можно программно менять атрибуты лэйаута (size, margins, paddings и др.)

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Заменяет контент отдельного view (вызывается layout manager-ом)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.mTextView.setText(mDataset.get(position));

    }

    // Возвращает размер данных (вызывается layout manager-ом)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
