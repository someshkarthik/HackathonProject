package com.example.somesh.myproject;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.somesh.myproject.data.Contract;

public class CategoryCursorAdapter extends CursorAdapter {

    public CategoryCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.category_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView nameTextView = view.findViewById(R.id.name);
        int nameColumnIndex = cursor.getColumnIndex(Contract.CategoryEntry.COLUMN_CATEGORY_NAME);
        String Name = cursor.getString(nameColumnIndex);
        nameTextView.setText(Name);
    }
}
