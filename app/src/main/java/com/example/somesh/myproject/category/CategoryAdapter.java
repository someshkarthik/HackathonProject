package com.example.somesh.myproject.category;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.somesh.myproject.R;

import java.util.ArrayList;

/**
 * Created by Robo warrior on 11-01-2018.
 */

public class CategoryAdapter extends ArrayAdapter<Category> {

    public CategoryAdapter(@NonNull Context context, ArrayList<Category> arrayList) {
        super(context, 0,arrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View ListItem = convertView;
        if(ListItem == null)
        {
            ListItem = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }
        Category CurrentCategory = getItem(position);
        ImageView iv1 = (ImageView) ListItem.findViewById(R.id.list_item_ImageView);
        iv1.setImageResource(CurrentCategory.getImageResourceId());
        return ListItem;
    }
}
