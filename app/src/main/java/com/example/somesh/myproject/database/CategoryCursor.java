package com.example.somesh.myproject.database;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.somesh.myproject.R;

/**
 * Created by Shiny Grace on 1/12/2018.
 */

public class CategoryCursor extends CursorAdapter {
    public CategoryCursor(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.sample_list, viewGroup, false);

    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView CategoryTextView = view.findViewById(R.id.category_text_view);
        String ProductName = cursor.getString(cursor.getColumnIndexOrThrow(CategoryContract.categoryListEntry.COLUMN_CATEGORY_NAME));
        CategoryTextView.setText(ProductName);

    }
}
