package com.example.somesh.myproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.somesh.myproject.category.Category;
import com.example.somesh.myproject.category.CategoryAdapter;

import java.util.ArrayList;

public class TopicsListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topics_list);

        ArrayList<Category> arrayList = new ArrayList<>();
        arrayList.add(new Category(R.drawable.c));
        arrayList.add(new Category(R.drawable.cpp));
        arrayList.add(new Category(R.drawable.java));
        arrayList.add(new Category(R.drawable.python));
        arrayList.add(new Category(R.drawable.sql));
        CategoryAdapter CA = new CategoryAdapter(this, arrayList);
        ListView lv = findViewById(R.id.CommonListLayout);
        lv.setAdapter(CA);
    }
}
